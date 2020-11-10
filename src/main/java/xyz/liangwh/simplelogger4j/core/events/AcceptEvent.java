package xyz.liangwh.simplelogger4j.core.events;

import lombok.Data;
import sun.misc.Contended;

import java.util.Date;

/**
 * 存放接受的日志
 * 经过格式化之后的日志
 */
@Data
@Contended
public class AcceptEvent {

    private Date time;
    //private String msg;
    private String format;
    private Object[] args;


}
