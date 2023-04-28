package com.example.test_security.model;

import com.example.test_security.enums.Role;
import com.example.test_security.enums.Status;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;

@Entity
@Table(name = "tb_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;

    @Column(name = "password")
    String password;

    @Column(name = "name")
    String name;

    @Column(name = "email", unique = true)
    String email;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    Role role;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    Status status;
}
