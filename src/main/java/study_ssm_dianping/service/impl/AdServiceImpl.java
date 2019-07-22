package study_ssm_dianping.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import study_ssm_dianping.bean.Ad;
import study_ssm_dianping.dao.AdDao;
import study_ssm_dianping.dto.AdDto;
import study_ssm_dianping.service.AdService;
import study_ssm_dianping.util.MyUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Create By yuanyuan on 2019/7/16 12:04
 */
@Service
public class AdServiceImpl implements AdService {

    @Autowired
    private AdDao adDao;

    @Value("${adImage.savePath}")
    private String adImageSavePath;

    @Value("${adImage.url}")
    private String adImageUrl;

    @Override
    // TODO 可以改成获取失败原因
    public boolean add(AdDto adDto) {
        Ad ad = new Ad();
        ad.setTitle(adDto.getTitle());
        ad.setLink(adDto.getLink());
        ad.setWeight(adDto.getWeight());
        if (adDto.getImgFile() != null && adDto.getImgFile().getSize() > 0) {
            try {
                String fileName =  MyUtil.saveImg(adDto.getImgFile(), adImageSavePath);
                ad.setImgFileName(fileName);
                adDao.insert(ad);
                return true;
            } catch (IOException e) {
                //TODO 需要添加日志
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public List<AdDto> adListInit(AdDto adDto) {
        List<AdDto> result = new ArrayList<>();
        Ad condition = new Ad();
        BeanUtils.copyProperties(adDto, condition);
        List<Ad> adList = adDao.selectByPage(condition);
        for (Ad ad : adList) {
            AdDto adDtoTemp = new AdDto();
            result.add(adDtoTemp);
            adDtoTemp.setImg(adImageUrl + ad.getImgFileName());
            BeanUtils.copyProperties(ad, adDtoTemp);
        }
        return result;
    }

    @Override
    public boolean remove(int id) {
        int n = adDao.remove(id);
        if (n > 0) {
            return true;
        }
        return false;
    }

    @Override
    public AdDto modifyInit(int id) {
        Ad ad = adDao.selectById(id);
        AdDto adDto = new AdDto();
        BeanUtils.copyProperties(ad, adDto);
        adDto.setImg(adImageUrl + ad.getImgFileName());
        return adDto;
    }

    @Override
    public boolean modify(AdDto adDto) {
        Ad ad = new Ad();
        BeanUtils.copyProperties(adDto, ad);
        String imgName = "";
        if (adDto.getImgFile() != null && adDto.getImgFile().getSize() > 0) {
            try {
                imgName = MyUtil.saveImg(adDto.getImgFile(), adImageSavePath);
                ad.setImgFileName(imgName);
                adDto.setImg(adImageUrl+imgName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        int n = adDao.modify(ad);
        if (n != 1) {
            return false;
        }
        if (!"".equals(imgName)) {
            File oldImg = new File(adImageSavePath + adDto.getImgFileName());
            //deleteOnExit()会在虚拟机停止之后才会把文件删除，也就是说，如果只有关掉tomcat的时候，文件才会删掉
            //oldImg.deleteOnExit();
            oldImg.delete();
        }
        return true;
    }

}
