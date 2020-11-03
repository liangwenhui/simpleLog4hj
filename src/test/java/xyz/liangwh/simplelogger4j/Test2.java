package xyz.liangwh.simplelogger4j;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;
import lombok.Data;
import org.junit.Test;
import sun.misc.Contended;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class Test2 {

    public static void stdoutHandle(MsgEvent event, long sequence, boolean endOfBatch){
        System.out.println("accept:"+event.getMsg());
    }
    public static void translate(MsgEvent event, long sequence, ByteBuffer buffer)
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
    @Test
    public void main() throws Exception {
        //log message
        String msg = "log message";
        //-> accept
        Disruptor<MsgEvent> msgEventDisruptor = startAcceptQ();
        ByteBuffer wrap = ByteBuffer.wrap(msg.getBytes("UTF-8"));
        RingBuffer<MsgEvent> ringBuffer = msgEventDisruptor.getRingBuffer();
        for(int i=0;i<20;i++){
            ringBuffer.publishEvent(Test2::translate,wrap);
        }

    }

    private Disruptor<MsgEvent> startAcceptQ(){
        int bufferSize = 16;
        Disruptor<MsgEvent> disruptor = new Disruptor<>(MsgEvent::new, bufferSize, DaemonThreadFactory.INSTANCE);
        //消息处理
        disruptor.handleEventsWith(Test2::stdoutHandle);
        disruptor.start();
        return disruptor;
//        RingBuffer<MsgEvent> ringBuffer = disruptor.getRingBuffer();
//        ByteBuffer bb = ByteBuffer.allocate(8);
//        for (long l = 0; true; l++)
//        {
//            bb.putLong(0, l);
//            ringBuffer.publishEvent(Main::translate, bb);
//            Thread.sleep(1000);
//        }
    }

}
@Data
@Contended
class MsgEvent{
    private String msg ;
}
