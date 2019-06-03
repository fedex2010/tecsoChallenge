package coop.tecso.examen.Utils;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class TimeUtils {
    public static Date getNowAsUtc(){
        OffsetDateTime utc = OffsetDateTime.now(ZoneOffset.UTC);
        return Date.from(utc.toInstant());
    }
}
