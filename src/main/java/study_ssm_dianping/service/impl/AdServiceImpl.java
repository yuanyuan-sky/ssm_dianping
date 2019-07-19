package study_ssm_dianping.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import study_ssm_dianping.bean.Ad;
import study_ssm_dianping.dao.AdDao;
import study_ssm_dianping.dto.AdDto;
import study_ssm_dianping.service.AdService;

import java.io.File;
import java.io.IOException;
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

    @Override
    // TODO 可以改成获取失败原因
    public boolean add(AdDto adDto) {
        Ad ad = new Ad();
        ad.setTitle(adDto.getTitle());
        ad.setLink(adDto.getLink());
        ad.setWeight(adDto.getWeight());
        if (adDto.getImgFile() != null && adDto.getImgFile().getSize() > 0) {
            String fileName = System.currentTimeMillis()
                    + "_"
                    + adDto.getImgFile().getOriginalFilename();
            File file = new File(adImageSavePath
                    + fileName);
            File fileFolder = new File(adImageSavePath);
            if (!fileFolder.exists()) {
                fileFolder.mkdirs();
            }
            try {
                adDto.getImgFile().transferTo(file);
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
    public List<Ad> adListInit() {
        List<Ad> list = adDao.adListInit();
        return list;
    }

}
