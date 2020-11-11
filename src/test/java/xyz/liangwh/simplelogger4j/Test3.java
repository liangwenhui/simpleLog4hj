package xyz.liangwh.simplelogger4j;

import lombok.SneakyThrows;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import xyz.liangwh.simplelogger4j.core.loggers.FileLogger;
import xyz.liangwh.simplelogger4j.core.loggers.Logger;
import xyz.liangwh.simplelogger4j.core.manage.LoggerManager;
import xyz.liangwh.simplelogger4j.testData.AiRandData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Test3 {
    @Test
    public void test(){
        int t = AiRandData.aiRandomInt[0];
        Logger logger = LoggerManager.getLogger(this.getClass());
        int iLoopTimes = 100;
        int test_times = 10000;
        long start = System.currentTimeMillis();
        //final CountDownLatch latch = new CountDownLatch(tn);
        //AppendRegistrant.getInstance().stop();
        for(int j=0;j<iLoopTimes;j++){
            for(int i=0;i<test_times;i++){
                logger.fPrint("INTERGET:%d|STRING:%s|FLOAT:%f\n", AiRandData.aiRandomInt[i],AiRandData.asRandomStr[i],AiRandData.afRandomFloat[i]);
               // logger.fPrint(":%d\n",i);

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
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Test
    public void test2(){
        Logger logger = LoggerManager.getLogger(this.getClass());
        int iLoopTimes = 1;
        int test_times = 1000000;
        long start = System.currentTimeMillis();
        //final CountDownLatch latch = new CountDownLatch(tn);
        //AppendRegistrant.getInstance().stop();
        for(int j=0;j<iLoopTimes;j++){
            for(int i=0;i<test_times;i++){
                logger.fPrint("INTERGET:%d|STRING:%s|FLOAT:%f\n",i,"awsadsadwsad",3.2);
                //logger.fPrint("%d\n",i);
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
    @Test
    public  void format2(){
        ByteBuffer bb = ByteBuffer.wrap("abcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcdabcd".getBytes());
        int index = 0;
        byte[] b ;
        ArrayList<byte[]> barr = new ArrayList<>();
       while(bb.position()<bb.limit()){
           if((bb.limit()-bb.position())>=64){
               b = new byte[64];
           }else{
               b = new byte[bb.limit()-bb.position()];
           }
            bb.get(b);
            barr.add(b);

       }
        System.out.println(barr.size());




    }
    @Test
    public  void format3(){
        ByteBuffer bb = ByteBuffer.allocate(64);
        byte[] bytes = "aaa".getBytes();
        while (true){
            while (true){
                if((bb.capacity()-bb.position())>=bytes.length){
                    bb.put(bytes);
                }else {
                    //flush
                    //Buffer clear = bb.clear();
                    bb.flip();
                    bb.get(new byte[bb.limit()]);
                    bb = ByteBuffer.allocate(64);
                    break;
                }
            }

        }
    }

    @Test
    public  void format4() throws IOException {
        File file = new File("D:\\fw.log");
        FileWriter fileWriter =new FileWriter(file);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        int iLoopTimes = 100;
        int test_times = 10000;
        long start = System.currentTimeMillis();
        //final CountDownLatch latch = new CountDownLatch(tn);
        //AppendRegistrant.getInstance().stop();
        for(int j=0;j<iLoopTimes;j++){
            for(int i=0;i<test_times;i++){
                printWriter.printf("INTERGET:%d|STRING:%s|FLOAT:%f\n",i,"awsadsadwsad",3.2);

                //logger.fPrint("%d\n",i);
                //String format = String.format("%s   %f" ,"2a1",1.2);
                //String forma = format("INTERGET:%d|STRING:%s|FLOAT:%f\n",1,"ssss",2.3);
            }
            for(int i=test_times-1;i>j;i--){
                printWriter.printf("INTERGET:%d|STRING:%s|FLOAT:%f\n",i,"awsadsadwsad",3.2);
                //logger.fPrint("%d\n",i);
                //String format = String.format("%s   %f" ,"2a1",1.2);
                //String forma = format("INTERGET:%d|STRING:%s|FLOAT:%f\n",1,"ssss",2.3);
            }
        }
        long end = System.currentTimeMillis();
        try {
            //latch.await();
            System.out.println("use time:"+(end-start)+"ms");
            TimeUnit.MILLISECONDS.sleep(2000);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
