package xyz.liangwh.simplelogger4j.core.loggers;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import lombok.Data;
import xyz.liangwh.simplelogger4j.core.events.AcceptEvent;
import xyz.liangwh.simplelogger4j.core.events.StringTranslator;
import xyz.liangwh.simplelogger4j.core.queue.QueueFactory;

import java.nio.ByteBuffer;

/**
 * 日志API提供者顶级接口
 */
public abstract class Logger {

    private String className;

    private  Disruptor<AcceptEvent> queue;

    private EventTranslatorOneArg  translator;

    public  void  info(String msg){
        String fileterMsg = filter(msg);
        sendToAccepter(msg);
    }

    protected  void sendToAccepter(String msg){
        try{
            //Disruptor<AcceptEvent> queue = accepter.getQueue();
            ByteBuffer wrap = ByteBuffer.wrap(msg.getBytes("UTF-8"));
            RingBuffer<AcceptEvent> ringBuffer = queue.getRingBuffer();
            ringBuffer.publishEvent(translator,wrap);
        }catch (Exception e){
            e.printStackTrace();
        }
    };

    protected  String filter(String msg){
        //默认不做处理
        return msg;
    };

    ;
    //void error(Object msg);
    //void warn(Object msg);


    public EventTranslatorOneArg getTranslator() {
        return translator;
    }

    public void setTranslator(EventTranslatorOneArg translator) {
        this.translator = translator;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Disruptor<AcceptEvent> getQueue() {
        return queue;
    }

    public void setQueue(Disruptor<AcceptEvent> queue) {
        this.queue = queue;
    }
}
