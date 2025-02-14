package com.zidio.enterprise_expense_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import com.zidio.enterprise_expense_management_system.dao.AdminDao;
import com.zidio.enterprise_expense_management_system.dto.Admin;
import com.zidio.enterprise_expense_management_system.exception.EmailNotFoundException;
import com.zidio.enterprise_expense_management_system.exception.IncorrectPasswordException;
import com.zidio.enterprise_expense_management_system.util.ResponseStructure;

@Service
public class AdminService {
	@Autowired
	private AdminDao adminDao;

	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(Admin admin) {
		ResponseStructure<Admin> structure = new ResponseStructure<Admin>();
		structure.setMessage("Signup Success");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(adminDao.saveAdmin(admin));
		return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.CREATED);

	}

	public ResponseEntity<ResponseStructure<Admin>> loginAdmin(String email, String password) {
		Admin admin = adminDao.findByAdminEmail(email);
		ResponseStructure<Admin> responseStructure = new ResponseStructure<Admin>();
		if (admin != null) {
			if (password.equals(admin.getAdminPassword())) {
				responseStructure.setMessage("Login Successfully");
				responseStructure.setStatus(HttpStatus.OK.value());
				responseStructure.setData(admin);
				return new ResponseEntity<ResponseStructure<Admin>>(responseStructure, HttpStatus.OK);
			} else {
				throw new IncorrectPasswordException("Incorrect password");
			}

		} else {
			throw new EmailNotFoundException("Given admin email is incorrect");
		}
	}
}
