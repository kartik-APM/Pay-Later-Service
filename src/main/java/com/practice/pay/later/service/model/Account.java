package com.practice.pay.later.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString   //(exclude = "user")

@Table(
        indexes = @Index(
                name = "idx_dateAccountCreated_dateAccountUpdated",
                columnList = "dateAccountCreated,dateAccountUpdated"

        )
)
public class Account {

    @Id
    @SequenceGenerator(
            name = "account_sequence",
            sequenceName = "account_sequence",
            initialValue = 1000001,
            allocationSize = 100
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "account_sequence"
    )
    private Long accountId;

    @Column(
            updatable = false
    )
    private int authorisedCreditLimit;
    private int availableCreditLimit;

    @Column(
            updatable = false
    )
    private String dateAccountCreated;
    private String dateAccountUpdated;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "userDetails",
            referencedColumnName = "userId"
    )
    @JsonIgnoreProperties("account")
    private User user;
}
