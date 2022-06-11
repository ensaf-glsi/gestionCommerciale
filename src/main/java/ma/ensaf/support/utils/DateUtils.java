package ma.ensaf.support.utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

public class DateUtils {

    private DateUtils() {
    }

    /**
     *
     * @param instant : Instant object to convert to Date
     * @return converted instant if not null otherwise null
     */
    public static Date toDate(Instant instant) {
        return Objects.isNull(instant) ? null : Date.from(instant);
    }

    /**
     *
     * @param date : date
     * @param days : number of days it can be negative
     * @return date + days
     */
    public static Date plusDays(Date date, int days) {
        ObjectUtils.checkNotNullParameter(date, "date");
        return toDate(date.toInstant().plus(days, ChronoUnit.DAYS));
    }

    /**
     *
     * @param days : number of days it can be negative
     * @return now + days
     */
    public static Date nowDatePlusDays(int days) {
        return plusDays(new Date(), days);
    }
}
