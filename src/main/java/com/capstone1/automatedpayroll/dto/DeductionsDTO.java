package com.capstone1.automatedpayroll.dto;

import com.capstone1.automatedpayroll.model.EmployeeModel;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeductionsDTO {

    private Long employeeId;
    private Long deductionsId;
    private boolean stationary;
    private String deductionsName;
    private Double deductionsAmount;
}
