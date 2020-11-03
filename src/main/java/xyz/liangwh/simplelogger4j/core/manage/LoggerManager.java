package xyz.liangwh.simplelogger4j.core.manage;

import xyz.liangwh.simplelogger4j.core.events.StringTranslator;
import xyz.liangwh.simplelogger4j.core.loggers.FileLogger;
import xyz.liangwh.simplelogger4j.core.loggers.Logger;

/**
 * logger管理器
 * 提供对logger的获取
 */
public class LoggerManager {

   private static StringTranslator translator = new StringTranslator();

   private static QueueRegistrant queueRegistrant = LogFactory.getQueueRegistrant();



    public static Logger getLogger(Class clazz){
        //目前只支持文件logger，后续改成根据配置
        FileLogger fileLogger = new FileLogger();
        fileLogger.setClassName(clazz.getName());
        fileLogger.setQueue(queueRegistrant.getAccepter());
        fileLogger.setTranslator(translator);
        return fileLogger;
    }
}
