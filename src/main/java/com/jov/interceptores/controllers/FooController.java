package com.jov.interceptores.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collections;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/app")
public class FooController {

	@GetMapping("/foo")
	public Map<String, Object> foo() {
		return   Collections.singletonMap("message", "handler foo del controlador");
	}
	
	@GetMapping("/bar")
	public Map<String, Object> bar() {
		return   Collections.singletonMap("message", "handler bar del controlador");
	}
	
	@GetMapping("/baz")
	public Map<String, Object> baz() {
		return   Collections.singletonMap("message", "handler baz del controlador");
	}
	
}
