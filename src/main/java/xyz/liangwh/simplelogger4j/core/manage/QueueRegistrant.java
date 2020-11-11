package xyz.liangwh.simplelogger4j.core.manage;

import com.lmax.disruptor.dsl.Disruptor;
import lombok.Data;
import xyz.liangwh.simplelogger4j.core.events.AcceptEvent;
import xyz.liangwh.simplelogger4j.core.events.AcceptMarkEvent;
import xyz.liangwh.simplelogger4j.core.events.HandleEvent;
import xyz.liangwh.simplelogger4j.core.queue.AcceptQueue;
import xyz.liangwh.simplelogger4j.core.queue.WriteQueue;

@Data
public class QueueRegistrant {

    private Disruptor<AcceptMarkEvent> accepter;
    private Disruptor<HandleEvent> writer;
    private static Object o = new Object();
    public QueueRegistrant(){

    }

    public void init(){

        WriteQueue writeQueue = new WriteQueue();
        writer = writeQueue.getQueue();
        AcceptQueue acceptQueue = new AcceptQueue();
        Disruptor<AcceptMarkEvent> queue = acceptQueue.getQueue();
        accepter = queue;

        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            //System.out.println("关闭前刷写日志"+buffer.length());
            System.out.println("关闭队列");

            close();
        }));
    }

    private void close() {
        accepter.shutdown();
        writer.shutdown();
    }


}
