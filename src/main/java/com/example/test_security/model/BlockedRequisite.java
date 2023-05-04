package com.example.test_security.model;

import com.example.test_security.enums.ServiceId;
import com.example.test_security.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_blocked_requisites")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@ToString
public class BlockedRequisite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String requisite;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "service", nullable = false)
    ServiceId serviceId;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", nullable = false)
    Status status;

    @Column(name = "comment")
    String comment;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss:S")
    @Column(nullable = false)
    Date createdDate;

    @ManyToOne
    @JoinColumn(name = "person_created_by_username",
                referencedColumnName = "username",
                nullable = false)
    Person createdBy;

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss:S")
    Date updatedDate;

    @ManyToOne
    @JoinColumn(name = "person_updated_by_username",
                referencedColumnName = "username")
    Person updatedBy;

    @Column(name = "actual", nullable = false)
    Boolean actual;
}
