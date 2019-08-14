package study_ssm_dianping.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import study_ssm_dianping.constant.ApiCodeEnum;

/**
 * Create By yuanyuan on 2019/8/8 11:07
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiCodeDto {

    private Integer errno;

    private String msg;

    private String token;

    private String code;

    public ApiCodeDto() {

    }

    public ApiCodeDto(Integer errno, String msg) {
        this.errno = errno;
        this.msg = msg;
    }
    public ApiCodeDto(ApiCodeEnum apiCodeEnum) {
        this.errno = apiCodeEnum.getErrno();
        this.msg = apiCodeEnum.getMsg();
    }

    public Integer getErrno() {
        return errno;
    }

    public void setErrno(Integer errno) {
        this.errno = errno;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
