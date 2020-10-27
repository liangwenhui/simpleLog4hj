package xyz.liangwh.simplelogger4j.core;

public class LogFactory {

    private volatile static FileRegostrant fileRegostrant;
    private static Object o = new Object();
    private static volatile AppendRegistrant appendRegistrant;
    private static volatile QueueRegistrant queueRegistrant;



   public static AppendRegistrant getAppendRegistrant(){
       if(appendRegistrant==null){
           synchronized (o){
               if(appendRegistrant==null){
                   appendRegistrant = new AppendRegistrant();
               }
           }
       }
       return appendRegistrant;
    }

    public static QueueRegistrant getQueueRegistrant(){
        if(queueRegistrant==null){
            synchronized (o){
                if(queueRegistrant==null){
                    queueRegistrant = new QueueRegistrant();
//                       instance.init();
                }
            }
        }
        return queueRegistrant;
    }

    public static FileRegostrant getFileRegostrant(){
        if(fileRegostrant==null){
            synchronized (o){
                if(fileRegostrant==null){
                    fileRegostrant = new FileRegostrant();
                }
            }
        }
        return fileRegostrant;
    }
}
