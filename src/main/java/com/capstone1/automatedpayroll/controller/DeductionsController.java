package com.capstone1.automatedpayroll.controller;

import com.capstone1.automatedpayroll.dto.DeductionsDTO;
import com.capstone1.automatedpayroll.dto.EarningsDTO;
import com.capstone1.automatedpayroll.service.DeductionsServiceImpl;
import com.capstone1.automatedpayroll.service.EarningsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deductions")
@CrossOrigin("*")
public class DeductionsController {

    @Autowired
    private final DeductionsServiceImpl deductionsService;

    public DeductionsController(DeductionsServiceImpl deductionsService) {
        this.deductionsService = deductionsService;
    }
    @GetMapping("/{employeeId}")
    public List<DeductionsDTO> getDeductions(@PathVariable Long employeeId){
        return deductionsService.getDeductionsByEmployeeId(employeeId);
    }
    @PostMapping("/{employeeId}")
    public DeductionsDTO createDeductions(@PathVariable Long employeeId,@RequestBody DeductionsDTO deductionsDTO){
        return deductionsService.createDeduction(employeeId,deductionsDTO);
    }
    @PutMapping("/{earningsId}")
    public DeductionsDTO updateDeductions(@PathVariable Long deductionsId,@RequestBody DeductionsDTO deductionsDTO) {
        return deductionsService.updateDeduction(deductionsId, deductionsDTO);
    }
    @DeleteMapping("/{earningsId}")
    public void deleteDeduction(@PathVariable Long deductionsId) {
        deductionsService.deleteDeduction(deductionsId);
    }
}
