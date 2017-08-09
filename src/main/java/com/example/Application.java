package com.example;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.jpa.repository.Customer;
import com.example.jpa.repository.CustomerRepository;

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
		
		// save a couple of customers
		repository.save(new Customer("Jack", "Bauer", "Jack@builder.com", null ));
		log.info("");
	 
	}
 

}
