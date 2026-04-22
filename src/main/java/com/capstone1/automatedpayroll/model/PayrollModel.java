package com.capstone1.automatedpayroll.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class PayrollModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private EmployeeModel employee;

    private LocalDate startDate;
    private LocalDate endDate;
    private double grossPay;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmployeeModel getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeModel employee) {
        this.employee = employee;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getGrossPay() {
        return grossPay;
    }

    public void setGrossPay(double grossPay) {
        this.grossPay = grossPay;
    }

    public double getTotalDeductions() {
        return totalDeductions;
    }

    public void setTotalDeductions(double totalDeductions) {
        this.totalDeductions = totalDeductions;
    }

    public double getNetPay() {
        return netPay;
    }

    public void setNetPay(double netPay) {
        this.netPay = netPay;
    }

    public LocalDate getDateProcessed() {
        return dateProcessed;
    }

    public void setDateProcessed(LocalDate dateProcessed) {
        this.dateProcessed = dateProcessed;
    }

    private double totalDeductions;
    private double netPay;

    private LocalDate dateProcessed;

}
