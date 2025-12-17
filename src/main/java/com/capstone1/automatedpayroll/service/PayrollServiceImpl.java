package com.capstone1.automatedpayroll.service;

import com.capstone1.automatedpayroll.dto.EarningsDTO;
import com.capstone1.automatedpayroll.helper.PayrollDateUtils;
import com.capstone1.automatedpayroll.model.AttendanceModel;
import com.capstone1.automatedpayroll.model.DeductionsModel;
import com.capstone1.automatedpayroll.model.EarningsModel;
import com.capstone1.automatedpayroll.model.EmployeeModel;
import com.capstone1.automatedpayroll.repository.AttendanceRepository;
import com.capstone1.automatedpayroll.repository.DeductionsRepository;
import com.capstone1.automatedpayroll.repository.EarningsRepository;
import com.capstone1.automatedpayroll.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class PayrollServiceImpl {

    @Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EarningsRepository earningsRepository;
    @Autowired
    private DeductionsRepository deductionsRepository;
    @Autowired
    private PayrollDateUtils payrollDateUtils;
    @Autowired
    private EarningsServiceImpl earningsService;


    private double calculateNetPay(EmployeeModel employee, LocalDate startDate,LocalDate endDate){
        List<AttendanceModel> attendances = attendanceRepository
                .findByEmployee_EIdAndAttendanceDateBetween(employee.geteId(), startDate, endDate);

        double totalHours = attendances.stream()
                .mapToDouble(AttendanceModel::getHoursWorked)
                .sum();

        double basicPay = totalHours * employee.getEmployeeRate();

        List<EarningsDTO> stationaryEarnings = earningsService.getStationaryEarnings(employee.geteId());
        double stationaryTotal = stationaryEarnings.stream()
                .mapToDouble(EarningsDTO::getEarningsAmount)
                .sum();

        double nonStationaryTotal = calculateNonStationaryEarnings(employee.geteId(), startDate, endDate);
        double totalDeductions = calculateTotalDeductions(employee.geteId(), startDate, endDate);

        double netPay = basicPay + stationaryTotal + nonStationaryTotal - totalDeductions;
        return Math.round(netPay * 100.0) / 100.0;
    }

    private double calculateNonStationaryEarnings(Long employeeId,LocalDate startDate,LocalDate endDate){
        return earningsRepository.findByEmployeeEId(employeeId)
                .stream()
                .filter(e -> !e.isStationary())
                .filter(e -> !e.getDateCreated().isBefore(startDate) && !e.getDateCreated().isAfter(endDate))
                .mapToDouble(EarningsModel::getEarningAmount)
                .sum();
    }

    private double calculateTotalDeductions(Long employeeId,LocalDate startDate, LocalDate endDate){
        List<DeductionsModel> earnings = deductionsRepository.findByEmployeeEId(employeeId);
        return earnings.stream()
                .mapToDouble(DeductionsModel::getDeductionAmount)
                .sum();
    }

    private void clearNonStationaryEarnings(Long employeeId, LocalDate start, LocalDate end) {
        List<EarningsModel> toDelete = earningsRepository.findByEmployeeEId(employeeId)
                .stream()
                .filter(e -> !e.isStationary())
                .filter(e -> !e.getDateCreated().isBefore(start) && !e.getDateCreated().isAfter(end))
                .toList();

        earningsRepository.deleteAll(toDelete);
    }

    public void runPayrollCurrentCutOff(){
        Map.Entry<LocalDate,LocalDate> cutOff = payrollDateUtils.getCutOffPeriod(LocalDate.now());
        LocalDate startDate = cutOff.getKey();
        LocalDate endDate = cutOff.getValue();

        processPayroll(startDate,endDate);

        //TODO LOG for HISTORY
    }

    public void processPayroll(LocalDate startDate, LocalDate endDate){
        List<EmployeeModel> employees = employeeRepository.findAll();

        for (EmployeeModel employee : employees){

            clearNonStationaryEarnings(employee.geteId(),startDate,endDate);

            List<AttendanceModel> attendances = attendanceRepository
                    .findByEmployee_EIdAndAttendanceDateBetween(employee.geteId(), startDate, endDate);

            double totalHours = attendances.stream()
                    .mapToDouble(AttendanceModel::getHoursWorked)
                    .sum();

            // 3. Create Basic Pay as non-stationary earnings
            EarningsDTO basicPayDTO = new EarningsDTO();
            basicPayDTO.setEmployeeId(employee.geteId());
            basicPayDTO.setEarningsName("Basic Pay");
            basicPayDTO.setEarningsAmount(totalHours * employee.getEmployeeRate());
            basicPayDTO.setStationary(false); // non-stationary so it expires after cutoff
            earningsService.createEarnings(employee.geteId(), basicPayDTO);

            double netPay = calculateNetPay(employee, startDate, endDate);

            System.out.println("Processed payroll for " + employee.getEmployeeFirstName()+employee.getEmployeeLastName() +
                    " | Net Pay: " + netPay);
        }
    }

    @Scheduled(cron = "0 0 0 * * ?") // runs every day at midnight
    public void runScheduledPayroll() {
        LocalDate today = LocalDate.now();
        int day = today.getDayOfMonth();
        int lastDayOfMonth = today.lengthOfMonth();

        // Only run payroll on cutoff days: 15th or last day
        if (day == 15 || day == lastDayOfMonth) {
            System.out.println("Running scheduled payroll for cutoff: " + today);
            runPayrollCurrentCutOff();
        } else {
            System.out.println("Today is not a cutoff. Payroll will not run.");
        }
    }

}
