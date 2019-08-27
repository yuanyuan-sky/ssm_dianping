package study_ssm_dianping.dao;

import study_ssm_dianping.bean.Member;

import java.util.List;

public interface MemberDao {
    List<Member> getByPhone(Member member);
}
