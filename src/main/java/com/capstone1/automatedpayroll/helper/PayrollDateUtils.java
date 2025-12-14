package com.capstone1.automatedpayroll.helper;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.AbstractMap;
import java.util.Map;

@Component
public class PayrollDateUtils {

    public Map.Entry<LocalDate, LocalDate> getCutOffPeriod(LocalDate referenceDate){
        int dayOfMonth = referenceDate.getDayOfMonth();
        int month = referenceDate.getMonthValue();
        int year = referenceDate.getYear();

        LocalDate startDate;
        LocalDate endDate;

        if (dayOfMonth >= 1 && dayOfMonth <= 15) {
            startDate = LocalDate.of(year, month, 1);
            endDate = LocalDate.of(year, month, 15);
        } else {
            startDate = LocalDate.of(year, month, 16);

            endDate = YearMonth.of(year, month).atEndOfMonth();
        }

        return new AbstractMap.SimpleEntry<>(startDate, endDate);
    }
}
