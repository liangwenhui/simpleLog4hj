package xyz.liangwh.simplelogger4j.core.handler;

import xyz.liangwh.simplelogger4j.core.appenders.Appender;
import xyz.liangwh.simplelogger4j.core.appenders.impl.FileAppender;
import xyz.liangwh.simplelogger4j.core.events.AcceptEvent;
import xyz.liangwh.simplelogger4j.core.manage.AppendRegistrant;
import xyz.liangwh.simplelogger4j.core.manage.LogFactory;
import xyz.liangwh.simplelogger4j.core.utils.FormatUtil;

import java.util.Formatter;

/**
 * 消费accpet queue
 * 将事件的值推送到 日志buffer中
 */
public class Send2BufferHandler {

    private Formatter formatter =  new Formatter();
    private Appender fileAppender;
           // = LogFactory.getAppendRegistrant().getAppend(FileAppender.class);
    public Send2BufferHandler(){
        System.out.println("Send2BufferHandler::new");
        AppendRegistrant appendRegistrant = LogFactory.getAppendRegistrant();
        fileAppender= appendRegistrant.getAppend(FileAppender.class);
    }
    //private QueueRegistrant registrant = QueueRegistrant.getInstance();
    public void send(AcceptEvent event, long sequence, boolean endOfBatch){

        fileAppender.doAppend(FormatUtil.format(event.getFormat(),event.getArgs()).getBytes());
        //fileAppender.doAppend(event.getBytes());
    }


}
