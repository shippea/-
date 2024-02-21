package com.oracle.oBootMybatis01.controller;

import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@Slf4j
public class Scoketcontroller {
	@RequestMapping("/chat")
	public ModelAndView chat() {
		System.out.println("Scoketcontroller chat start...");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("chatView");
		return mv;
	}
	
	
}
