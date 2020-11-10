package xyz.liangwh.simplelogger4j.core.events;

import lombok.Data;
import sun.misc.Contended;

@Data
@Contended
public class HandleEvent {

    private byte[] msg;
    private String fileName;

    private long start;
    private long size;

}
