package com.baidu.dpop.ctp.index.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**   
 * Index Controller
 * @author cgd  
 * @date 2014年12月22日 下午3:09:01 
 */
@Controller
public class IndexController {
    /**
     * 标注平台首页
     * */
    @RequestMapping(value={"/", "index.html"}, method=RequestMethod.GET)
    public ModelAndView showIndex() {
        return new ModelAndView("index");
    }
}
