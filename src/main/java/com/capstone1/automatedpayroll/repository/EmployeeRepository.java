package com.capstone1.automatedpayroll.repository;

import com.capstone1.automatedpayroll.model.EmployeeModel;
import com.capstone1.automatedpayroll.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeModel,Long> {

    @Override
    Optional<EmployeeModel> findById(Long UserId);
    UserModel findByEmployeeEmail(String userEmail);
    boolean existsByEmployeeEmail(String userEmail);

}
