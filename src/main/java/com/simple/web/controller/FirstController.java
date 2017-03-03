package com.simple.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.simple.common.base.BaseUrl;

@Controller
@RequestMapping(value=BaseUrl.First)
public class FirstController {

	@GetMapping(value="hello")
	public String hello(Model model){
		System.out.println("hello");
		model.addAttribute("title","hello");
		return "hello";
	}
}
