package com.base.app.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MainController {

    @RequestMapping(value = "/")
	public ModelAndView index() throws Exception{
		ModelAndView mav = new ModelAndView();
		System.out.println("tttaaaaaatt");
		mav.setViewName("index"); //jsp(html)로 갈때는 setViewName // class로 갈때는 setView
		return mav;
	}

    @RequestMapping(value = "/main")
	public ModelAndView main() throws Exception{
		ModelAndView mav = new ModelAndView();
		System.out.println("ttttt");
		mav.setViewName("main/main"); //jsp(html)로 갈때는 setViewName // class로 갈때는 setView
		return mav;
	}

	@RequestMapping(value = "/main/call.do")
	public ModelAndView call() throws Exception{
		ModelAndView mav = new ModelAndView();
		System.out.println("진입이요");
		
		//mav.setViewName("main/main"); //jsp(html)로 갈때는 setViewName // class로 갈때는 setView
		
		return mav;
	}
}