package com.msfb.maju_mundur_application.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", updatable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "merchant_id")
    private Merchant merchant;

    @OneToMany(mappedBy = "transaction")
    private List<TransactionDetail> trxDetails;
}
