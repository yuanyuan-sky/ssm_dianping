package study_ssm_dianping.constant;

public enum PageCodeEnum {
    ADD_SUCCESS(1000, "新增成功"),
    ADD_FAIL(1001, "新增失败"),
    DEL_SUCCESS(2000, "删除成功"),
    DEL_FAIL(2001, "删除失败"),
    MODIFY_SUCCESS(3000, "修改成功"),
    MODIFY_FAIL(3001, "修改失败");

    private Integer code;

    private String msg;

    public static final String KEY = "pageCode";

    PageCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static PageCodeEnum getPageCodeEnumByCode(Integer code) {
        for (PageCodeEnum item:PageCodeEnum.values()) {
            if (item.code.equals(code)) {
                return item;
            }
        }
        return null;
    }
}
