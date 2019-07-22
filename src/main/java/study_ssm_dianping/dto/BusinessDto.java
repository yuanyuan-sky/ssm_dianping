package study_ssm_dianping.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.web.multipart.MultipartFile;
import study_ssm_dianping.bean.Business;

/**
 * Create By yuanyuan on 2019/7/22 11:18
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessDto extends Business {

    private String img;

    private MultipartFile imgFile;

    private String keyword;

    private Integer mumber;

    private Integer star;

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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public Integer getMumber() {
        return mumber;
    }

    @Override
    public void setMumber(Integer mumber) {
        this.mumber = mumber;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }
}
