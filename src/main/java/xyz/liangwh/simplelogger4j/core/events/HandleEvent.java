package xyz.liangwh.simplelogger4j.core.events;

import lombok.Data;

@Data
public class HandleEvent {

    private String msg;
    private String fileName;

    private long start;
    private long end;

}
