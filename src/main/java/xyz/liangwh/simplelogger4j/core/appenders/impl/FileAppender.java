package xyz.liangwh.simplelogger4j.core.appenders.impl;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.EventTranslatorThreeArg;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import xyz.liangwh.simplelogger4j.core.appenders.Appender;
import xyz.liangwh.simplelogger4j.core.events.HandleEvent;
import xyz.liangwh.simplelogger4j.core.manage.LogFactory;

import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class FileAppender implements Appender {
    private StringBuffer buffer ;
    private int bufferSize;
    private final static int MAX_SIZE = 1024*8;
    private long timeoutMs = 100;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    @Setter
    private Disruptor<HandleEvent> writer;
    @Setter
    private EventTranslatorThreeArg translator;
    private volatile long lastSendTime ;
//    private AtomicLong nextStartIndex = new AtomicLong();
    private long nextStartIndex = 0L;
    public void init(){
        bufferSize = 2048;
        buffer = new StringBuffer();
        //registrant = LogFactory.getQueueRegistrant();
        lastSendTime = System.currentTimeMillis();
    }

    public FileAppender(){
        System.out.println("new file append");
        init();
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("关闭前刷写日志"+buffer.length());

            close();
        }));

    }

    public  void clearBuffer(){
        buffer.setLength(0);
    }

    /**
     * 动态扩展
     */
    public  void expansion(){

        int size = bufferSize<<1;
        if(size>0&&size<=MAX_SIZE){
            bufferSize = size;
        }
    }

    public boolean isTimeToFlush(){
        boolean flag = false;
        lock.readLock().lock();
        //满了没
        try {
            flag = buffer.length()>=bufferSize;

        }finally {
            lock.readLock().unlock();
        }
        if(flag){
            return flag;
        }else{
            //判断时间
            long totalTimeMillis = System.currentTimeMillis()-lastSendTime;
            flag = timeoutMs<=totalTimeMillis;
        }
        return flag;
    }
    private void sendToQueue(String s){

    }
    @Override
    public void doAppend(String s) {
        flush(s,false);
    }

    private void flush(String s,boolean flushNow){
        boolean timeToFlush = false;
        lock.writeLock().lock();
        try {
            buffer.append(s);
            timeToFlush = isTimeToFlush();
            if(flushNow||timeToFlush){
                send( buffer.toString());
                clearBuffer();
            }
        }finally {
            lock.writeLock().unlock();
        }
    }

    private void send(String s){
        if(StringUtils.isEmpty(s)){
            return;
        }
        RingBuffer ringBuffer = writer.getRingBuffer();
        ByteBuffer wrap = ByteBuffer.wrap(s.getBytes());
        ringBuffer.publishEvent(translator,wrap,nextStartIndex,new Long(s.length()));
        nextStartIndex+=s.length();
        lastSendTime = System.currentTimeMillis();
    }


    /**
     * 关闭将日志flush
     */
    private void close(){
        flush("",true);

    }


}
