package xyz.liangwh.simplelogger4j.core.events;

import lombok.Data;
import sun.misc.Contended;

@Data
@Contended
public class AcceptMarkEvent {
    long id;
}
