package xyz.liangwh.simplelogger4j;

import org.junit.jupiter.api.Test;
import xyz.liangwh.simplelogger4j.core.AppendRegistrant;
import xyz.liangwh.simplelogger4j.core.loggers.FileLogger;

import java.util.concurrent.TimeUnit;

public class Test3 {
    @Test
    public void test(){
        FileLogger logger = FileLogger.getLogger();
        //AppendRegistrant.getInstance().stop();
        for(int i=0;i<1000000;i++){
            logger.info(i+"_abc abc\n");
            //break;
        }
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
