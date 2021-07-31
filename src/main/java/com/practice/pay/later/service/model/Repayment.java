package com.practice.pay.later.service.model;


import com.practice.pay.later.service.enums.Status;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.http.HttpStatus;

import javax.persistence.*;
import java.net.http.HttpResponse;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long repaymentTransactionId;

    @Column(nullable = false)
    private int amount;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date transactionDate;

    private Status status;

    @Version
    private Integer version;

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
