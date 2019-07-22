package study_ssm_dianping.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import study_ssm_dianping.bean.Business;
import study_ssm_dianping.dao.BusinessDao;
import study_ssm_dianping.dto.BusinessDto;
import study_ssm_dianping.service.BusinessService;

import java.util.ArrayList;
import java.util.List;

/**
 * Create By yuanyuan on 2019/7/22 11:29
 */
@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessDao businessDao;

    @Override
    public List<BusinessDto> searchByPage(BusinessDto dto) {

        Business condition = new Business();
        BeanUtils.copyProperties(dto, condition);
        List<Business> list = businessDao.searchByPage(condition);

        List<BusinessDto> result = new ArrayList<>();
        for (Business business : list) {
            BusinessDto businessDtoTemp = new BusinessDto();
            result.add(businessDtoTemp);
            BeanUtils.copyProperties(business, businessDtoTemp);
        }
        return result;
    }

}
