package xyz.liangwh.simplelogger4j.core.handler;

import com.lmax.disruptor.WorkHandler;
import xyz.liangwh.simplelogger4j.core.events.HandleEvent;
import xyz.liangwh.simplelogger4j.core.manage.LogFactory;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class WriteBufferHandleWorker implements WorkHandler<HandleEvent> {


    public WriteBufferHandleWorker(){
        System.out.println("new WriteBufferHandleWorker");
    }


    @Override
    public void onEvent(HandleEvent event) throws Exception {
//        System.out.println(Thread.currentThread().getName()+":: write");
        RandomAccessFile raf =null;
        try {
            raf    = LogFactory.getFileRegostrant().addFile(event.getFileName());
            FileChannel channel = raf.getChannel();
            MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, event.getStart(), event.getSize());
            map.put(event.getMsg());
        }catch (Exception e){
            System.err.println("写日志文件发生异常");
            e.printStackTrace();
        }finally {
        }
    }
}
