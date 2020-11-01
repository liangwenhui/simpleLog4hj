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
       final CountDownLatch latch = new CountDownLatch(2);
        //AppendRegistrant.getInstance().stop();
        for(int t=0;t<2;t++){
            new Thread(()->{
                for(int i=0;i<1000000;i++){
                    logger.info(i+"_abc  abc\n");
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
    @Test
    public void test2(){
        Logger logger = LoggerManager.getLogger(this.getClass());
        final CountDownLatch latch = new CountDownLatch(1);
        //AppendRegistrant.getInstance().stop();
        for(int t=0;t<1;t++){
            new Thread(()->{
                for(int i=0;i<100000;i++){
                    logger.info(i+"_abc  abc\n");
                }
                latch.countDown();
            }).start();
        }

        try {
            latch.await();
            TimeUnit.MILLISECONDS.sleep(200);
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
}
