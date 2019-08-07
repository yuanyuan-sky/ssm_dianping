package study_ssm_dianping.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import study_ssm_dianping.bean.Business;
import study_ssm_dianping.bean.Dic;
import study_ssm_dianping.bean.Page;
import study_ssm_dianping.constant.CategoryConst;
import study_ssm_dianping.constant.DicTypeConst;
import study_ssm_dianping.dao.BusinessDao;
import study_ssm_dianping.dto.BusinessDto;
import study_ssm_dianping.dto.BusinessListDto;
import study_ssm_dianping.service.BusinessService;
import study_ssm_dianping.util.CommonUtil;
import study_ssm_dianping.util.FileUtil;

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
            String fileName = FileUtil.saveImg(businessDto.getImgFile(), businessImageSavePath);
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
                fileName = FileUtil.saveImg(multipartFile, businessImageSavePath);
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

    @Override
    public BusinessListDto searchByPageForApi(BusinessDto businessDto) {
        BusinessListDto result = new BusinessListDto();
        //组织查询条件
        Business businessForSelect = new Business();
        BeanUtils.copyProperties(businessDto, businessForSelect);
        //当关键字不为空时，将关键字的值分别设置到标题、副标题、描述中
        //TODO 改进做法：全文检索
        String keyWord = businessDto.getKeyword();
        if (!CommonUtil.isEmpty(keyWord)) {
            businessForSelect.setTitle(keyWord);
            businessForSelect.setSubtitle(keyWord);
            businessForSelect.setDesc(keyWord);
        }
        //当类别为全部(null)时，需要将类别清空，不作为过滤条件
        if (businessDto.getCategory() != null && CategoryConst.ALL.equals(businessDto.getCategory())) {
            businessForSelect.setCategory(null);
        }
        //前端app页码从0开始计算，这里需要+1
        int currentPage = businessForSelect.getPage().getCurrentPage();
        businessForSelect.getPage().setCurrentPage(currentPage + 1);

        List<Business> list = businessDao.selectLikeByPage(businessForSelect);

        //经过查询后根据page对象设置hasMore
        Page page = businessForSelect.getPage();
        result.setHasMore(page.getCurrentPage() < page.getTotalPage());

        //对查询结果进行格式化
        for (Business business : list) {
            BusinessDto businessDtoTemp = new BusinessDto();
            result.getData().add(businessDtoTemp);
            BeanUtils.copyProperties(business, businessDtoTemp);
            businessDtoTemp.setImg(businessImageUrl + business.getImgFileName());
            //为兼容前端的mumber属性
            businessDtoTemp.setMumber(business.getNumber());
            businessDtoTemp.setStar(this.getStart(business));
        }
        return result;
    }

    private Integer getStart(Business business) {
        if (business.getStarTotalNum() != null && business.getCommentTotalNum() != null && business.getCommentTotalNum() != 0) {
            return (int) (business.getStarTotalNum() / business.getCommentTotalNum());
        } else {
            return 0;
        }
    }


}
