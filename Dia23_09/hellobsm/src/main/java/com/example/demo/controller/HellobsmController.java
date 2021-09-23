package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hellobsm")
public class HellobsmController {

	@GetMapping
	
	public String bsm ()
	{
		return   "<br /> Mentalidade de crescimento"
                + "<br /> Persistência";
              
		
	}
	
	
}
