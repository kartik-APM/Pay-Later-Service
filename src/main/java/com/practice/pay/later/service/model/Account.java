package com.practice.pay.later.service.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

@Table(
        indexes = @Index(
                name = "idx_createdAt_updatedAt",
                columnList = "createdAt,updatedAt"

        )
)
public class Account {

    @Id
    private String accountId;
    private int authorisedCreditLimit;
    private int availableCreditLimit;
    private Date createdAt;
    private Date updatedAt;

    @OneToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "userDetails",
            referencedColumnName = "userId"
    )
    private User user;

}
