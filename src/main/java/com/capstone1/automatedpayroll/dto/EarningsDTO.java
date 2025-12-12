package com.capstone1.automatedpayroll.dto;

import com.capstone1.automatedpayroll.model.EmployeeModel;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EarningsDTO {

    private Long employeeId;
    private Long earningsId;
    private boolean stationary;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getEarningsId() {
        return earningsId;
    }

    public void setEarningsId(Long earningsId) {
        this.earningsId = earningsId;
    }

    public boolean isStationary() {
        return stationary;
    }

    public void setStationary(boolean stationary) {
        this.stationary = stationary;
    }

    public String getEarningsName() {
        return earningsName;
    }

    public void setEarningsName(String earningsName) {
        this.earningsName = earningsName;
    }

    public Double getEarningsAmount() {
        return earningsAmount;
    }

    public void setEarningsAmount(Double earningsAmount) {
        this.earningsAmount = earningsAmount;
    }

    private String earningsName;
    private Double earningsAmount;
}
