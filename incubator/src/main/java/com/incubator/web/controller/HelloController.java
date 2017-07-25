/**
 * 
 */
package com.incubator.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.incubator.service.IUserService;

/**
 * @author HDP23
 *
 */
@Controller
@RequestMapping("/hello")
public class HelloController {
	@Autowired
	private IUserService userService;
	@RequestMapping(value={"","/index"})
	public String hello(){
		userService.getUser();
		return "index";
	}
	@RequestMapping(value="/baidumap")
	public String baidumap(){
		return "baidumap";
	}

}
