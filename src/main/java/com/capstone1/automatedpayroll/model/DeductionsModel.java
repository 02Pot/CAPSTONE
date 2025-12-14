package com.capstone1.automatedpayroll.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "deductions")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DeductionsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deductionId;
    private String deductionName;
    private Double deductionAmount;
    private boolean stationary;
    @ManyToOne
    @JoinColumn(name = "userId")
    private EmployeeModel employee;

}
