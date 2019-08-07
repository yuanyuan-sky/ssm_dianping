package study_ssm_dianping.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import study_ssm_dianping.dto.AdDto;
import study_ssm_dianping.dto.BusinessDto;
import study_ssm_dianping.dto.BusinessListDto;
import study_ssm_dianping.service.AdService;
import study_ssm_dianping.service.BusinessService;

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

    @RequestMapping(value = "/detail/info/{id}",method = RequestMethod.GET)
    public BusinessDto detail(@PathVariable("id") Integer id) {
        BusinessDto businessDto = businessService.modifyInit(id);
        return businessDto;
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
