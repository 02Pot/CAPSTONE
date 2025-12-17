package com.capstone1.automatedpayroll.controller;


import com.capstone1.automatedpayroll.dto.PayrollPeriodDTO;
import com.capstone1.automatedpayroll.dto.PayslipDTO;
import com.capstone1.automatedpayroll.helper.PayrollDateUtils;
import com.capstone1.automatedpayroll.service.PayrollServiceImpl;
import com.capstone1.automatedpayroll.service.PayslipServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/payroll")
@CrossOrigin("*")
public class PayrollController {

    @Autowired
    private PayrollServiceImpl payrollService;
    @Autowired
    private PayrollDateUtils payrollDateUtils;
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
