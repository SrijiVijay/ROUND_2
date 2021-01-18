package com.kgisl.twofa.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kgisl.twofa.model.Employee;

/**
 * The Interface EmployeeRepository.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
