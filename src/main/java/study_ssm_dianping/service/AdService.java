package study_ssm_dianping.service;

import study_ssm_dianping.bean.Ad;
import study_ssm_dianping.dto.AdDto;

import java.util.List;

public interface AdService {

    /**
     * 新增广告
     * @param adDto
     * @return  是否添加成功
     */
    boolean add(AdDto adDto);

    List<Ad> adListInit();
}
