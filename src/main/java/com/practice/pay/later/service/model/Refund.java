package com.practice.pay.later.service.model;


import lombok.*;

import javax.persistence.*;

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
public class Refund {

    @Id
    @SequenceGenerator(
            name = "refundTransactionId_sequence",
            sequenceName = "refundTransactionId_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "refundTransactionId_sequence"
    )
    private Long refundTransactionId;
    private int amount;
    @Column(
            updatable = false,
            nullable = false
    )
    private Long originalTransactionId;
    private String transactionDate;
    private String status;

    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "accountDetails",
            referencedColumnName = "accountId"
    )
    private Account account;
}
