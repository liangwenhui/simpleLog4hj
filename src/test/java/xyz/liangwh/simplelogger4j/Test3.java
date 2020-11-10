package xyz.liangwh.simplelogger4j;

import lombok.SneakyThrows;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import xyz.liangwh.simplelogger4j.core.loggers.FileLogger;
import xyz.liangwh.simplelogger4j.core.loggers.Logger;
import xyz.liangwh.simplelogger4j.core.manage.LoggerManager;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Test3 {
    @Test
    public void test(){
        Logger logger = LoggerManager.getLogger(this.getClass());
        int iLoopTimes = 100;
        int test_times = 10000;
        long start = System.currentTimeMillis();
        //final CountDownLatch latch = new CountDownLatch(tn);
        //AppendRegistrant.getInstance().stop();
        for(int j=0;j<iLoopTimes;j++){
            for(int i=0;i<test_times;i++){
                logger.fPrint("INTERGET:%d|STRING:%s|FLOAT:%f\n",i,"awsadsadwsad",3.2);
                //String format = String.format("%s   %f" ,"2a1",1.2);
                //String forma = format("INTERGET:%d|STRING:%s|FLOAT:%f\n",1,"ssss",2.3);
            }
            for(int i=test_times-1;i>j;i--){
                logger.fPrint("INTERGET:%d|STRING:%s|FLOAT:%f\n",i,"awsadsadwsad",3.2);
                //String format = String.format("%s   %f" ,"2a1",1.2);
                //String forma = format("INTERGET:%d|STRING:%s|FLOAT:%f\n",1,"ssss",2.3);
            }
        }
        long end = System.currentTimeMillis();
        try {
            //latch.await();
            System.out.println("use time:"+(end-start)+"ms");
            //TimeUnit.MILLISECONDS.sleep(20);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Test
    public void test2(){
        Logger logger = LoggerManager.getLogger(this.getClass());
        final CountDownLatch latch = new CountDownLatch(1);
        //AppendRegistrant.getInstance().stop();
        for(int t=0;t<1;t++){
            new Thread(()->{
                for(int i=0;i<2;i++){
//                    logger.info(i+"_abc  abc\n");
                    //logger.fPrintln("%d ----------  %s",i,"\"kkkk\"");
                }
                latch.countDown();
            }).start();
        }

        try {
            latch.await();
            //TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



    @Test
    @SneakyThrows
    public  void testMap(){
        boolean o = StringUtils.isBlank(" ");
        RandomAccessFile file = new RandomAccessFile("D:\\map.log", "rw");
        String msg = "1bsadasdasdsadsadadsadsadsadsa";
        MappedByteBuffer map2 = file.getChannel().map(FileChannel.MapMode.READ_WRITE, msg.length(), msg.length());
        map2.put(msg.getBytes());
        MappedByteBuffer map = file.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, msg.length());
        map.put(msg.getBytes());

    }

    @Test
    public void testForm(){
        int j=0;
        for(int i=0;i<1000000;i++){
            //String format = String.format("%s   %d" ,"2a1",1);
            //StringUtils.replaceOnce("%s   %d","%s","string");
            String forma = format("INTERGET:%d|STRING:%s|FLOAT:%f\n",1,"ssss",2.3);
            //System.out.println(forma);
        }
        String format = String.format("%s   %f" ,"2a1",1.2);
        System.out.println(format);
    }

    private String format(String format,Object...args){
        for (Object o : args){
            if(o instanceof String){
                format= StringUtils.replaceOnce(format, "%s", (String) o);
            }else if(o instanceof Integer){
                format= StringUtils.replaceOnce(format,"%d",o.toString());
            }else if(o instanceof Float){
                format=StringUtils.replaceOnce(format,"%f",o.toString());
            }else if(o instanceof Double){
                format=StringUtils.replaceOnce(format,"%f",o.toString());
            }
        }
        return format;
    }
    private String format2(String format,Object...args){
        for (Object o : args){
            if(o instanceof String){
                StringUtils.replaceOnce(format,"%s",(String) o);
            }else if(o instanceof Integer){
                StringUtils.replaceOnce(format,"%d",o.toString());
            }else if(o instanceof Float){
                StringUtils.replaceOnce(format,"%f",o.toString());
            }else if(o instanceof Double){
                StringUtils.replaceOnce(format,"%f",o.toString());
            }
        }
        return null;
    }

}
