package com.capstone1.automatedpayroll.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.capstone1.automatedpayroll.model.enums.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "employee")
public class EmployeeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eId;
    private String employeeEmail;
    private String employeeFirstName;
    private String employeeLastName;
    private Long employeeContactNumber;
    private String employeeAddress;
    @Enumerated(EnumType.STRING)
    private Gender employeeGender;
    private String employeeEmploymentType;
    private LocalDate dateOfHire;
    private String employeeDepartment;
    private Long employeeRate;
    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<EarningsModel> earningsModels;
    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<DeductionsModel> deductionsModels;
    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<AttendanceModel> attendanceModels;

    public EmployeeModel(Long eId, String employeeEmail, String employeeFirstName, String employeeLastName, long employeeContactNumber,
                         String employeeAddress,Gender employeeGender, String employeeEmploymentType, LocalDate dateOfHire, String employeeDepartment, Long employeeRate
                         ) {
        this.eId = eId;
        this.employeeEmail = employeeEmail;
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.employeeContactNumber = employeeContactNumber;
        this.employeeAddress = employeeAddress;
        this.employeeGender = employeeGender;
        this.employeeEmploymentType = employeeEmploymentType;
        this.dateOfHire = dateOfHire;
        this.employeeDepartment = employeeDepartment;
        this.employeeRate = employeeRate;
    }

    public Long getEmployeeRate() {return employeeRate;}
    public void setEmployeeRate(Long employeeRate) {this.employeeRate = employeeRate;}
    public String getEmployeeEmail() {return employeeEmail;}
    public void setEmployeeEmail(String employeeEmail) {this.employeeEmail = employeeEmail;}
    public String getEmployeeFirstName() {return employeeFirstName;}
    public void setEmployeeFirstName(String employeeFirstName) {this.employeeFirstName = employeeFirstName;}
    public String getEmployeeLastName() {return employeeLastName;}
    public void setEmployeeLastName(String employeeLastName) {this.employeeLastName = employeeLastName;}
    public Long getEmployeeContactNumber() {return employeeContactNumber;}
    public void setEmployeeContactNumber(Long employeeContactNumber) {this.employeeContactNumber = employeeContactNumber;}
    public String getEmployeeAddress() {return employeeAddress;}
    public void setEmployeeAddress(String employeeAddress) {this.employeeAddress = employeeAddress;}
    public void setEmployeeGender(Gender employeeGender) {this.employeeGender = employeeGender;}
    public Gender getEmployeeGender(){return employeeGender;}
    public String getEmployeeEmploymentType() {return employeeEmploymentType;}
    public void setEmployeeEmploymentType(String employeeEmploymentType) {this.employeeEmploymentType = employeeEmploymentType;}
    public LocalDate getDateOfHire() {return dateOfHire;}
    public void setDateOfHire(LocalDate dateOfHire) {this.dateOfHire = dateOfHire;}
    public String getEmployeeDepartment() {return employeeDepartment;}
    public void setEmployeeDepartment(String employeeDepartment) {this.employeeDepartment = employeeDepartment;}
    public Long geteId() {return eId;}
    public void seteId(Long eId) {this.eId = eId;}


}
