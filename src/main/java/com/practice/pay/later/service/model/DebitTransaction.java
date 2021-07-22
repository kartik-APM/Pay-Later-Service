package com.practice.pay.later.service.model;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "account")
@Table(
        uniqueConstraints = @UniqueConstraint(
                name = "unique_orderId",
                columnNames = "orderId"
        ),
        indexes = @Index(
                name = "idx_amount_transactionDate_status",
                columnList = "amount,transactionDate,status"
        )
)
public class DebitTransaction {

    @Id
    @SequenceGenerator(
            name = "debitTransactionId_sequence",
            sequenceName = "debitTransactionId_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "debitTransactionId_sequence"
    )
    private Long debitTransactionId;

    @Column(nullable = false)
    private int amount;

    @Column(
            updatable = false,
            nullable = false
    )
    private String orderId;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date transactionDate;


    private String status;

    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "accountID",
            referencedColumnName = "accountId"
    )
    private Account account;
}
