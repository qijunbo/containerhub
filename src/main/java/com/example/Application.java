package com.example;


import java.util.stream.IntStream;

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
		repository.save(new Customer("Chloe", "O'Brian", "Chloe@builder.com", null));
		repository.save(new Customer("Kim", "Bauer", "Kim@builder.com", null));
		repository.save(new Customer("David", "Palmer", "David@builder.com", null));
		repository.save(new Customer("Michelle", "Dessler", "Michelle@builder.com", null));

		// fetch all customers
		log.info("Customers found with findAll():");
		log.info("-------------------------------");
		
		repository.findAll().forEach(customer -> log.info(customer.toString()));
		
		log.info("");

		// fetch an individual customer by ID
		Customer customer = repository.findOne(1L);
		log.info("Customer found with findOne(1L):");
		log.info("--------------------------------");
		log.info(customer.toString());
		log.info("");

		// fetch customers by last name
		log.info("Customer found with findByLastName('Bauer'):");
		log.info("--------------------------------------------");
		
		repository.findByLastName("Bauer").forEach(bauer -> log.info(bauer.toString()));	 
		log.info("");
	 
	}
 

}
