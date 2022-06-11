package ma.ensaf.support.utils;

public class NumberUtils {

    public static <T> boolean equals(T a, T b) {
        if (a == null) return b == null;
        return a.equals(b);
    }
    
    public static long fact(Long n) {
    	ObjectUtils.checkNotNullParameter(n, "n");
    	if (n <= 1) return 1;
    	return n * fact(n - 1);
    }
    
    // public static boolean equals(Double a, Double b) {
    //     if (a == null) return b == null;
    //     return a.equals(b);
    // }
    // public static boolean equals(Long a, Long b) {
    //     if (a == null) return b == null;
    //     return a.equals(b);
    // }

}
