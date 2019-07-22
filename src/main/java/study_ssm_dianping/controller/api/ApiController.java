package study_ssm_dianping.controller.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import study_ssm_dianping.bean.BusinessList;
import study_ssm_dianping.dto.AdDto;
import study_ssm_dianping.service.AdService;

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

    @Value("${ad.number}")
    private int adNumber;

//    @ResponseBody
    @RequestMapping(value = "/homead",method = RequestMethod.GET)
    public List<AdDto> homead() throws IOException {
        AdDto adDto = new AdDto();
        adDto.getPage().setPageNumber(adNumber);
        List<AdDto> list = adService.adListInit(adDto);
        return list;
    }

    //@ResponseBody
    @RequestMapping(value = "/homelist/{city}/{page}",method = RequestMethod.GET)
    public BusinessList homelist() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String s = "{\n" +
                "\t\"hasMore\": true,\n" +
                "\t\"data\": [{\n" +
                "\t\t\"img\": \"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016201638030-473660627.png\",\n" +
                "\t\t\"title\": \"汉堡大大\",\n" +
                "\t\t\"subTitle\": \"叫我汉堡大大，还你多彩口味\",\n" +
                "\t\t\"price\": \"28\",\n" +
                "\t\t\"distance\": \"120m\",\n" +
                "\t\t\"mumber\": \"389\",\n" +
                "\t\t\"id\": \"07631741853861862\"\n" +
                "\t}, {\n" +
                "\t\t\"img\": \"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016201645858-1342445625.png\",\n" +
                "\t\t\"title\": \"北京开源饭店\",\n" +
                "\t\t\"subTitle\": \"[望京]自助晚餐\",\n" +
                "\t\t\"price\": \"98\",\n" +
                "\t\t\"distance\": \"140m\",\n" +
                "\t\t\"mumber\": \"689\",\n" +
                "\t\t\"id\": \"19471945916274724\"\n" +
                "\t}, {\n" +
                "\t\t\"img\": \"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016201652952-1050532278.png\",\n" +
                "\t\t\"title\": \"服装定制\",\n" +
                "\t\t\"subTitle\": \"原价xx元，现价xx元，可修改一次\",\n" +
                "\t\t\"price\": \"1980\",\n" +
                "\t\t\"distance\": \"160\",\n" +
                "\t\t\"mumber\": \"106\",\n" +
                "\t\t\"id\": \"8900325022560771\"\n" +
                "\t}, {\n" +
                "\t\t\"img\": \"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016201700186-1351787273.png\",\n" +
                "\t\t\"title\": \"婚纱摄影\",\n" +
                "\t\t\"subTitle\": \"免费试穿，拍照留念\",\n" +
                "\t\t\"price\": \"2899\",\n" +
                "\t\t\"distance\": \"160\",\n" +
                "\t\t\"mumber\": \"58\",\n" +
                "\t\t\"id\": \"9961477451151537\"\n" +
                "\t}, {\n" +
                "\t\t\"img\": \"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016201708124-1116595594.png\",\n" +
                "\t\t\"title\": \"麻辣串串烧\",\n" +
                "\t\t\"subTitle\": \"双人免费套餐等你抢购\",\n" +
                "\t\t\"price\": \"0\",\n" +
                "\t\t\"distance\": \"160\",\n" +
                "\t\t\"mumber\": \"1426\",\n" +
                "\t\t\"id\": \"8504133532453393\"\n" +
                "\t}]\n" +
                "}";

        return mapper.readValue(s, new TypeReference<BusinessList>(){});
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
