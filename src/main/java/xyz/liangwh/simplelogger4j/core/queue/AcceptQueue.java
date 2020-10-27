package xyz.liangwh.simplelogger4j.core.queue;

import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;
import xyz.liangwh.simplelogger4j.core.events.AcceptEvent;
import xyz.liangwh.simplelogger4j.core.handler.Send2BufferHandler;

import java.util.concurrent.ThreadFactory;


public class AcceptQueue implements QueueFactory<AcceptEvent>{

    private final static int BUFFER_SIZE = 2048;

    private Disruptor queue;
    private Object o = new Object();


    public  AcceptQueue (){}

    public Disruptor<AcceptEvent> getQueue(){
        if(this.queue==null){
            synchronized (o){
                if(this.queue==null){
                    Disruptor<AcceptEvent> disruptor
                            //= new Disruptor<>(AcceptEvent::new, BUFFER_SIZE, DaemonThreadFactory.INSTANCE);
                            = new Disruptor<>(AcceptEvent::new, BUFFER_SIZE, new ThreadFactory() {
                        @Override
                        public Thread newThread(Runnable r) {
                            Thread thread = new Thread(r,"AcceptEventThread");
                            thread.setDaemon(true);
                            return thread;
                        }
                    });
                    //消息处理
                    disruptor.handleEventsWith(new Send2BufferHandler()::send);
                    disruptor.start();
                    queue = disruptor;
                }
            }
        }
        return queue;
    }

    @Override
    public void setQueue(Disruptor queue) {
        return;
    }


}
