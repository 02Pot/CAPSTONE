package com.capstone1.automatedpayroll.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone1.automatedpayroll.dto.PayslipDTO;
import com.capstone1.automatedpayroll.service.PayrollServiceImpl;
import com.capstone1.automatedpayroll.service.PayslipServiceImpl;

@RestController
@RequestMapping("/payroll")
@CrossOrigin("*")
public class PayrollController {

    @Autowired
    private PayrollServiceImpl payrollService;
    @Autowired
    private PayslipServiceImpl payslipService;


    @PostMapping("/run-cutoff")
    public Map<String, Object> runPayroll(){
        payrollService.runPayrollCurrentCutOff();

        Map<String,Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Payroll succesfully Processed");

        return response;
    }

    @GetMapping("/{payrollId}")
    public PayslipDTO getPayslip(@PathVariable Long payrollId){
        return payslipService.generatePayslip(payrollId);
    }

}
