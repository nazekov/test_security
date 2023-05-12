package com.example.test_security.util;

import com.example.test_security.model.Person;

public interface ValidatePersonService {

    boolean existsUsername(Person person);
}
