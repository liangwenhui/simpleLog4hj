package xyz.liangwh.simplelogger4j.core.queue;

import com.lmax.disruptor.dsl.Disruptor;

public interface QueueFactory<T> {

    Disruptor<T> getQueue();
    void setQueue(Disruptor<T> queue);
}
