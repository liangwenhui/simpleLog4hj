package xyz.liangwh.simplelogger4j.core.handler;


import xyz.liangwh.simplelogger4j.core.manage.LogFactory;
import xyz.liangwh.simplelogger4j.core.events.HandleEvent;

import java.io.RandomAccessFile;

public class WriteBufferHandler {
    private long index = 0;
    public WriteBufferHandler(){
        System.out.println("new WriteBufferHandler");
    }
    public void write(HandleEvent event, long sequence, boolean endOfBatch){

        RandomAccessFile raf =null;
        try {
            raf    = LogFactory.getFileRegostrant().addFile(event.getFileName());
            int length = event.getMsg().length();
            raf.seek(index);
            raf.write(event.getMsg().getBytes());
            index+=length;
        }catch (Exception e){

        }finally {
        }

    }

}
