package study_ssm_dianping.controller.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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

    @RequestMapping(value = "/adListInit",method = RequestMethod.GET)
    public String adListInit( Model model,@ModelAttribute(value = "flg") String flg) {
        AdDto adDto = new AdDto();
        List<AdDto> list = service.adListInit(adDto);
        model.addAttribute("list", list);
        model.addAttribute("searchParam", adDto);
        if (flg != null && !"".equals(flg)) {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.getPageCodeEnumByCode(Integer.valueOf(flg)));
        }
        return "/content/adList";
    }

    @RequestMapping(value ="/search",method = RequestMethod.POST)
    public String search(Model model,AdDto adDto) {
        List<AdDto> list = service.adListInit(adDto);
        model.addAttribute("list", list);
        model.addAttribute("searchParam", adDto);
        return "/content/adList";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String Add(AdDto adDto, Model model) {
        boolean result = service.add(adDto);
        if (result) {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.ADD_SUCCESS);
        } else {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.ADD_FAIL);
        }
        return "/content/adAdd";
    }

    @RequestMapping(value = "/remove/{id}",method = RequestMethod.DELETE)
    public String remove(@PathVariable("id") int id, RedirectAttributes attributes) {
        boolean result = service.remove(id);
        if (result) {
            attributes.addAttribute("flg", PageCodeEnum.DEL_SUCCESS.getCode().toString());
        }else {
            attributes.addAttribute("flg", PageCodeEnum.DEL_FAIL.getCode().toString());
        }
        return "redirect:/ad/adListInit";
    }

    @RequestMapping(value = "/modifyInit/{id}",method = RequestMethod.GET)
    public String modifyInit(@PathVariable("id") int id,Model model,@ModelAttribute(value = "flg") String flg) {
        AdDto adDto = service.modifyInit(id);
        model.addAttribute("modifyObj", adDto);
        if (flg != null && !"".equals(flg)) {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.getPageCodeEnumByCode(Integer.valueOf(flg)));
        }
        return "/content/adModify";
    }

    @RequestMapping(value = "/modify",method = RequestMethod.PUT)
    public String modify(AdDto adDto,RedirectAttributes attributes){
        boolean result = service.modify(adDto);
        if (result) {
            attributes.addAttribute("flg", PageCodeEnum.MODIFY_SUCCESS.getCode());
        } else {
            attributes.addAttribute("flg", PageCodeEnum.MODIFY_FAIL.getCode());
        }
        return "redirect:/ad/modifyInit/"+adDto.getId();
    }
}
