package xyz.liangwh.simplelogger4j.core.loggers;

/**
 * 日志API提供者顶级接口
 */
public interface Logger {

    void info(Object msg);
    void error(Object msg);
    void warn(Object msg);


}
