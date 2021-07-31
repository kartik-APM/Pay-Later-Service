package com.practice.pay.later.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString(exclude = "user")

@Table(
        indexes = @Index(
                name = "idx_dateAccountCreated_dateAccountUpdated",
                columnList = "dateAccountCreated,dateAccountUpdated"

        )
)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long accountId;

    @Column(
            updatable = false,
            nullable = false
    )
    private int authorisedCreditLimit;

    private int availableCreditLimit;

    @Version
    private Integer version;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date dateAccountCreated;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAccountUpdated;

    @OneToOne(
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "userID",
            referencedColumnName = "userId"
    )
    @JsonIgnoreProperties("account")
    private User user;
}
