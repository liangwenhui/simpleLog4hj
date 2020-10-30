package xyz.liangwh.simplelogger4j;

import org.junit.Test;
import xyz.liangwh.simplelogger4j.core.loggers.FileLogger;
import xyz.liangwh.simplelogger4j.core.loggers.Logger;
import xyz.liangwh.simplelogger4j.core.manage.LoggerManager;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Test3 {
    @Test
    public void test(){
        Logger logger = LoggerManager.getLogger(this.getClass());
       final CountDownLatch latch = new CountDownLatch(2);
        //AppendRegistrant.getInstance().stop();
        for(int t=0;t<2;t++){
            new Thread(()->{
                for(int i=0;i<1;i++){
                    logger.info(i+"_abc abc\n");
                }
                latch.countDown();
            }).start();
        }

        try {
            latch.await();
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
