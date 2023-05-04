package com.example.test_security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestSecurityApplication.class, args);
	}

	//    @Bean
//	public CommandLineRunner run(PersonService personService,
//								 BlockedService blockedService) {
//		return args -> {
//			List<Person> personList = personService.findAll();
//			List<Person> collectBanned = personList.stream()
//					.filter(person -> person.getStatus() == Status.BANNED)
//					.collect(Collectors.toList());
//
//			List<BlockedRequisite> blockedRequisiteList = blockedService.findAllBlockedPerson();
//
//		};
//	}
}
