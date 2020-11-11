package xyz.liangwh.simplelogger4j.core.events;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.EventTranslatorThreeArg;
import com.lmax.disruptor.EventTranslatorTwoArg;
import xyz.liangwh.simplelogger4j.core.manage.LogFactory;
import xyz.liangwh.simplelogger4j.core.utils.FormatUtil;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class StringTranslator implements EventTranslatorTwoArg<AcceptMarkEvent,String ,Object[] > {

    public StringTranslator(){
        System.out.println("StringTranslator");
    }

    @Override
    public void translateTo(AcceptMarkEvent event, long sequence,String format,Object[] args) {
        try {
            AcceptEvent acceptEvent = new AcceptEvent();
            acceptEvent.setArgs(args);
            acceptEvent.setFormat(format);
            LogFactory.LOG_TABLE.put(sequence,acceptEvent);
            event.setId(sequence);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
