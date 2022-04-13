package com.example.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.NetflixTitle;
import com.example.service.ResourceService;

@RestController
public class SearchController {
	
	private final ResourceService service;	
	
	@Autowired
	public SearchController(ResourceService service) {
		super();
		this.service = service;
	}

//	@GetMapping("/search/{*params}")
//	public void getResource(@PathVariable String params) {
//		System.out.println("Params: " + params);
//		service.getResource(new ArrayList<String>(Arrays.asList(params.split("/"))));
//	}
	
	@GetMapping("/search")
	public List<NetflixTitle> getEntity(@RequestParam Map<String, String> params) {
		System.out.println("Paramters: " + params.toString());
		return service.getResource(params);
	}

}
