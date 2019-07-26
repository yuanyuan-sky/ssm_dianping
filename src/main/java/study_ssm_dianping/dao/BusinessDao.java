package study_ssm_dianping.dao;

import study_ssm_dianping.bean.Business;
import study_ssm_dianping.bean.Dic;

import java.util.List;

public interface BusinessDao {
    List<Business> searchByPage(Business business);

    List<Dic> getCitys(String city);

    List<Dic> getCategorys(String category);

    int businessAdd(Business business);

    Business selectById(int id);

    int modify(Business business);

    int delete(Long id);
}
