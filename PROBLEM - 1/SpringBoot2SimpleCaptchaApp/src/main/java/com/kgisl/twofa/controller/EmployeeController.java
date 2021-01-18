package com.kgisl.twofa.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kgisl.twofa.captcha.CaptchaUtils;
import com.kgisl.twofa.model.Employee;
import com.kgisl.twofa.service.IEmployeeService;

import cn.apiclub.captcha.Captcha;

/**
 * The Class EmployeeController.
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

	/** The service. */
	@Autowired
	private IEmployeeService service;

	/**
	 * Sets the up captcha.
	 *
	 * @param employee the new up captcha
	 */
	private void setupCaptcha(Employee employee) {
		Captcha captcha = CaptchaUtils.createCaptcha(200, 50);
		employee.setHidden(captcha.getAnswer());
		employee.setCaptcha("");
		employee.setImage(CaptchaUtils.encodeBase64(captcha));
	}

	/**
	 * Method to register new user.
	 *
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/register")
	public String showRegister(Model model) {
		Employee e = new Employee();
		setupCaptcha(e);
		model.addAttribute("employee", e);

		return "EmployeeRegister";
	}

	/**
	 * Method to Save employee.
	 *
	 * @param employee the employee
	 * @param model    the model
	 * @return the string
	 */
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee employee, Model model) {
		String page = "";
		if (employee.getCaptcha().equals(employee.getHidden())) {
			service.createEmployee(employee);
			page = "redirect:all";
		} else {
			setupCaptcha(employee);
			return "EmployeeRegister";
		}
		return page;
	}

	/**
	 * Method to Get the all employees.
	 *
	 * @param model the model
	 * @return the all employees
	 */
	@GetMapping("/all")
	public String getAllEmployees(Model model) {
		model.addAttribute("list", service.getAllEmployees());
		return "EmployeeData";
	}

	/**
	 * Method to Edit the employee details.
	 *
	 * @param id    the id
	 * @param model the model
	 * @return the string
	 */
	@GetMapping("/edit/{id}")
	public String editEmployees(@PathVariable Integer id, Model model) {
		String page = null;
		Optional<Employee> opt = service.getOneEmployee(id);
		if (opt.isPresent()) {
			Employee e = opt.get();
			setupCaptcha(e);
			model.addAttribute("employee", e);
			page = "EmployeeRegister";
		} else {
			page = "redirect:all";
		}

		return page;
	}
}
