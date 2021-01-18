package com.kgisl.twofa.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kgisl.twofa.model.Employee;
import com.kgisl.twofa.service.IEmployeeService;

/**
 * The Class EmployeeServiceImpl.
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {
	
	/** The repo. */
	@Autowired
	private EmployeeRepository repo;

	/**
	 * Creates the employee.
	 *
	 * @param employee the employee
	 */
	@Override
	public void createEmployee(Employee employee) {
		repo.save(employee);
	}
	
	/**
	 * Gets the all employees.
	 *
	 * @return the all employees
	 */
	@Override
	public List<Employee> getAllEmployees() {
		return repo.findAll();
	}

	/**
	 * Gets the one employee.
	 *
	 * @param id the id
	 * @return the one employee
	 */
	@Override
	public Optional<Employee> getOneEmployee(Integer id) {
		return repo.findById(id);
	}
}
