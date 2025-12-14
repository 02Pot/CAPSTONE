package com.capstone1.automatedpayroll.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "earnings")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class EarningsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long earningId;
    private String earningName;
    private Double earningAmount;
    private boolean stationary;
    @CreationTimestamp
    private LocalDate dateCreated;
    @ManyToOne
    @JoinColumn(name = "userId")
    private EmployeeModel employee;

}
