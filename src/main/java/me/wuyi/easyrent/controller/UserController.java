package me.wuyi.easyrent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import me.wuyi.easyrent.bean.User;
import me.wuyi.easyrent.service.UserService;

@Controller
@RequestMapping("/")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		User user = userService.getUser(1);
		mav.setViewName("index");
		mav.addObject("user", user);
		return mav;
	}
	
	
}
