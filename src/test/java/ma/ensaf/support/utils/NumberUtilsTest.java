package ma.ensaf.support.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NumberUtilsTest {

	@Test
	void fact_should_return_1() {
        // given
        long a = 0L;
        // when
        long result = NumberUtils.fact(a);
        // then
        assertThat(result).isEqualTo(1L);
	}

	@Test
	void fact_should_return_120() {
        // given
        long in = 5L;
        // when
        long result = NumberUtils.fact(in);
        // then
        assertThat(result).isEqualTo(120L);
	}

}
