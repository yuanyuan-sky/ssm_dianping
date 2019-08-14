package study_ssm_dianping.util;

/**
 * 通用工具类
 * Create By yuanyuan on 2019/8/7 17:10
 */
public class CommonUtil {

    /**
     * 方法描述：判断一个字符串是否为null或空字符串
     * @param str 需要判断的字符串
     * @return 空返回true，非空返回false
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str.trim())) {
            return true;
        }
        return false;
    }

    /**
     * 生成指定位数的随机数
     * @param n 位数
     * @return 随机整数
     */
    public static int random(int n) {
        int m = (int) ((Math.random() * 9 + 1) * Math.pow(10, n - 1));
        return m;
    }

}
