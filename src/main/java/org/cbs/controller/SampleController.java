package org.cbs.controller;

import org.cbs.service.HelloService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SampleController {
	
	private final HelloService helloService;
	
	@GetMapping("/hello")
	public String hello() {
		log.info("안녕하세요 {}", helloService);
		System.out.println(helloService);
		System.out.println(helloService.sayHello()); 
		return "hello";
	}
	
	
}
