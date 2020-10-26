package xyz.liangwh.simplelogger4j.core.loggers;

import xyz.liangwh.simplelogger4j.core.appenders.Appender;

import java.util.Map;

public interface AppendOwner {

    void addAppender(Appender appender);

    Appender getAppender(String name);

    Map<String,Appender> getAllAppender();

    void removeAppender(String name);

    void removeAllAppender();



}
