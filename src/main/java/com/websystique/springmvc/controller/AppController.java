package com.websystique.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class AppController {

	@RequestMapping(value = { "/"}, method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		return "home";
	}

	@RequestMapping(value = { "/playalongs"}, method = RequestMethod.GET)
	public String playalongsPage(ModelMap model) {
		return "playalongs";
	}

	@RequestMapping(value = { "/videos"}, method = RequestMethod.GET)
	public String videosPage(ModelMap model) {
		return "videos";
	}

	@RequestMapping(value = { "/tutorials"}, method = RequestMethod.GET)
	public String tutorialsPage(ModelMap model) {
		return "tutorials";
	}

}
