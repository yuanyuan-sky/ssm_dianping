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

}
