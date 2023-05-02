package com.example.test_security.model;

import com.example.test_security.enums.Role;
import com.example.test_security.enums.Status;
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

    @Column(name = "password", unique = true)
    String password;

    @Column(name = "name")
    String name;

    @Column(name = "email", unique = true)
    String email;

    @Column(name = "requisite", unique = true)
    String requisite;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    Role role;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", columnDefinition = "ACTIVE")
    Status status;
}
