package com.pactera.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat")
public class ChatController {
	@RequestMapping(value = "/login")
	public String getGoodsList() {
		return "/chat";
	}
}
