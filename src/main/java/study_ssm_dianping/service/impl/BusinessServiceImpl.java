package study_ssm_dianping.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import study_ssm_dianping.bean.Business;
import study_ssm_dianping.bean.Dic;
import study_ssm_dianping.constant.DicTypeConst;
import study_ssm_dianping.dao.BusinessDao;
import study_ssm_dianping.dto.BusinessDto;
import study_ssm_dianping.service.BusinessService;
import study_ssm_dianping.util.MyUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Create By yuanyuan on 2019/7/22 11:29
 */
@Service
public class BusinessServiceImpl implements BusinessService {


    @Value("${businessImage.savePath}")
    private String businessImageSavePath;

    @Value("${businessImage.url}")
    private String businessImageUrl;

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

    @Override
    public List<Dic> getCitys() {
        List<Dic> citys = businessDao.getCitys(DicTypeConst.CITY);
        return citys;
    }

    @Override
    public List<Dic> getCategors() {
        List<Dic> categorys = businessDao.getCategorys(DicTypeConst.CATEGORY);
        return categorys;
    }

    @Override
    public boolean businessAdd(BusinessDto businessDto) {
        Business business = new Business();
        BeanUtils.copyProperties(businessDto, business);
        try {
            String fileName = MyUtil.saveImg(businessDto.getImgFile(), businessImageSavePath);
            business.setImgFileName(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int affectCount = businessDao.businessAdd(business);
        if (affectCount != 1) {
            return false;
        }
        return true;
    }

    @Override
    public boolean modify(BusinessDto businessDto) {
        Business business = new Business();
        BeanUtils.copyProperties(businessDto, business);
        MultipartFile multipartFile = businessDto.getImgFile();
        String fileName = "";
        if (multipartFile != null && multipartFile.getSize() > 0) {
            try {
                fileName = MyUtil.saveImg(multipartFile, businessImageSavePath);
                business.setImgFileName(fileName);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        int n = businessDao.modify(business);
        if (n != 1) {
            return false;
        }
        if (!fileName.equals("")) {
            File file = new File(businessImageSavePath + businessDto.getImgFileName());
            file.delete();
        }
        return true;
    }

    @Override
    public boolean delete(Long id) {
        int n = businessDao.delete(id);
        if (n != 1) {
            return false;
        }
        return true;
    }

    @Override
    public BusinessDto modifyInit(int id) {
        Business business = businessDao.selectById(id);
        BusinessDto businessDto = new BusinessDto();
        BeanUtils.copyProperties(business, businessDto);
        businessDto.setImg(businessImageUrl + business.getImgFileName());
        return businessDto;
    }

}
