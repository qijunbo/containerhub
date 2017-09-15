package com.example;


import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.jpa.repository.CustomerRepository;
import com.example.profile.Template;
import com.example.profile.Test;

@SpringBootApplication
public class Application  implements CommandLineRunner{

	@Autowired
	private CustomerRepository repository;
	
	
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}
	
	@Override
	public void run(String... strings) throws Exception {
		
	     Map m = new HashMap();
	        m.put("clientid", "tony");
	        m.put("app.port", 12121);
	        m.put("context.path", "iframework/");


	        System.out.print(Template.loadFrom(SpringApplication.class.getClassLoader().getResourceAsStream("templates/nginx.conf.ftl")).format(m));

	 
	}
 

}
