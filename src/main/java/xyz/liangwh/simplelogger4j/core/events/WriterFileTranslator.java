package xyz.liangwh.simplelogger4j.core.events;

import com.lmax.disruptor.EventTranslatorOneArg;
import lombok.Data;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
@Data
public class WriterFileTranslator implements EventTranslatorOneArg<HandleEvent, ByteBuffer> {

    private String fileName;


    @Override
    public void translateTo(HandleEvent event, long sequence, ByteBuffer buffer) {
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
            event.setFileName(fileName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
