package ma.ensaf.support.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;

class ObjectUtilsTest {

    @Test
    void checkNotNullParameter_should_throw_IllegalArgumentException() {
        // given
        String param = null;
        // when
        // then
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> ObjectUtils.checkNotNullParameter(param, "param"));
    }

    @Test
    void checkNotNullParameter_should_not_throw_IllegalArgumentException() {
        // given
        String param = "";
        // when
        ObjectUtils.checkNotNullParameter(param, "param");
        // then do nothing
    }

    @Test
    void in_should_return_false_when_values_isEmpty() {
        // given
        int a = 12;
        // when
        boolean result = ObjectUtils.in(a);
        // then
        assertThat(result).isFalse();
    }
    
    @Test
    void in_should_return_false() {
        // given
        int a = 12;
        // when
        boolean result = ObjectUtils.in(a, 10);
        // then
        assertThat(result).isFalse();
    }

    @Test
    void in_should_return_true() {
        // given
        String a = "12";
        // when
        boolean result = ObjectUtils.in(a, "12", "13");
        // then
        assertThat(result).isTrue();
    }

}