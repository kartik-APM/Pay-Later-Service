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
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            initialValue = 100001,
            allocationSize = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @Column(
            updatable = false,
            nullable = false
    )
    private Long userId;
    private String firstName;
    private String lastName;

    @Column(
            nullable = false,
            updatable = false
    )
    private String emailId;
    private String phoneNumber;

    @Embedded
    private Address address;

    //Bi-directional OneToOne mapping
    //to get account data when the
    // printAllUser() is called

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user"
    )
    @JsonIgnoreProperties("user")
    private Account account;
}
