package com.example.test_security;

import com.example.test_security.enums.Status;
import com.example.test_security.model.BlockedPerson;
import com.example.test_security.model.Person;
import com.example.test_security.service.BlockedService;
import com.example.test_security.service.PersonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class TestSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestSecurityApplication.class, args);
	}

	//    @Bean
	public CommandLineRunner run(PersonService personService,
								 BlockedService blockedService) {
		return args -> {
			List<Person> personList = personService.findAll();
			List<Person> collectBanned = personList.stream()
					.filter(person -> person.getStatus() == Status.BANNED)
					.collect(Collectors.toList());

			List<BlockedPerson> blockedPersonList = blockedService.findAllBlockedPerson();

		};
	}
}
