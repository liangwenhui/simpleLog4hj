package xyz.liangwh.simplelogger4j.core.events;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.EventTranslatorOneArg;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class StringTranslator implements EventTranslatorOneArg<AcceptEvent, ByteBuffer> {

    public StringTranslator(){
        System.out.println("StringTranslator");
    }

    @Override
    public void translateTo(AcceptEvent event, long sequence, ByteBuffer buffer) {
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
}
