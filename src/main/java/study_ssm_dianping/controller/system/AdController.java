package study_ssm_dianping.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import study_ssm_dianping.bean.Ad;
import study_ssm_dianping.constant.PageCodeEnum;
import study_ssm_dianping.dto.AdDto;
import study_ssm_dianping.service.AdService;

import java.util.List;

/**
 * Create By yuanyuan on 2019/7/17 15:14
 */
@Controller
@RequestMapping("/ad")
public class AdController {

    @Autowired
    private AdService service;

    @RequestMapping("/addInit")
    public String addInit() {
        return "/content/adAdd";
    }

    @RequestMapping("/adListInit")
    public String adListInit(Model model) {
        List<Ad> list = service.adListInit();
        model.addAttribute("list", list);
        return "/content/adList";
    }

    @RequestMapping("add")
    public String Add(AdDto adDto, Model model) {
        boolean result = service.add(adDto);
        if (result) {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.ADD_SUCCESS);
        } else {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.ADD_FAIL);
        }
        return "/content/adAdd";
    }
}
