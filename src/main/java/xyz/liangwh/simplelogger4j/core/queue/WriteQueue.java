package xyz.liangwh.simplelogger4j.core.queue;

import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;
import xyz.liangwh.simplelogger4j.core.events.AcceptEvent;
import xyz.liangwh.simplelogger4j.core.events.HandleEvent;
import xyz.liangwh.simplelogger4j.core.handler.Send2BufferHandler;
import xyz.liangwh.simplelogger4j.core.handler.WriteBufferHandler;

import java.util.concurrent.ThreadFactory;

public class WriteQueue implements QueueFactory<HandleEvent> {
    private final static int BUFFER_SIZE = 2048;

    private Disruptor queue;
    private Object o = new Object();

    @Override
    public Disruptor<HandleEvent> getQueue() {
        if(this.queue==null){
            synchronized (o){
                if(this.queue==null){
                    Disruptor<HandleEvent> disruptor
                           // = new Disruptor<>(HandleEvent::new, BUFFER_SIZE, DaemonThreadFactory.INSTANCE);
                            = new Disruptor<>(HandleEvent::new, BUFFER_SIZE, new ThreadFactory() {
                        @Override
                        public Thread newThread(Runnable r) {
                            Thread thread = new Thread(r,"HandleEventThread");
                            thread.setDaemon(true);
                            return thread;
                        }
                    });
                    //消息处理
                    disruptor.handleEventsWith(new WriteBufferHandler()::write);
                    disruptor.start();
                    queue = disruptor;
                }
            }
        }
        return queue;
    }

    @Override
    public void setQueue(Disruptor<HandleEvent> queue) {

    }
}
