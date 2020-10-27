package xyz.liangwh.simplelogger4j.core.loggers;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import xyz.liangwh.simplelogger4j.core.LogFactory;
import xyz.liangwh.simplelogger4j.core.QueueRegistrant;
import xyz.liangwh.simplelogger4j.core.appenders.Appender;
import xyz.liangwh.simplelogger4j.core.events.AcceptEvent;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Map;

public class FileLogger implements Logger{

    private  QueueRegistrant registrant ;

    public static void translate(AcceptEvent event, long sequence, ByteBuffer buffer)
    {
        Charset charset = null;
        CharsetDecoder decoder = null;
        CharBuffer charBuffer = null;
        try {
            charset = Charset.forName("UTF-8");
            decoder = charset.newDecoder();
            //用这个的话，只能输出来一次结果，第二次显示为空
            // charBuffer = decoder.decode(buffer);
            charBuffer = decoder.decode(buffer.asReadOnlyBuffer());
            event.setMsg(charBuffer.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void info(String msg) {
        try{
            Disruptor<AcceptEvent> accepter = registrant.getAccepter();
            ByteBuffer wrap = ByteBuffer.wrap(msg.getBytes("UTF-8"));
            RingBuffer<AcceptEvent> ringBuffer = accepter.getRingBuffer();
            ringBuffer.publishEvent(FileLogger::translate,wrap);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void init(){
        registrant = LogFactory.getQueueRegistrant();

    }

    public static FileLogger getLogger(){
        FileLogger worker = new FileLogger();
        worker.init();

        return worker;
    }


}
