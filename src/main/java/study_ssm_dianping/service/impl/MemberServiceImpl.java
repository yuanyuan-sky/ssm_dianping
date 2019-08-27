package study_ssm_dianping.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study_ssm_dianping.bean.Member;
import study_ssm_dianping.cache.CodeCache;
import study_ssm_dianping.cache.TokenCache;
import study_ssm_dianping.dao.MemberDao;
import study_ssm_dianping.service.MemberService;
import study_ssm_dianping.util.MD5Util;

import java.util.List;

/**
 * Create By yuanyuan on 2019/8/8 11:17
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public boolean exists(Long phone) {
        Member member = new Member();
        member.setPhone(phone);
        List<Member> list = memberDao.getByPhone(member);
        return list != null && list.size() == 1;
    }

    @Override
    public boolean saveCode(Long phone, String code) {
        // TODO 在真实环境中，改成借助第三方实现
        CodeCache codeCache = CodeCache.getInstance();
        return codeCache.save(phone, MD5Util.getMD5(code));
    }

    @Override
    public boolean sendCode(Long username, String code) {

        return true;
    }

    @Override
    public String getCode(Long userName) {
        CodeCache instance = CodeCache.getInstance();
        return instance.getCode(userName);
    }

    @Override
    public void saveToken(String token, Long userName) {
        TokenCache cache = TokenCache.getInstance();
        cache.save(token, userName);
    }
}
