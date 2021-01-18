package com.kgisl.twofa.service;

import java.util.List;
import java.util.Optional;

import com.kgisl.twofa.model.Employee;


/**
 * The Interface IEmployeeService.
 */
public interface IEmployeeService {

	/**
	 * Creates the employee.
	 *
	 * @param employee the employee
	 */
	void createEmployee(Employee employee);

	/**
	 * Gets the all employees.
	 *
	 * @return the all employees
	 */
	List<Employee> getAllEmployees();

	/**
	 * Gets the one employee.
	 *
	 * @param id the id
	 * @return the one employee
	 */
	Optional<Employee> getOneEmployee(Integer id);
}
