package com.msfb.maju_mundur_application.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "m_reward")
public class Reward {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "reward_name", length = 50, nullable = false)
    private String rewardName;

    @Column(name = "point", nullable = false)
    private Integer point;

    @Column(name = "is_delete")
    private Boolean isDelete;
}
