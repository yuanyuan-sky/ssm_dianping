package study_ssm_dianping.service;

import study_ssm_dianping.bean.Dic;
import study_ssm_dianping.dto.BusinessDto;
import study_ssm_dianping.dto.BusinessListDto;

import java.util.List;

public interface BusinessService {
    List<BusinessDto> searchByPage(BusinessDto dto);

    BusinessDto modifyInit(int id);

    List<Dic> getCitys();

    List<Dic> getCategors();

    boolean businessAdd(BusinessDto businessDto);

    boolean modify(BusinessDto businessDto);

    boolean delete(Long id);

    BusinessListDto searchByPageForApi(BusinessDto businessDto);
}
