package xyz.liangwh.simplelogger4j.core.events;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.EventTranslatorThreeArg;
import com.lmax.disruptor.EventTranslatorTwoArg;
import xyz.liangwh.simplelogger4j.core.utils.FormatUtil;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class StringTranslator implements EventTranslatorTwoArg<AcceptEvent,String ,Object[] > {

    public StringTranslator(){
        System.out.println("StringTranslator");
    }

    @Override
    public void translateTo(AcceptEvent event, long sequence,String format,Object[] args) {
        try {
            //event.setMsg(String.format(format, (Object[])args));
            //String msg = FormatUtil.format(format, args);
           // event.setBytes(msg.getBytes());
            event.setFormat(format);
            event.setArgs(args);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
