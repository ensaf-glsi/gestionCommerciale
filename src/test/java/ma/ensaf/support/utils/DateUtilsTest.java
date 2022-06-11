package ma.ensaf.support.utils;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.Instant;
import java.util.Date;

import org.junit.jupiter.api.Test;

class DateUtilsTest {

    @Test
    void toDate_should_return_null() {
        // given
        // when
        Date result = DateUtils.toDate(null);
        // then
        assertThat(result).isNull();
    }

    @Test
    void plusDays_should_add_days() {
        // given
        Date input = DateUtils.toDate(Instant.parse("2022-05-20T00:00:00.0Z"));
        Date output = DateUtils.toDate(Instant.parse("2022-05-23T00:00:00.0Z"));
        // when
        Date result = DateUtils.plusDays(input, 3);
        // then
        assertThat(result).isEqualTo(output);
    }

    @Test
    void plusDays_should_subtract_days() {
        // given
        Date input = DateUtils.toDate(Instant.parse("2022-05-20T00:00:00.0Z"));
        Date output = DateUtils.toDate(Instant.parse("2022-05-15T00:00:00.0Z"));
        // when
        Date result = DateUtils.plusDays(input, -5);
        // then
        assertThat(result).isEqualTo(output);
    }

    @Test
    void nowDatePlusDays_should_add_days() {
        // given
        Date input = new Date();
        long output = DateUtils.plusDays(input, 2).getTime();
        // when
        Date result = DateUtils.nowDatePlusDays(2);
        // then
        assertThat(result.getTime()).isBetween(output - 1000, output + 1000);
    }

}