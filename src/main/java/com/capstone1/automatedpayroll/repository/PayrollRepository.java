package com.capstone1.automatedpayroll.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone1.automatedpayroll.model.EmployeeModel;
import com.capstone1.automatedpayroll.model.PayrollModel;

public interface PayrollRepository extends JpaRepository<PayrollModel,Long> {

    List<PayrollModel> findByEmployee(EmployeeModel employee);

    Optional<PayrollModel> findTopByEmployee_EIdOrderByDateProcessedDesc(Long aLong);
}
