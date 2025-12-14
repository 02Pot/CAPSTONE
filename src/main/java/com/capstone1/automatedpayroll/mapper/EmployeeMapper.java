package com.capstone1.automatedpayroll.mapper;

import com.capstone1.automatedpayroll.dto.EmployeeDTO;
import com.capstone1.automatedpayroll.model.EmployeeModel;

public class EmployeeMapper {

    public static EmployeeDTO mapToEmployeeDTO(EmployeeModel employeeModel) {
        if (employeeModel == null) return null;

        return new EmployeeDTO(
                employeeModel.geteId(),
                employeeModel.getEmployeeFirstName(),
                employeeModel.getEmployeeLastName(),
                employeeModel.getEmployeeAddress(),
                employeeModel.getEmployeeContactNumber(),
                employeeModel.getEmployeeEmail(),
                employeeModel.getEmployeeDepartment(),
                employeeModel.getDateOfHire(),
                employeeModel.getEmployeeGender(),
                employeeModel.getEmployeeEmploymentType(),
                employeeModel.getEmployeeRate()
        );
    }

    public static EmployeeModel mapToEmployee(EmployeeDTO employeeDTO){
        if (employeeDTO == null) return null;

        return EmployeeModel.builder()
                .eId(employeeDTO.getEmployeeId())
                .employeeEmail(employeeDTO.getEmployeeEmail())
                .employeeFirstName(employeeDTO.getEmployeeFirstName())
                .employeeLastName(employeeDTO.getEmployeeLastName())
                .employeeContactNumber(employeeDTO.getEmployeeContactNumber())
                .employeeAddress(employeeDTO.getEmployeeAddress())
                .employeeGender(employeeDTO.getEmployeeGender())
                .employeeEmploymentType(employeeDTO.getEmployeeEmploymentType())
                .dateOfHire(employeeDTO.getDateOfHire())
                .employeeDepartment(employeeDTO.getEmployeeDepartment())
                .employeeRate(employeeDTO.getEmployeeRate())
                .build();
    }
}
