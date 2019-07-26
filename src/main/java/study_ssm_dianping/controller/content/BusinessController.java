package study_ssm_dianping.controller.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import study_ssm_dianping.bean.Dic;
import study_ssm_dianping.constant.PageCodeEnum;
import study_ssm_dianping.dto.BusinessDto;
import study_ssm_dianping.service.BusinessService;

import java.util.List;

/**
 * Create By yuanyuan on 2019/7/22 11:13
 */
@Controller
@RequestMapping("/business")
public class BusinessController {

    @Autowired
    private BusinessService service;

    /**
     * 商户列表
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String search(BusinessDto dto, Model model, @ModelAttribute(value = "flag") String flg) {
        List<BusinessDto> businessDtoList = service.searchByPage(dto);
        model.addAttribute("list", businessDtoList);
        model.addAttribute("searchParam", dto);
        if ("success".equals(flg)) {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.DEL_SUCCESS);
        } else if ("error".equals(flg)) {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.DEL_FAIL);
        }
        return "/content/businessList";
    }

    @RequestMapping(value = "/modifyInit/{id}",method = RequestMethod.GET)
    public String modifyInit(@PathVariable("id") int id,Model model,@ModelAttribute(value = "flag") String flg) {
        BusinessDto businessDto = service.modifyInit(id);
        List<Dic> cityList = service.getCitys();
        List<Dic> categoryList = service.getCategors();
        model.addAttribute("cityList", cityList);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("modifyObj", businessDto);
        if ("success".equals(flg)) {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.MODIFY_SUCCESS);
        } else if ("error".equals(flg)) {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.MODIFY_SUCCESS);
        }
        return "/content/businessModify";
    }
    @RequestMapping(value = "/modify/{id}", method = RequestMethod.PUT)
    public String modify(@PathVariable("id") Long id,BusinessDto businessDto, RedirectAttributes model) {
        businessDto.setId(id);
        boolean result = service.modify(businessDto);
        if (result) {
            model.addFlashAttribute("flag", "success");
        } else {
            model.addFlashAttribute("flag", "error");
        }
        return "redirect:/business/modifyInit/" + id;
    }

    @RequestMapping(value = "/addInit",method = RequestMethod.GET)
    public String addInit(Model model) {
        List<Dic> cityList = service.getCitys();
        List<Dic> categoryList = service.getCategors();
        model.addAttribute("cityList", cityList);
        model.addAttribute("categoryList", categoryList);
        return "/content/businessAdd";
    }

    @RequestMapping(value = "/businessAdd",method = RequestMethod.POST)
    public String businessAdd(BusinessDto businessDto,Model model) {
        boolean result = service.businessAdd(businessDto);
        if (result) {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.ADD_SUCCESS);
        } else {
            model.addAttribute(PageCodeEnum.KEY, PageCodeEnum.ADD_FAIL);
        }
        return "/content/businessAdd";
    }



    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Long id, RedirectAttributes model) {
        boolean result = service.delete(id);
        if (result) {
            model.addFlashAttribute("flag", "success");
        } else {
            model.addFlashAttribute("flag", "error");
        }
        return "redirect:/business";
    }
}
