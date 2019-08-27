package study_ssm_dianping.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * Create By yuanyuan on 2019/8/16 10:38
 */
public class TokenCache {
    private static TokenCache instance;

    private Map<String, Long> tokenMap;

    private TokenCache() {
        tokenMap = new HashMap<>();
    }

    public synchronized static TokenCache getInstance() {
        if (instance == null) {
            instance = new TokenCache();
        }
        return instance;
    }

    /**
     * 保存token与对应的手机号
     * @param token
     * @param phone 手机号
     */
    public void save(String token, Long phone) {
        tokenMap.put(token, phone);
    }

    /**
     * 根据token获取用户信息（手机号）
     * @param token
     * @return
     */

    public Long getPhone(String token) {
        return tokenMap.get(token);
    }
}
