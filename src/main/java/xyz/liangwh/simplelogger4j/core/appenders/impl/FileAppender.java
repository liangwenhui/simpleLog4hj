package xyz.liangwh.simplelogger4j.core.appenders.impl;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import xyz.liangwh.simplelogger4j.core.LogFactory;
import xyz.liangwh.simplelogger4j.core.QueueRegistrant;
import xyz.liangwh.simplelogger4j.core.appenders.Appender;
import xyz.liangwh.simplelogger4j.core.events.HandleEvent;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class FileAppender implements Appender {

    private StringBuffer buffer ;
    private int bufferSize;
    private final static int MAX_SIZE = 1024*8;
    private long timeoutMs = 300;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private QueueRegistrant registrant;
    private volatile long lastSendTime ;

    public void init(){
        bufferSize = 2048;
        buffer = new StringBuffer();
        registrant = LogFactory.getQueueRegistrant();
        LogFactory.getAppendRegistrant().regist(this);
        lastSendTime = System.currentTimeMillis();
    }

    public FileAppender(){
        init();
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
        boolean timeToFlush = false;
        lock.writeLock().lock();
       try {
           buffer.append(s);
           timeToFlush = isTimeToFlush();
           if(timeToFlush){
               send( buffer.toString());
               clearBuffer();
           }
       }finally {
           lock.writeLock().unlock();
       }

    }

    private void send(String s){
        Disruptor<HandleEvent> writer = registrant.getWriter();
        RingBuffer<HandleEvent> ringBuffer = writer.getRingBuffer();
        ByteBuffer wrap = ByteBuffer.wrap(s.getBytes());
        ringBuffer.publishEvent(FileAppender::translate,wrap);
        lastSendTime = System.currentTimeMillis();
    }

    public static void translate(HandleEvent event, long sequence, ByteBuffer buffer)
    {
        Charset charset = null;
        CharsetDecoder decoder = null;
        CharBuffer charBuffer = null;
        try {
            charset = Charset.forName("UTF-8");
            decoder = charset.newDecoder();
            //用这个的话，只能输出来一次结果，第二次显示为空
            // charBuffer = decoder.decode(buffer);
            charBuffer = decoder.decode(buffer.asReadOnlyBuffer());
            event.setMsg(charBuffer.toString());
            event.setFileName("D:\\info.log");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
