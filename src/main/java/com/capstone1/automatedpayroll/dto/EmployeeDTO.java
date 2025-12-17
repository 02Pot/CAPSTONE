package com.capstone1.automatedpayroll.dto;

import com.capstone1.automatedpayroll.model.enums.EmployeeType;
import com.capstone1.automatedpayroll.model.enums.Gender;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDTO {

    private Long employeeId;
    private String employeeFirstName;
    private String employeeLastName;
    private String employeeAddress;
    private Long employeeContactNumber;
    private String employeeEmail;
    private String employeeDepartment;
    private LocalDate dateOfHire;
    private Gender employeeGender;
    private EmployeeType employeeEmploymentType;
    private Long employeeRate;
    private double monthlySalary;
    private Long latestPayrollId;

    public Long getLatestPayrollId() {
        return latestPayrollId;
    }

    public void setLatestPayrollId(Long latestPayrollId) {
        this.latestPayrollId = latestPayrollId;
    }

    public Long getEmployeeRate() {
        return employeeRate;
    }

    public void setMonthlySalary(double monthlySalary) {
        this.monthlySalary = monthlySalary;
    }
    public double getMonthlySalary() {
        return monthlySalary;
    }

    public void setEmployeeRate(Long employeeRate) {
        this.employeeRate = employeeRate;
    }
    public EmployeeType getEmployeeEmploymentType() {
        return employeeEmploymentType;
    }

    public void setEmployeeEmploymentType(EmployeeType employeeEmploymentType) {
        this.employeeEmploymentType = employeeEmploymentType;
    }

    public Gender getEmployeeGender() {
        return employeeGender;
    }

    public void setEmployeeGender(Gender employeeGender) {
        this.employeeGender = employeeGender;
    }

    public LocalDate getDateOfHire() {
        return dateOfHire;
    }

    public void setDateOfHire(LocalDate dateOfHire) {
        this.dateOfHire = dateOfHire;
    }

    public String getEmployeeDepartment() {
        return employeeDepartment;
    }

    public void setEmployeeDepartment(String employeeDepartment) {
        this.employeeDepartment = employeeDepartment;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public Long getEmployeeContactNumber() {
        return employeeContactNumber;
    }

    public void setEmployeeContactNumber(Long employeeContactNumber) {
        this.employeeContactNumber = employeeContactNumber;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }


}
