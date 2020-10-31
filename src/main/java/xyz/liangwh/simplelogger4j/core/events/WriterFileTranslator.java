package xyz.liangwh.simplelogger4j.core.events;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.EventTranslatorThreeArg;
import lombok.Data;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
@Data
public class WriterFileTranslator implements EventTranslatorThreeArg<HandleEvent, ByteBuffer,Long,Long> {

    private String fileName;


    @Override
    public void translateTo(HandleEvent event, long sequence, ByteBuffer buffer,Long start,Long end) {
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
            event.setStart(start);
            event.setEnd(end);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
