package me.wuyi.easyrent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import me.wuyi.easyrent.bean.User;

@Controller
@RequestMapping("/")
public class ExampleController {
	
	@RequestMapping(value = "")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		User user = new User("hh");
		mav.setViewName("index");
		mav.addObject("user", user);
		return mav;
	}
	
	
}
