package com.capstone1.automatedpayroll.dto;

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
    private String employeeEmploymentType;
    private Long employeeRate;

    public Long getEmployeeRate() {
        return employeeRate;
    }

    public void setEmployeeRate(Long employeeRate) {
        this.employeeRate = employeeRate;
    }

    public String getEmployeeEmploymentType() {
        return employeeEmploymentType;
    }

    public void setEmployeeEmploymentType(String employeeEmploymentType) {
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
