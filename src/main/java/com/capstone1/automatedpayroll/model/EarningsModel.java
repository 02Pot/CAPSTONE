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

    public Long getEarningId() {
        return earningId;
    }

    public void setEarningId(Long earningId) {
        this.earningId = earningId;
    }

    public String getEarningName() {
        return earningName;
    }

    public void setEarningName(String earningName) {
        this.earningName = earningName;
    }

    public Double getEarningAmount() {
        return earningAmount;
    }

    public void setEarningAmount(Double earningAmount) {
        this.earningAmount = earningAmount;
    }

    public boolean isStationary() {
        return stationary;
    }

    public void setStationary(boolean stationary) {
        this.stationary = stationary;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public EmployeeModel getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeModel employee) {
        this.employee = employee;
    }


}
