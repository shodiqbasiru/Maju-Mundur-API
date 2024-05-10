package com.msfb.maju_mundur_application.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_transaction_reward")
public class TransactionReward {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", updatable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "reward_id", nullable = false)
    private Reward reward;
}
