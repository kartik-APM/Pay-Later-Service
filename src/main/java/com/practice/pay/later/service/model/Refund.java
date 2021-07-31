package com.practice.pay.later.service.model;


import com.practice.pay.later.service.enums.Status;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.http.HttpStatus;

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
public class Refund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long refundTransactionId;

    @Column(nullable = false)
    private int amount;

    @Column(
            updatable = false,
            nullable = false
    )
    private Long originalTransactionId;

    @Version
    private Integer version;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date transactionDate;

    private Status status;

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
