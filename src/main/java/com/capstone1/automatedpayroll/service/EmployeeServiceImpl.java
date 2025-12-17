package com.capstone1.automatedpayroll.service;

import com.capstone1.automatedpayroll.dto.DeductionsDTO;
import com.capstone1.automatedpayroll.dto.EmployeeDTO;
import com.capstone1.automatedpayroll.mapper.EmployeeMapper;
import com.capstone1.automatedpayroll.model.EmployeeModel;
import com.capstone1.automatedpayroll.model.enums.EmployeeType;
import com.capstone1.automatedpayroll.repository.DeductionsRepository;
import com.capstone1.automatedpayroll.repository.EmployeeRepository;
import com.capstone1.automatedpayroll.repository.PayrollRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DeductionsRepository deductionsRepository;
    @Autowired
    private DeductionsServiceImpl deductionsService;
    @Autowired
    private PayrollRepository payrollRepository;

    public List<EmployeeDTO> findAllEmployees(){
        return employeeRepository.findAll()
                .stream()
                .map(emp -> EmployeeMapper.mapToEmployeeDTO(emp,payrollRepository))
                .collect(Collectors.toList());
    }

    public EmployeeDTO getEmployee(Long employeeId){
        EmployeeModel employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new UsernameNotFoundException("No User with Id of " + employeeId));

        return EmployeeMapper.mapToEmployeeDTO(employee,payrollRepository);
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO){
        EmployeeModel employee = EmployeeMapper.mapToEmployee(employeeDTO);
        employee.seteId(null);

        EmployeeModel saved = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDTO(saved,payrollRepository);
    }

    public EmployeeDTO updateEmployee( Long employeeId,EmployeeDTO employeeDTO) {
        EmployeeModel existingEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Earnings not found"));
        if (employeeDTO.getEmployeeFirstName() != null) {
            existingEmployee.setEmployeeFirstName(employeeDTO.getEmployeeFirstName());
        }
        if (employeeDTO.getEmployeeLastName() != null) {
            existingEmployee.setEmployeeLastName(employeeDTO.getEmployeeLastName());
        }
        if (employeeDTO.getEmployeeAddress() != null) {
            existingEmployee.setEmployeeFirstName(employeeDTO.getEmployeeFirstName());
        }
        if (employeeDTO.getEmployeeContactNumber() != null) {
            existingEmployee.setEmployeeContactNumber(employeeDTO.getEmployeeContactNumber());
        }
        if (employeeDTO.getEmployeeEmail() != null) {
            existingEmployee.setEmployeeEmail(employeeDTO.getEmployeeEmail());
        }
        if (employeeDTO.getEmployeeDepartment() != null) {
            existingEmployee.setEmployeeDepartment(employeeDTO.getEmployeeDepartment());
        }
        if (employeeDTO.getDateOfHire() != null) {
            existingEmployee.setDateOfHire(employeeDTO.getDateOfHire());
        }
        if (employeeDTO.getEmployeeEmploymentType() != null) {
            existingEmployee.setEmployeeEmploymentType(employeeDTO.getEmployeeEmploymentType());
        }
        if (employeeDTO.getEmployeeRate() != null) {
            existingEmployee.setEmployeeRate(employeeDTO.getEmployeeRate());
        }

        EmployeeModel updated = employeeRepository.save(existingEmployee);

        return EmployeeMapper.mapToEmployeeDTO(updated,payrollRepository);
    }

    public void deleteEmployee(Long employeeId){
        employeeRepository.deleteById(employeeId);
    }

}
