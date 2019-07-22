package study_ssm_dianping.controller.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public String search(BusinessDto dto, Model model) {
        List<BusinessDto> businessDtoList = service.searchByPage(dto);
        model.addAttribute("list", businessDtoList);
        model.addAttribute("searchParam", dto);
        return "/content/businessList";
    }

}
