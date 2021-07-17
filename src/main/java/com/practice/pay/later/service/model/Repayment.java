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
                name = "idx_amount_transactionDate_status",
                columnList = "amount,transactionDate,status"
        )
)
public class Repayment {

    @Id
    @SequenceGenerator(
            name = "repaymentTransactionId_sequence",
            sequenceName = "repaymentTransactionId_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "repaymentTransactionId_sequence"
    )
    private String repaymentTransactionId;
    private int amount;
    private String transactionDate;
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
