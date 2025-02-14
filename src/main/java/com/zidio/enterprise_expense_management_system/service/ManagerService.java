package com.zidio.enterprise_expense_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.zidio.enterprise_expense_management_system.dao.ManagerDao;
import com.zidio.enterprise_expense_management_system.dto.Manager;
import com.zidio.enterprise_expense_management_system.exception.EmailNotFoundException;
import com.zidio.enterprise_expense_management_system.exception.IncorrectPasswordException;
import com.zidio.enterprise_expense_management_system.util.ResponseStructure;

@Service
public class ManagerService {
	@Autowired
	private ManagerDao managerDao;

	public ResponseEntity<ResponseStructure<Manager>> saveManager(Manager manager) {
		ResponseStructure<Manager> structure = new ResponseStructure<Manager>();
		structure.setMessage("Signup Success");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(managerDao.saveManager(manager));
		return new ResponseEntity<ResponseStructure<Manager>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Manager>> loginManager(String email, String password) {
		Manager manager = managerDao.findByManagerEmail(email);
		ResponseStructure<Manager> responseStructure = new ResponseStructure<>();
		if (manager != null) {
			if (password.equals(manager.getManagerPassword())) {
				responseStructure.setMessage("Login Successfully");
				responseStructure.setStatus(HttpStatus.OK.value());
				responseStructure.setData(manager);
				return new ResponseEntity<>(responseStructure, HttpStatus.OK);
			} else {
				throw new IncorrectPasswordException(" Given Password is Incorrect");
			}
		} else {
			throw new EmailNotFoundException("Given manager email is incorrect");
		}
	}
}
