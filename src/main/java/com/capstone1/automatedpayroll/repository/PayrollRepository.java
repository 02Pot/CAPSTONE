package com.capstone1.automatedpayroll.repository;

import com.capstone1.automatedpayroll.model.DeductionsModel;
import com.capstone1.automatedpayroll.model.EmployeeModel;
import com.capstone1.automatedpayroll.model.PayrollModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PayrollRepository extends JpaRepository<PayrollModel,Long> {

    List<PayrollModel> findByEmployee(EmployeeModel employee);

    Optional<PayrollModel> findTopByEmployee_EIdOrderByDateProcessedDesc(Long aLong);
}
