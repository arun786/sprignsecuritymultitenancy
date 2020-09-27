package com.arun.springsecuritymultitenancy.mapper;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * @author arun on 9/26/20
 */

@Component
public class DateMapper {

    public OffsetDateTime asOffsetDateTime(Timestamp ts) {
        if (ts != null) {

            LocalDateTime localDateTime = ts.toLocalDateTime();
            return OffsetDateTime.of(localDateTime.getYear(), localDateTime.getMonthValue()
                    , localDateTime.getDayOfMonth(), localDateTime.getHour(), localDateTime.getMinute()
                    , localDateTime.getSecond(), localDateTime.getNano(), ZoneOffset.UTC);
        }

        return null;
    }

    public Timestamp asTimeStamp(OffsetDateTime offsetDateTime) {
        if (offsetDateTime != null) {
            return Timestamp.valueOf(offsetDateTime.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
        }

        return null;
    }

}
