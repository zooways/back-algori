package top.zoowayss.bank.utils;

/**
 * @Description: 数组算数工具类
 * @Author: <a href="https://github.com/zooways">zooways</a>
 * @Date: 2023/7/24 10:15
 */

public class ArraysMathUtil {
    /**
     * 判断数组 a 中的每个元素是否都小于等于数组 b 中的每个元素
     * @param a
     * @param b
     * @return
     */
    public static boolean lessThen(int[] a, int[] b) {
        if (a.length != b.length)
            throw new RuntimeException("a.length != b.length");
        for (int i = 0; i < a.length; i++) {
            if (a[i] > b[i])
                return false;
        }
        return true;
    }

    /**
     * 两个数组相加，结果放在 a 中
     * @param a
     * @param b
     */
    public static void add(int[] a, int[] b) {
        if (a.length != b.length)
            throw new RuntimeException("a.length != b.length");
        for (int i = 0; i < a.length; i++) {
            a[i] += b[i];
        }
    }

    /**
     * 两个数组相减，结果放在 a 中
     * @param a
     * @param b
     */
    public static void sub(int[] a, int[] b) {
        if (a.length != b.length)
            throw new RuntimeException("a.length != b.length");
        for (int i = 0; i < a.length; i++) {
            a[i] -= b[i];
        }
    }
}
