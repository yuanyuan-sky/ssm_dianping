package study_ssm_dianping.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import study_ssm_dianping.constant.ApiCodeEnum;
import study_ssm_dianping.dto.AdDto;
import study_ssm_dianping.dto.ApiCodeDto;
import study_ssm_dianping.dto.BusinessDto;
import study_ssm_dianping.dto.BusinessListDto;
import study_ssm_dianping.service.AdService;
import study_ssm_dianping.service.BusinessService;
import study_ssm_dianping.service.MemberService;
import study_ssm_dianping.util.CommonUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create By yuanyuan on 2019/7/15 13:04
 */
//@Controller
    //将方法上的@ResponseBody 与@Controller合并，这样，这个类里的所有方法都是@ResponseBody了
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private AdService adService;

    @Autowired
    private BusinessService businessService;

    @Autowired
    private MemberService memberService;

    @Value("${ad.number}")
    private int adNumber;

    @Value("5")
    private int businessHomeNumber;

//    @ResponseBody

    /**
     * 首页-广告（超值特惠）
     *
     */
    @RequestMapping(value = "/homead",method = RequestMethod.GET)
    public List<AdDto> homead() throws IOException {
        AdDto adDto = new AdDto();
        adDto.getPage().setPageNumber(adNumber);
        List<AdDto> list = adService.adListInit(adDto);
        return list;
    }

    //@ResponseBody

    /**
     * 首页——推荐列表（猜你喜欢）
     */
    @RequestMapping(value = "/homelist/{city}/{page.currentPage}",method = RequestMethod.GET)
    public BusinessListDto homelist(BusinessDto businessDto) throws IOException {
        businessDto.getPage().setPageNumber(businessHomeNumber);
        return businessService.searchByPageForApi(businessDto);
    }

    /**
     * 搜索结果页 - 三个参数
     * @param businessDto
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/search/{page.currentPage}/{city}/{category}/{keyword}",method = RequestMethod.GET)
    public BusinessListDto searchByKeyWord(BusinessDto businessDto) throws IOException {
        businessDto.getPage().setPageNumber(businessHomeNumber);
        return businessService.searchByPageForApi(businessDto);
    }

    /**
     * 搜索结果页 - 两个参数
     * @param businessDto
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/search/{page.currentPage}/{city}/{category}",method = RequestMethod.GET)
    public BusinessListDto search(BusinessDto businessDto) throws IOException {
        businessDto.getPage().setPageNumber(businessHomeNumber);
        return businessService.searchByPageForApi(businessDto);
    }

    /**
     * 商户详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/detail/info/{id}",method = RequestMethod.GET)
    public BusinessDto detail(@PathVariable("id") Integer id) {
        BusinessDto businessDto = businessService.modifyInit(id);
        return businessDto;
    }

    @RequestMapping(value = "/sms",method = RequestMethod.POST)
    public ApiCodeDto sms(@RequestParam("username") Long username){
        ApiCodeDto dto;
        //1、 验证用户手机号是否存在（是否注册过）
        if (memberService.exists(username)) {
            //2、生成六位随机数
            String code = String.valueOf(CommonUtil.random(6));
            //3、保存手机号与对应的md5（6为随机）（一般保存一分钟，1分钟后失效）
            if (memberService.saveCode(username, code)) {
                //4、调用短信通道，将明文6位随机数发送到对应的手机上
                if (memberService.sendCode(username, code)) {
                    dto = new ApiCodeDto(ApiCodeEnum.SUCCESS.getErrno(), code);
                } else {
                    dto = new ApiCodeDto(ApiCodeEnum.SEND_FAIL);
                }
            } else {
                dto = new ApiCodeDto(ApiCodeEnum.REPEAT_REQUEST);
            }
        } else {
            dto = new ApiCodeDto(ApiCodeEnum.USER_NOT_EXISTS);
        }
        return dto;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ApiCodeDto login(@RequestParam("username") Long userName, @RequestParam("code") String code) {
        ApiCodeDto dto;
        //  1、用手机号取出保存的md5（6位随机数），能取到，并且与提交上来的code值相同为校验通过
        String saveCode = memberService.getCode(userName);
        if (saveCode != null) {
            if (saveCode.equals(code)) {
                //2.如果校验通过，生成一个32位的token
                String token = CommonUtil.getUUID();
                //3、保存手机号与对应的token（一般这个手机号中途没有与服务端交互的情况下，保持10分钟）
                memberService.saveToken(token, userName);
                // 4、将这个token返回给前端
                dto = new ApiCodeDto(ApiCodeEnum.SUCCESS);
                dto.setToken(token);
            } else {
                dto = new ApiCodeDto(ApiCodeEnum.CODE_ERROR);
            }
        } else {
            dto = new ApiCodeDto(ApiCodeEnum.CODE_INVALID);
        }
        return dto;
    }

    //@ResponseBody
    @RequestMapping(value = "/submitComment",method = RequestMethod.POST)
    public Map<String, Object> submitComment() throws IOException {
        Map<String, Object> result = new HashMap<>();
        result.put("errno", 0);
        result.put("msg", "ok");
        return result;
    }


}
