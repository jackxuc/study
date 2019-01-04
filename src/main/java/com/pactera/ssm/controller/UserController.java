package com.pactera.ssm.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pactera.ssm.entities.UserInfo;
import com.pactera.ssm.service.UserService;

@Controller
public class UserController {
	@Resource
	private UserService userService;
	@RequestMapping("/check")
	public String check(Model model,@ModelAttribute("userinfo") @Valid UserInfo userinfo) {
		UserInfo user = userService.getUserInfoByUsername(userinfo.getUsername());
		String message = "";
		if (user != null) {
			String truepwd = user.getPassword();
			if (truepwd.equals(userinfo.getPassword())) {
				return "redirect:/goods/goodslist";
			} else {
				message = "密码错误";
			}
		} else {
			message = "账号不存在";
		}
		model.addAttribute("message", message);
		return "redirect:/login";
	}
	@RequestMapping("/login")
	public String login(@ModelAttribute("userinfo") @Valid UserInfo userinfo) {
		return "/login";
	}
	@RequestMapping(value="/check2",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String check2(@RequestParam("username")  String username,@RequestParam("password")  String password) {
		UserInfo user = userService.getUserInfoByUsername(username);
		String message = "{success:false}";
		if (user != null) {
			String truepwd = user.getPassword();
			if (truepwd.equals(password)) {
				message = "{success:true,message:'登陆成功'}";
			} else {
				message = "{success:false,message:'密码错误'}";
			}
		} else {
			message = "{success:false,message:'账号不存在'}";
		}
		return message;
	}
}
