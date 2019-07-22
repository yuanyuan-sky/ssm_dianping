package study_ssm_dianping.bean;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Create By yuanyuan on 2019/7/15 13:33
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Business extends BaseBean {
    private Long id;

    private String imgFileName;

    private String title;

    private String subtitle;

    private Double price;

    private Integer distance;

    private Integer mumber;

    private String desc;

    private String city;

    private String category;

    private Long starTotalNum;

    private Long commentTotalNum;

    private Dic cityDic;

    private Dic categoryDic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgFileName() {
        return imgFileName;
    }

    public void setImgFileName(String imgFileName) {
        this.imgFileName = imgFileName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getMumber() {
        return mumber;
    }

    public void setMumber(Integer mumber) {
        this.mumber = mumber;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getStarTotalNum() {
        return starTotalNum;
    }

    public void setStarTotalNum(Long starTotalNum) {
        this.starTotalNum = starTotalNum;
    }

    public Long getCommentTotalNum() {
        return commentTotalNum;
    }

    public void setCommentTotalNum(Long commentTotalNum) {
        this.commentTotalNum = commentTotalNum;
    }

    public Dic getCityDic() {
        return cityDic;
    }

    public void setCityDic(Dic cityDic) {
        this.cityDic = cityDic;
    }

    public Dic getCategoryDic() {
        return categoryDic;
    }

    public void setCategoryDic(Dic categoryDic) {
        this.categoryDic = categoryDic;
    }
}
