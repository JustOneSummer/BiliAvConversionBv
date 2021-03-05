package link.linxun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LinXun
 */
public class Main {
    private static final String[] table = "fZodR9XQDSUm21yCkr6zBqiveYah8bt4xsWpHnJE7jL5VG3guMTKNPAwcF".split("");
    private static final Map<String, Integer> tr = new HashMap<>();
    private static final int[] S = new int[]{11, 10, 3, 8, 4, 6};
    private static final long XOR = 177451812L;
    private static final long ADD = 8728348608L;

    static {
        for (int i = 0; i < 58; i++) {
            tr.put(table[i], i);
        }
    }

    public static void main(String[] args) {
        System.out.println(enc(170001));
        System.out.println(dec("BV17x411w7KC"));
    }

    /**
     * BV转AV(不带AV)
     *
     * @param bv 完整的BV号
     * @return BV号
     */
    public static long dec(String bv) {
        String[] x = bv.split("");
        double r = 0;
        for (int i = 0; i < 6; i++) {
            double pow = Math.pow(58, i);
            r += tr.get(x[S[i]]) * pow;
        }
        return (long) (r - ADD) ^ XOR;
    }

    /**
     * AV转BV
     *
     * @param av AV号(不带AV)
     * @return BV号(带BV)
     */
    public static String enc(long av) {
        av = (av ^ XOR) + ADD;
        List<String> r = new ArrayList<>();
        r.add("B");
        r.add("V");
        r.add("1");
        r.add("");
        r.add("");
        r.add("4");
        r.add("");
        r.add("1");
        r.add("");
        r.add("7");
        r.add("");
        r.add("");
        for (int i = 0; i < 6; i++) {
            double pow = Math.pow(58, i);
            r.add(S[i], table[(int) Math.floor(Math.floor(av / pow) % 58)]);
        }
        StringBuilder builder = new StringBuilder();
        r.forEach(builder::append);
        return builder.toString();
    }
}
