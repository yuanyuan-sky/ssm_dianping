package study_ssm_dianping.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Create By yuanyuan on 2019/7/16 10:08
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    @RequestMapping
    public String init(){
        return "/system/index";
    }
}
