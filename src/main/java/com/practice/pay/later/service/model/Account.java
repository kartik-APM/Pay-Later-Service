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
                name = "idx_createdAt_updatedAt",
                columnList = "createdAt,updatedAt"

        )
)
public class Account {

    @Id
    @Column(
            //name = "accountNumber",
            updatable = false
    )
    private String accountId;

    @Column(
            updatable = false
    )
    private int authorisedCreditLimit;
    private int availableCreditLimit;
    private Date createdAt;
    private Date updatedAt;

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
