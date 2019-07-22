package study_ssm_dianping.dao;

import study_ssm_dianping.bean.Ad;

import java.util.List;

public interface AdDao {

    int insert(Ad ad);

    List<Ad> selectByPage(Ad condition);

    int remove(int id);

    Ad selectById(int id);

    int modify(Ad ad);
}
