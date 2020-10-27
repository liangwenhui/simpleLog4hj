package xyz.liangwh.simplelogger4j.core.handler;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import xyz.liangwh.simplelogger4j.core.AppendRegistrant;
import xyz.liangwh.simplelogger4j.core.QueueRegistrant;
import xyz.liangwh.simplelogger4j.core.appenders.impl.FileAppender;
import xyz.liangwh.simplelogger4j.core.events.AcceptEvent;
import xyz.liangwh.simplelogger4j.core.events.HandleEvent;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * 消费accpet queue
 * 将事件的值推送到 日志buffer中
 */
public class Send2BufferHandler {

    static FileAppender  fileAppender = new FileAppender();//(FileAppender)AppendRegistrant.getInstance().getAppend(FileAppender.class);

    //private QueueRegistrant registrant = QueueRegistrant.getInstance();
    public void send(AcceptEvent event, long sequence, boolean endOfBatch){
        fileAppender.doAppend(event.getMsg());
    }


}
