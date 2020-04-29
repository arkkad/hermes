package maestro.util;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static LocalDateTime getLocalDateTimeUtc() {
        return LocalDateTime.now(Clock.systemUTC());
    }

    public static LocalDateTime getLocalDateTimeUtcOffset(int utcOffset) {
        int zoneId = utcOffset / 60;
        ZoneOffset zoneOffset;
        if (zoneId > 0) {
            zoneOffset = ZoneOffset.of("+" + zoneId);
        } else {
            zoneOffset = ZoneOffset.of(String.valueOf(zoneId));
        }

        return LocalDateTime.now(zoneOffset);
    }
}
