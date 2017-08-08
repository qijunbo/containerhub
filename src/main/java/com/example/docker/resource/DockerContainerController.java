package com.example.docker.resource;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.docker.repository.Container;
import com.example.docker.service.ShellExecutorService;
import com.sun.istack.internal.Nullable;

@Controller
@RequestMapping("/container")
public class DockerContainerController {
 
	@Autowired
	private ShellExecutorService service;
	
	
	@RequestMapping(method = POST)
	public @ResponseBody Container create(@Nullable @RequestBody Container customer) {
		service.execute();
		return new Container("lims1", 8082);
	}
	 
}
