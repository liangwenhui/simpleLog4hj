package xyz.liangwh.simplelogger4j.core.appenders.impl;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.EventTranslatorThreeArg;
import com.lmax.disruptor.EventTranslatorTwoArg;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import xyz.liangwh.simplelogger4j.core.appenders.Appender;
import xyz.liangwh.simplelogger4j.core.events.HandleEvent;
import xyz.liangwh.simplelogger4j.core.manage.LogFactory;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class FileAppender implements Appender {
    //private StringBuffer buffer ;
    private ByteBuffer buffer;
    private int bufferSize;
    private final static int MAX_SIZE = 1024*8;
    private long timeoutMs = 100;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    @Setter
    private Disruptor<HandleEvent> writer;
    @Setter
    private EventTranslatorTwoArg translator;
    private volatile long lastSendTime ;
//    private AtomicLong nextStartIndex = new AtomicLong();
    private long nextStartIndex = 0L;
    public void init(){
        bufferSize = 1024;
        //buffer = new StringBuffer();
        buffer = ByteBuffer.allocate(bufferSize+1024);
        //registrant = LogFactory.getQueueRegistrant();
        lastSendTime = System.currentTimeMillis();
    }

    public FileAppender(){
        System.out.println("new file append");
        init();
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            //System.out.println("关闭前刷写日志"+buffer.length());
            System.out.println("关闭前刷写日志"+buffer.position());

            close();
        }));

    }

    public  void clearBuffer(){
        buffer = ByteBuffer.allocate(bufferSize+1024);
        //buffer.setLength(0);
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
            flag = buffer.position()>=bufferSize;

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
    public void doAppend(byte[] s) {

        flush(s,false);
    }

    private void flush(byte[] s,boolean flushNow){
        boolean timeToFlush = false;
        lock.writeLock().lock();
        try {
            //buffer.append(new String(s));
            if(s==null){
                return;
            }
            buffer.put(s);
            timeToFlush = isTimeToFlush();
            if(flushNow||timeToFlush){
                buffer.flip();
                byte[] getb = new byte[buffer.limit()];
                buffer.get(getb);
                send( getb);
                clearBuffer();
            }
        }finally {
            lock.writeLock().unlock();
        }
    }

    private void send(byte[] s){
        if(s.length==0){
            return;
        }
        RingBuffer ringBuffer = writer.getRingBuffer();
        //ByteBuffer wrap = ByteBuffer.wrap(s);
        //
        //s = new byte[]{33,33,33,33,33,33,33,33};
        ringBuffer.publishEvent(translator,s,nextStartIndex);
        nextStartIndex+=s.length;
        lastSendTime = System.currentTimeMillis();
    }

//    private ArrayList<byte[]> getbbs(ByteBuffer bb) throws UnsupportedEncodingException {
//        ArrayList<byte[]> barr = new ArrayList<>();
//        byte[] b;
//        while(bb.position()<bb.limit()){
//            if((bb.limit()-bb.position())>=64){
//                b = new byte[64];
//            }else{
//                b = new byte[bb.limit()-bb.position()];
//            }
//            bb.get(b);
//            barr.add(b);
//        }
////        byte[][] bbb = new byte[barr.size()][];
////        for(int i=0;i<barr.size();i++){
////            bbb[i] = barr.get(i);
////        }
//        return barr;
//    }


    /**
     * 关闭将日志flush
     */
    private void close(){
        flush("".getBytes(),true);

    }


}
