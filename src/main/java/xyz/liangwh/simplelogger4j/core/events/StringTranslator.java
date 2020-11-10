package xyz.liangwh.simplelogger4j.core.events;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.EventTranslatorThreeArg;
import com.lmax.disruptor.EventTranslatorTwoArg;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class StringTranslator implements EventTranslatorTwoArg<AcceptEvent, String,Object[]> {

    public StringTranslator(){
        System.out.println("StringTranslator");
    }

    @Override
    public void translateTo(AcceptEvent event, long sequence, String format,Object[] args) {
        try {
            //event.setMsg(String.format(format, (Object[])args));
            event.setFormat(format);
            event.setArgs(args);
//            event.setFormat("%s");
//            event.setArgs(new Object[]{"1"});
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
