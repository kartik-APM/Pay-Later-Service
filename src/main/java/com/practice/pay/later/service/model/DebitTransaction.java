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
        uniqueConstraints = @UniqueConstraint(
                columnNames = "debitTransactionId"
        ),
        indexes = @Index(
                name = "idx_amount_transactionDate_status",
                columnList = "amount,transactionDate,status"
        )
)
public class DebitTransaction {

    @Id
    @Column(
            updatable = false,
            nullable = false
    )
    private String debitTransactionId;
    private int amount;
    @Column(
            updatable = false,
            nullable = false
    )
    private String orderId;
    private Date transactionDate;
    private String status;

    @ManyToOne(
            cascade = CascadeType.ALL,
            optional = false
    )
    @JoinColumn(
            name = "accountDetails",
            referencedColumnName = "accountId"
    )
    private Account account;
}
