package xyz.liangwh.simplelogger4j.core.manage;

import xyz.liangwh.simplelogger4j.core.appenders.impl.FileAppender;
import xyz.liangwh.simplelogger4j.core.events.AcceptEvent;
import xyz.liangwh.simplelogger4j.core.events.WriterFileTranslator;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class LogFactory {

    private volatile static FileRegostrant fileRegostrant = new FileRegostrant();
//    private static Object o = new Object();
    private static volatile AppendRegistrant appendRegistrant = new AppendRegistrant();
    private static volatile QueueRegistrant queueRegistrant = new QueueRegistrant();

    public static final HashMap<Long, AcceptEvent> LOG_TABLE = new HashMap<>(1024 * 32);


    static{
        FileAppender fileAppender = new FileAppender();
        appendRegistrant.regist(fileAppender);
        queueRegistrant.init();
        WriterFileTranslator writerFileTranslator = new WriterFileTranslator();
        writerFileTranslator.setFileName("./info.log");
        fileAppender.setTranslator(writerFileTranslator);
        fileAppender.setWriter(queueRegistrant.getWriter());


    }


   public static AppendRegistrant getAppendRegistrant(){
//       if(appendRegistrant==null){
//           synchronized (o){
//               if(appendRegistrant==null){
//                   appendRegistrant = new AppendRegistrant();
//               }
//           }
//       }
       return appendRegistrant;
    }

    public static QueueRegistrant getQueueRegistrant(){
//        if(queueRegistrant==null){
//            synchronized (o){
//                if(queueRegistrant==null){
//                    queueRegistrant = new QueueRegistrant();
////                       instance.init();
//                }
//            }
//        }
        return queueRegistrant;
    }

    public static FileRegostrant getFileRegostrant(){
//        if(fileRegostrant==null){
//            synchronized (o){
//                if(fileRegostrant==null){
//                    fileRegostrant = new FileRegostrant();
//                }
//            }
//        }
        return fileRegostrant;
    }
}
