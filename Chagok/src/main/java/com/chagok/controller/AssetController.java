package com.chagok.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/asset/*")
public class AssetController {
	
	private static final Logger mylog = LoggerFactory.getLogger(HomeController.class);
	
	@GetMapping("/myAsset")
	public String myAssetGET() {
		
		return "/asset/myAsset";
	}
	
}
