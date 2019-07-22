package study_ssm_dianping.controller.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String adListInit( Model model) {
        AdDto adDto = new AdDto();
        List<AdDto> list = service.adListInit(adDto);
        model.addAttribute("list", list);
        model.addAttribute("searchParam", adDto);
        return "/content/adList";
    }

    @RequestMapping("/search")
    public String search(Model model,AdDto adDto) {
        List<AdDto> list = service.adListInit(adDto);
        model.addAttribute("list", list);
        model.addAttribute("searchParam", adDto);
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

    @RequestMapping("/remove/{id}")
    public String remove(@PathVariable("id") int id, Model model) {
        boolean result = service.remove(id);
        if (result) {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.DEL_SUCCESS);
        }else {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.DEL_FAIL);
        }

        return "forward:/ad/adListInit";
    }

    @RequestMapping("/modifyInit/{id}")
    public String modifyInit(@PathVariable("id") int id,Model model) {
        AdDto adDto = service.modifyInit(id);
        model.addAttribute("modifyObj", adDto);
        return "/content/adModify";
    }

    @RequestMapping("/modify")
    public String modify(AdDto adDto,Model model){
        boolean result = service.modify(adDto);
        if (result) {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.MODIFY_SUCCESS);
        } else {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.MODIFY_FAIL);
        }
        model.addAttribute("modifyObj", adDto);
        return "/content/adModify";
    }
}
