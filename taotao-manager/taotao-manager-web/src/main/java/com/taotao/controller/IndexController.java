package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping(value= {"","/"})
	public String index() {
		return "index";
	}
	
	@RequestMapping("/{page}")
	public String page(@PathVariable String page) {
		return page;
	}
}
