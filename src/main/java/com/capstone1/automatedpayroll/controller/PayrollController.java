package com.capstone1.automatedpayroll.controller;


import com.capstone1.automatedpayroll.dto.PayrollPeriodDTO;
import com.capstone1.automatedpayroll.helper.PayrollDateUtils;
import com.capstone1.automatedpayroll.service.PayrollServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/payroll")
@CrossOrigin
public class PayrollController {

    @Autowired
    private PayrollServiceImpl payrollService;
    @Autowired
    private PayrollDateUtils payrollDateUtils;

    @PostMapping("/run-cutoff")
    public Map<String, Object> runPayroll(){
        payrollService.runPayrollCurrentCutOff();

        Map<String,Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Payroll succesfully Processed");

        return response;
    }

    @GetMapping("/payroll-period")
    public PayrollPeriodDTO getCurrentPayrollPeriod(){
        Map.Entry<LocalDate,LocalDate> cutOff = payrollDateUtils.getCutOffPeriod(LocalDate.now());
        return new PayrollPeriodDTO(cutOff.getKey(),cutOff.getValue());
    }

}
