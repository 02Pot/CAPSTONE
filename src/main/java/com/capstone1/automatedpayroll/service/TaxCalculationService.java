package com.capstone1.automatedpayroll.service;

public class TaxCalculationService {

    public double calculateWithHoldingTax(double monthlySalary,double totalStatutoryDeductions){
        double taxableIncome = monthlySalary - totalStatutoryDeductions;

        if (taxableIncome <= 20833.33){
            return 0;
        }
        //Pag above 20,833
        double excess = taxableIncome - 20833.33;
        return excess * 0.15;
    }
    //Per CutOFF
    public double calculatePagIbig(double monthlySalary){
        double rate = 0;
        if (monthlySalary <= 1500){
            rate = 0.01;
        }else {
            rate = 0.02;
        }
        double contribution = monthlySalary * rate;
        double monthlyDeduction = Math.min(contribution,200.0);
        return monthlyDeduction / 2;
    }

    public double calculatePhilHealth(double monthlySalary){
        double salaryBase = Math.max(10000.0, Math.min(monthlySalary, 100000.0));
        double totalMonthlyPremium = salaryBase * 0.05;
        return (totalMonthlyPremium / 2) / 2;
    }

    public double calculateGSIS(double monthlySalary){
        double contribution = monthlySalary * 0.09;
        return contribution / 2;
    }
}
