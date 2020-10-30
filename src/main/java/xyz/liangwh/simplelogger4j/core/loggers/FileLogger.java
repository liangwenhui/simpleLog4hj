package xyz.liangwh.simplelogger4j.core.loggers;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import xyz.liangwh.simplelogger4j.core.manage.LogFactory;
import xyz.liangwh.simplelogger4j.core.manage.QueueRegistrant;
import xyz.liangwh.simplelogger4j.core.events.AcceptEvent;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class FileLogger extends Logger{


//    @Override
//    public void info(String msg) {
//        try{
//            Disruptor<AcceptEvent> accepter = registrant.getAccepter();
//            ByteBuffer wrap = ByteBuffer.wrap(msg.getBytes("UTF-8"));
//            RingBuffer<AcceptEvent> ringBuffer = accepter.getRingBuffer();
//            ringBuffer.publishEvent(FileLogger::translate,wrap);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

//    private void init(){
//        registrant = LogFactory.getQueueRegistrant();
//
//    }

//    public static FileLogger getLogger(){
//        FileLogger worker = new FileLogger();
//        worker.init();
//
//        return worker;
//    }


}
