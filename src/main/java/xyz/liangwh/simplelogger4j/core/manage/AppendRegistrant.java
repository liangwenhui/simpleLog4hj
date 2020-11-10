package xyz.liangwh.simplelogger4j.core.manage;

import xyz.liangwh.simplelogger4j.core.appenders.Appender;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AppendRegistrant {

    private static volatile Object o = new Object();
    private volatile List<Appender> appenders;
    private Thread rangDoAppendThread ;
    private volatile boolean  run = true;
    private long rangMs = 100;

    public AppendRegistrant(){
        this.appenders = new ArrayList<>();
        rangDoAppendThread = new Thread(()->{
            while (run){
                if(appenders.size()==0){
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for(Appender appender :appenders){
                    appender.doAppend("".getBytes());
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(rangMs);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        },"rangDoAppendThread");
        rangDoAppendThread.start();
    }

   public Appender getAppend(Class<? extends Appender> clazz){
       for(Appender appender :appenders){
           if(appender.getClass().equals(clazz)){
               return appender;
           }
       }
       return null;
    }

    public String getStatus(){
        if(run){
            return "running";
        }
        return "no running";
    }

    public synchronized void stop(){
            run = false;
    }

    public synchronized void start(){
            run = run;
    }

    public void regist(Appender appender){
        appenders.add(appender);
    }





}
