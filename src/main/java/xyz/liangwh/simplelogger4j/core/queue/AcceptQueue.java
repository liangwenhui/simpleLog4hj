package xyz.liangwh.simplelogger4j.core.queue;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;
import xyz.liangwh.simplelogger4j.core.events.AcceptEvent;
import xyz.liangwh.simplelogger4j.core.events.AcceptMarkEvent;
import xyz.liangwh.simplelogger4j.core.handler.Send2BufferHandler;

import java.util.concurrent.ThreadFactory;


public class AcceptQueue implements QueueFactory<AcceptMarkEvent>{

    private final static int BUFFER_SIZE = 1024 * 32;

    private Disruptor queue;
    private Object o = new Object();


    public  AcceptQueue (){}

    public Disruptor<AcceptMarkEvent> getQueue(){
        if(this.queue==null){
            synchronized (o){
                if(this.queue==null){
                    Disruptor<AcceptMarkEvent> disruptor
                            //= new Disruptor<>(AcceptEvent::new, BUFFER_SIZE, DaemonThreadFactory.INSTANCE);
                            = new Disruptor<>(AcceptMarkEvent::new, BUFFER_SIZE, new ThreadFactory() {
                        @Override
                        public Thread newThread(Runnable r) {
                            Thread thread = new Thread(r,"AcceptMarkEventThread");
                            thread.setDaemon(true);
                            return thread;
                        }
                    }, ProducerType.MULTI,new BlockingWaitStrategy());
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
