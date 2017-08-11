package com.example.docker.resource;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.common.RestResponse;
import com.example.docker.repository.Container;
import com.example.docker.repository.ContainerRepository;
import com.example.docker.service.ShellExecutorService;

@Controller
@RequestMapping("/container")
public class DockerContainerController {

	@Autowired
	private ShellExecutorService service;
	@Autowired
	private ContainerRepository  repository;

	@RequestMapping(value = "/{owner}", method = POST)
	public @ResponseBody RestResponse<Container> create(@PathVariable String owner) {

		RestResponse<Container> error = new RestResponse<>(null);
		error.getHeader().setErrorCode(2);
		error.getHeader().setTemplete("%s has created a container before!");
		error.getHeader().setParms(new String[] { owner });

		return Optional.ofNullable(service.createContainer(owner)).map(c -> new RestResponse<>(c)).orElse(error);

	}

	@RequestMapping(value = "/{owner}", method = GET)
	public @ResponseBody RestResponse<Container> temp(@PathVariable String owner) {

		RestResponse<Container> error = new RestResponse<>(null);
		error.getHeader().setErrorCode(1);
		error.getHeader().setTemplete("Please create container first!");

		return Optional.ofNullable(service.getPort(owner)).map(c -> new RestResponse<>(c)).orElse(error);
	}
	
	
	/**
	 * @return all the Customers
	 */
	@RequestMapping(method = GET)
	public @ResponseBody Iterable<Container> get() {
		return repository.findAll();
	}

}
