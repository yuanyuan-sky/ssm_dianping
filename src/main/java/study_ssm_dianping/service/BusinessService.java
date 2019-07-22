package study_ssm_dianping.service;

import study_ssm_dianping.dto.BusinessDto;

import java.util.List;

public interface BusinessService {
    List<BusinessDto> searchByPage(BusinessDto dto);
}
