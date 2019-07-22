package study_ssm_dianping.bean;

/**
 * Create By yuanyuan on 2019/7/19 10:21
 */
public class BaseBean {

    private Page page;

    public BaseBean() {
        this.page = new Page();
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
