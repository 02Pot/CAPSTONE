package com.capstone1.automatedpayroll.controller;

import com.capstone1.automatedpayroll.dto.EarningsDTO;
import com.capstone1.automatedpayroll.model.EarningsModel;
import com.capstone1.automatedpayroll.service.EarningsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/earnings")
@CrossOrigin("*")
public class EarningsController {

    @Autowired
    private final EarningsServiceImpl earningsService;

    public EarningsController(EarningsServiceImpl earningsService) {
        this.earningsService = earningsService;
    }
    @GetMapping("/{employeeId}")
    public List<EarningsDTO> getEarnings(@PathVariable Long employeeId){
        return earningsService.getEarningsByEmployeeId(employeeId);
    }
    @PostMapping("/{employeeId}")
    public EarningsDTO createEarnings(@PathVariable Long employeeId,@RequestBody EarningsDTO earningsDTO){
        return earningsService.createEarnings(employeeId,earningsDTO);
    }
    @PutMapping("/{earningsId}")
    public EarningsDTO updateEarnings(@PathVariable Long earningsId,@RequestBody EarningsDTO earningsDTO) {
        return earningsService.updateEarning(earningsId, earningsDTO);
    }
    @DeleteMapping("/{earningsId}")
    public void deleteEarnings(@PathVariable Long earningsId) {
        earningsService.deleteEarning(earningsId);
    }
}
