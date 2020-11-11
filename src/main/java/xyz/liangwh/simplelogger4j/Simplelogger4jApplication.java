package xyz.liangwh.simplelogger4j;

import xyz.liangwh.simplelogger4j.core.loggers.Logger;
import xyz.liangwh.simplelogger4j.core.manage.LoggerManager;
import xyz.liangwh.simplelogger4j.testData.AiRandData;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
public class Simplelogger4jApplication {

    public static void main(String[] args) {
        int t = AiRandData.aiRandomInt[0];
        Logger logger = LoggerManager.getLogger(Simplelogger4jApplication.class);
        int iLoopTimes = 100;
        int test_times = 10000;
        long start = System.currentTimeMillis();
        //final CountDownLatch latch = new CountDownLatch(tn);
        //AppendRegistrant.getInstance().stop();
        for(int j=0;j<iLoopTimes;j++){
            for(int i=0;i<test_times;i++){
                logger.fPrint("INTERGET:%d|STRING:%s|FLOAT:%f\n", AiRandData.aiRandomInt[i],AiRandData.asRandomStr[i],AiRandData.afRandomFloat[i]);
                 //logger.fPrint(":%d\n",i);

                //logger.fPrint("%d\n",i);
                //String format = String.format("%s   %f" ,"2a1",1.2);
                //String forma = format("INTERGET:%d|STRING:%s|FLOAT:%f\n",1,"ssss",2.3);
            }
            for(int i=test_times-1;i>j;i--){
                logger.fPrint("INTERGET:%d|STRING:%s|FLOAT:%f\n", AiRandData.aiRandomInt[i],AiRandData.asRandomStr[i],AiRandData.afRandomFloat[i]);
                //logger.fPrint(":%d\n",i);
                //logger.fPrint("%d\n",i);
                //String format = String.format("%s   %f" ,"2a1",1.2);
                //String forma = format("INTERGET:%d|STRING:%s|FLOAT:%f\n",1,"ssss",2.3);
            }
        }
        long end = System.currentTimeMillis();
        try {
            //latch.await();
            System.out.println("use time:"+(end-start)+"s");
            //TimeUnit.MILLISECONDS.sleep(20);
            System.exit(1);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

}
