package com.example.test_security.model;

import com.example.test_security.enums.Service;
import com.example.test_security.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_status_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class BlockedPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "person_blocked_id", referencedColumnName = "id")
    Person blocked;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "service")
    Service service;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    Status status;

    @Column(name = "comment")
    String comment;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss:S")
    @Column(nullable = false)
    Date createdDate;

    @ManyToOne
    @JoinColumn(name = "person_createdBy_id", referencedColumnName = "id")
    Person createdBy;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss:S")
    @Column(nullable = false)
    Date updatedDate;

    @ManyToOne
    @JoinColumn(name = "person_updatedBy_id", referencedColumnName = "id")
    Person updatedBy;
}
