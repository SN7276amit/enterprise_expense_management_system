package com.zidio.enterprise_expense_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.zidio.enterprise_expense_management_system.dao.EmployeeDao;
import com.zidio.enterprise_expense_management_system.dto.Employee;
import com.zidio.enterprise_expense_management_system.exception.EmailNotFoundException;
import com.zidio.enterprise_expense_management_system.exception.IncorrectPasswordException;
import com.zidio.enterprise_expense_management_system.util.ResponseStructure;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeDao employeeDao;

	public ResponseEntity<ResponseStructure<Employee>> saveEmployee(Employee employee) {
		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		structure.setMessage("Signup Success");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(employeeDao.saveEmployee(employee));
		return new ResponseEntity<ResponseStructure<Employee>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Employee>> loginEmployee(String email, String password) {
		Employee employee = employeeDao.findByEmployeeEmail(email);
		ResponseStructure<Employee> responseStructure = new ResponseStructure<>();
		if (employee != null) {
			if (password.equals(employee.getEmployeePassword())) {
				responseStructure.setMessage("Login Successfully");
				responseStructure.setStatus(HttpStatus.OK.value());
				responseStructure.setData(employee);
				return new ResponseEntity<ResponseStructure<Employee>>(responseStructure, HttpStatus.OK);
			} else {
				throw new IncorrectPasswordException("incorrect password");
			}
		} else {
			throw new EmailNotFoundException("Given employee email is incorrect");
		}

	}
}
