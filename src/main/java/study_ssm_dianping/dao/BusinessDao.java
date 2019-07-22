package study_ssm_dianping.dao;

import study_ssm_dianping.bean.Business;

import java.util.List;

public interface BusinessDao {
    List<Business> searchByPage(Business business);
}
