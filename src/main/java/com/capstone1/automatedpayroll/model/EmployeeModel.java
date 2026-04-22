package com.capstone1.automatedpayroll.model;

import java.time.LocalDate;
import java.util.List;

import com.capstone1.automatedpayroll.model.enums.EmployeeType;
import com.capstone1.automatedpayroll.model.enums.Gender;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
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

    @Enumerated(EnumType.STRING)
    @Column(name = "employee_employment_type")
    private EmployeeType employmentType;

    private LocalDate dateOfHire;
    private String employeeDepartment;

    private Long employeeRate;
    private double monthlySalary;

    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<EarningsModel> earningsModels;
    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<DeductionsModel> deductionsModels;
    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<AttendanceModel> attendanceModels;

    public EmployeeModel() {}

    public EmployeeModel(Long eId, String employeeEmail, String employeeFirstName, String employeeLastName,
                        Long employeeContactNumber, String employeeAddress, Gender employeeGender,
                        EmployeeType employmentType, LocalDate dateOfHire, String employeeDepartment,
                        Long employeeRate, double monthlySalary,
                        List<EarningsModel> earningsModels,
                        List<DeductionsModel> deductionsModels,
                        List<AttendanceModel> attendanceModels) {

        this.eId = eId;
        this.employeeEmail = employeeEmail;
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.employeeContactNumber = employeeContactNumber;
        this.employeeAddress = employeeAddress;
        this.employeeGender = employeeGender;
        this.employmentType = employmentType;
        this.dateOfHire = dateOfHire;
        this.employeeDepartment = employeeDepartment;
        this.employeeRate = employeeRate;
        this.monthlySalary = monthlySalary;
        this.earningsModels = earningsModels;
        this.deductionsModels = deductionsModels;
        this.attendanceModels = attendanceModels;
    }

    public Long getEmployeeRate() {return employeeRate;}
    public void setEmployeeRate(Long employeeRate) {this.employeeRate = employeeRate;}
    public double getMonthlySalary() {return monthlySalary;}
    public void setMonthlySalary(double monthlySalary) {this.monthlySalary = monthlySalary;}
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
    public EmployeeType getEmployeeEmploymentType() {return employmentType;}
    public void setEmployeeEmploymentType(EmployeeType employeeType) {this.employmentType = employmentType;}
    public LocalDate getDateOfHire() {return dateOfHire;}
    public void setDateOfHire(LocalDate dateOfHire) {this.dateOfHire = dateOfHire;}
    public String getEmployeeDepartment() {return employeeDepartment;}
    public void setEmployeeDepartment(String employeeDepartment) {this.employeeDepartment = employeeDepartment;}
    public Long geteId() {return eId;}
    public void seteId(Long eId) {this.eId = eId;}

    public static class Builder {
        private Long eId;
        private String employeeEmail;
        private String employeeFirstName;
        private String employeeLastName;
        private Long employeeContactNumber;
        private String employeeAddress;
        private Gender employeeGender;
        private EmployeeType employmentType;
        private LocalDate dateOfHire;
        private String employeeDepartment;
        private Long employeeRate;
        private double monthlySalary;
        private List<EarningsModel> earningsModels;
        private List<DeductionsModel> deductionsModels;
        private List<AttendanceModel> attendanceModels;

        public Builder eId(Long eId) { this.eId = eId; return this; }
        public Builder employeeEmail(String employeeEmail) { this.employeeEmail = employeeEmail; return this; }
        public Builder employeeFirstName(String employeeFirstName) { this.employeeFirstName = employeeFirstName; return this; }
        public Builder employeeLastName(String employeeLastName) { this.employeeLastName = employeeLastName; return this; }
        public Builder employeeContactNumber(Long employeeContactNumber) { this.employeeContactNumber = employeeContactNumber; return this; }
        public Builder employeeAddress(String employeeAddress) { this.employeeAddress = employeeAddress; return this; }
        public Builder employeeGender(Gender employeeGender) { this.employeeGender = employeeGender; return this; }
        public Builder employmentType(EmployeeType employmentType) { this.employmentType = employmentType; return this; }
        public Builder dateOfHire(LocalDate dateOfHire) { this.dateOfHire = dateOfHire; return this; }
        public Builder employeeDepartment(String employeeDepartment) { this.employeeDepartment = employeeDepartment; return this; }
        public Builder employeeRate(Long employeeRate) { this.employeeRate = employeeRate; return this; }
        public Builder monthlySalary(double monthlySalary) { this.monthlySalary = monthlySalary; return this; }
        public Builder earningsModels(List<EarningsModel> earningsModels) { this.earningsModels = earningsModels; return this; }
        public Builder deductionsModels(List<DeductionsModel> deductionsModels) { this.deductionsModels = deductionsModels; return this; }
        public Builder attendanceModels(List<AttendanceModel> attendanceModels) { this.attendanceModels = attendanceModels; return this; }

        public EmployeeModel build() {
            return new EmployeeModel(eId, employeeEmail, employeeFirstName, employeeLastName,
                                    employeeContactNumber, employeeAddress, employeeGender,
                                    employmentType, dateOfHire, employeeDepartment, employeeRate,
                                    monthlySalary, earningsModels, deductionsModels, attendanceModels);
        }
    }

    public static Builder builder() { return new Builder(); }
}
