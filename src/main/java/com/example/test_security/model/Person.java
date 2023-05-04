package com.example.test_security.model;

import com.example.test_security.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@ToString
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "username", unique = true)
    String username;

    @Column(name = "password", unique = true)
    String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    Role role;
}
