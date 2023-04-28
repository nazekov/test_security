package com.example.test_security.security;

import com.example.test_security.model.Person;
import com.example.test_security.repository.PersonRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PersonRepository personRepository;

    public UserDetailsServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("UserDetails loadUserByUsername(String email)");
        Person user = personRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User doesn't exists")
                );
        System.out.println("ApplicationUser " + user);
        UserDetails userDetails = PersonDetails.fromUser(user);
        System.out.println("UserDetails " + userDetails);
        return userDetails;
    }
}
