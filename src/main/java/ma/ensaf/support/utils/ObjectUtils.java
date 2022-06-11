package ma.ensaf.support.utils;

import java.util.Objects;

public class ObjectUtils {

    private ObjectUtils() {
    }

    /**
     * Throw IllegalArgumentException if object is null, otherwise donothing
     *
     * @param object        : an object to test
     * @param parameterName : the name of the parameter
     */
    public static void checkNotNullParameter(Object object, String parameterName) {
    	if (Objects.isNull(object)) {
    		throw new IllegalArgumentException("The parameter " + parameterName + " should not be null");
        }
    }

    /**
     * check if a value exists in a list
     * 
     * @param value  : a value
     * @param values : list of values
     * @return true if value exists in values and false otherwise
     */
    public static boolean in(Object value, Object... values) {
    	for (Object v : values) {
            if (Objects.equals(value, v)) {
                return true;
            }
        }
        return false;
    }

}
