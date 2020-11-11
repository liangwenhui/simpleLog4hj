package xyz.liangwh.simplelogger4j.core.loggers;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.EventTranslatorTwoArg;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import lombok.Data;
import xyz.liangwh.simplelogger4j.core.events.AcceptEvent;
import xyz.liangwh.simplelogger4j.core.events.AcceptMarkEvent;
import xyz.liangwh.simplelogger4j.core.events.StringTranslator;
import xyz.liangwh.simplelogger4j.core.queue.QueueFactory;
import xyz.liangwh.simplelogger4j.core.utils.FormatUtil;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 日志API提供者顶级接口
 */
public abstract class Logger {

    private String className;

    private  Disruptor<AcceptEvent> queue;

    private EventTranslatorTwoArg translator;

    protected ExecutorService service = new ThreadPoolExecutor(3, 10, 120L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(), new UpdateHeadwaterTheadFactory());

    public static class UpdateHeadwaterTheadFactory implements ThreadFactory {
        private static AtomicInteger times = new AtomicInteger();
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "log-" + times.incrementAndGet());
        }
    }
    /**
     * 输出一行日志
     * @param msg
     */
    public  void  info(String msg){
        //String fileterMsg = filter(msg);
        //sendToAccepter(msg);
    }

    /**
     * 格式化输出日志
     * @param format
     */
    public  void  fPrint(String format,Object...args){
        //assert format!=null:"format can not be null";
        //String.format(format,args)

        //service.execute(()->{
            sendToAccepter(format,args );
        //});
    }


    protected  void sendToAccepter(String format,Object... args){
        try{
            //Disruptor<AcceptEvent> queue = accepter.getQueue();
            //ByteBuffer wrap = ByteBuffer.wrap(msg.getBytes("UTF-8"));
            RingBuffer<AcceptEvent> ringBuffer = queue.getRingBuffer();
//            String msg = FormatUtil.format(format, args);
//
//            byte[][] getbbs = getbbs(msg);

            ringBuffer.publishEvent(translator,format,args);
            //ringBuffer.publishEvents(translator,getbbs);


        }catch (Exception e){
            e.printStackTrace();
        }
    };

//    private byte[][] getbbs(String msg) throws UnsupportedEncodingException {
//        ByteBuffer bb = ByteBuffer.wrap(msg.getBytes("UTF-8"));
//        ArrayList<byte[]> barr = new ArrayList<>();
//        byte[] b;
//        while(bb.position()<bb.limit()){
//            if((bb.limit()-bb.position())>=64){
//                b = new byte[64];
//            }else{
//                b = new byte[bb.limit()-bb.position()];
//            }
//            bb.get(b);
//            barr.add(b);
//        }
//        byte[][] bbb = new byte[barr.size()][];
//        for(int i=0;i<barr.size();i++){
//            bbb[i] = barr.get(i);
//        }
//        return bbb;
//    }

    protected  String filter(String msg){
        //默认不做处理
        return msg;
    };

    ;
    //void error(Object msg);
    //void warn(Object msg);


    public EventTranslatorTwoArg getTranslator() {
        return translator;
    }

    public void setTranslator(EventTranslatorTwoArg translator) {
        this.translator = translator;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Disruptor<AcceptEvent> getQueue() {
        return queue;
    }

    public void setQueue(Disruptor<AcceptEvent> queue) {
        this.queue = queue;
    }
}
