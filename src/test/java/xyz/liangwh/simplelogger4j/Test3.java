package xyz.liangwh.simplelogger4j;

import org.junit.Test;
import xyz.liangwh.simplelogger4j.core.AppendRegistrant;
import xyz.liangwh.simplelogger4j.core.loggers.FileLogger;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Test3 {
    @Test
    public void test(){
        FileLogger logger = FileLogger.getLogger();
       final CountDownLatch latch = new CountDownLatch(2);
        //AppendRegistrant.getInstance().stop();
        for(int t=0;t<2;t++){
            new Thread(()->{
                for(int i=0;i<1000000;i++){
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
