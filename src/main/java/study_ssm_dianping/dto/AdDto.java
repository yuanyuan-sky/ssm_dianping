package study_ssm_dianping.dto;

import org.springframework.web.multipart.MultipartFile;
import study_ssm_dianping.bean.Ad;

/**
 * Create By yuanyuan on 2019/7/16 11:11
 */
public class AdDto extends Ad {
    private String img;

    private MultipartFile imgFile;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public MultipartFile getImgFile() {
        return imgFile;
    }

    public void setImgFile(MultipartFile imgFile) {
        this.imgFile = imgFile;
    }
}
