package study_ssm_dianping.service;

public interface MemberService {
    boolean exists(Long username);

    boolean saveCode(Long username, String code);

    boolean sendCode(Long username, String code);
}
