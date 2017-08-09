package com.example.docker.resource;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.docker.repository.Container;
import com.example.docker.service.ShellExecutorService;

@Controller
@RequestMapping("/container")
public class DockerContainerController {
 
	@Autowired
	private ShellExecutorService service;
	
	@RequestMapping(value = "/{owner}" , method = POST)
	public @ResponseBody Container create(@PathVariable String owner) {
		return service.createContainer(owner);
		 
	}
	 
	@RequestMapping(value = "/{owner}" , method = GET)
	public @ResponseBody Container temp(@PathVariable String owner) {
		return service.getPort(owner);
	}
	
	
	
	
	 
}
