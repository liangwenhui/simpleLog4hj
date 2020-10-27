package xyz.liangwh.simplelogger4j.core.handler;


import xyz.liangwh.simplelogger4j.core.LogFactory;
import xyz.liangwh.simplelogger4j.core.events.HandleEvent;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class WriteBufferHandler {
    private long index = 0;
    public WriteBufferHandler(){
        System.out.println("new WriteBufferHandler");
    }
    public void write(HandleEvent event, long sequence, boolean endOfBatch){
//        System.out.println(event.getFileName());
//        System.out.println(event.getMsg());

        RandomAccessFile raf =null;
        try {
            raf    = LogFactory.getFileRegostrant().addFile(event.getFileName());
            int length = event.getMsg().length();
            raf.seek(index);
            raf.write(event.getMsg().getBytes());
            index+=length;
        }catch (Exception e){

        }finally {
//            if(raf!=null){
//                try {
//                    raf.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
        }

    }

}
