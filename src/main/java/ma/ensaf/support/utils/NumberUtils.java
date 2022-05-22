package ma.ensaf.support.utils;

public class NumberUtils {

    public static <T> boolean equals(T a, T b) {
        if (a == null) return b == null;
        return a.equals(b);
    }
    // public static boolean equals(Double a, Double b) {
    //     if (a == null) return b == null;
    //     return a.equals(b);
    // }
    // public static boolean equals(Long a, Long b) {
    //     if (a == null) return b == null;
    //     return a.equals(b);
    // }

    public static void main(String[] args) {
        Integer x = null, y = null;
        System.out.println(equals(x, y));
        System.out.println(equals(1, 2));
        System.out.println(equals(1.2, 1.2));
        System.out.println(equals(1L, 1L));
    }
    
}
