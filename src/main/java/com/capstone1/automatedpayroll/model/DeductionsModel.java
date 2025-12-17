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
    private double maxAmount;

    private double deductionPercentage;

    @ManyToOne
    @JoinColumn(name = "userId")
    private EmployeeModel employee;

    public Long getDeductionId() {
        return deductionId;
    }

    public void setDeductionId(Long deductionId) {
        this.deductionId = deductionId;
    }

    public String getDeductionName() {
        return deductionName;
    }

    public void setDeductionName(String deductionName) {
        this.deductionName = deductionName;
    }

    public Double getDeductionAmount() {
        return deductionAmount;
    }

    public void setDeductionAmount(Double deductionAmount) {
        this.deductionAmount = deductionAmount;
    }

    public boolean isStationary() {
        return stationary;
    }

    public void setStationary(boolean stationary) {
        this.stationary = stationary;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public double getDeductionPercentage() {
        return deductionPercentage;
    }

    public void setDeductionPercentage(double deductionPercentage) {
        this.deductionPercentage = deductionPercentage;
    }

    public EmployeeModel getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeModel employee) {
        this.employee = employee;
    }


}
