package xyz.liangwh.simplelogger4j.core.manage;

import com.lmax.disruptor.dsl.Disruptor;
import lombok.Data;
import xyz.liangwh.simplelogger4j.core.events.AcceptEvent;
import xyz.liangwh.simplelogger4j.core.events.HandleEvent;
import xyz.liangwh.simplelogger4j.core.queue.AcceptQueue;
import xyz.liangwh.simplelogger4j.core.queue.WriteQueue;

@Data
public class QueueRegistrant {

    private Disruptor<AcceptEvent> accepter;
    private Disruptor<HandleEvent> writer;
    private static Object o = new Object();
    public QueueRegistrant(){

    }

    public void init(){

        WriteQueue writeQueue = new WriteQueue();
        writer = writeQueue.getQueue();
        AcceptQueue acceptQueue = new AcceptQueue();
        Disruptor<AcceptEvent> queue = acceptQueue.getQueue();
        accepter = queue;
    }




}
