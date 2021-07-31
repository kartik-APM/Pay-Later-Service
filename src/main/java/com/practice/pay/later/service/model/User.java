package com.practice.pay.later.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString

@Table(
        uniqueConstraints = @UniqueConstraint(
                name = "unique_emailId",
                columnNames = "emailId"
        ),
        indexes = @Index(
                name = "idx_firstname_lastName_emailId",
                columnList = "firstName,lastName,emailId"
        )
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long userId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(
            nullable = false,
            updatable = false
    )
    private String emailId;

    private String phoneNumber;

    @Embedded
    private Address address;

    @Version
    private Integer version;

    //Bi-directional OneToOne mapping
    //to get account data when the
    // printAllUser() is called

    @OneToOne(
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY,
            mappedBy = "user"
    )
    @JsonIgnoreProperties("user")
    private Account account;
}
