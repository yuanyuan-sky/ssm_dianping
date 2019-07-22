package study_ssm_dianping.service;

import study_ssm_dianping.dto.AdDto;

import java.util.List;

public interface AdService {

    /**
     * 新增广告
     * @param adDto
     * @return  是否添加成功
     */
    boolean add(AdDto adDto);


    List<AdDto> adListInit(AdDto adDto);

    boolean remove(int id);

    /**
     *修改页面初始化
     * @param id   主键
     * @return
     */
    AdDto modifyInit(int id);

    /**
     * 修改
     * @param adDto
     * @return
     */
    boolean modify(AdDto adDto);
}
