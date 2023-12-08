package org.jsp.adminbusapp.service;

import java.util.Optional;

import org.jsp.adminbusapp.dao.AdminDao;
import org.jsp.adminbusapp.dto.Admin;
import org.jsp.adminbusapp.dto.ResponseStructure;
import org.jsp.adminbusapp.exception.AdminNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

	@Autowired
	private AdminDao adminDao;

	public ResponseEntity<ResponseStructure<Admin>> saveAdmin(Admin admin) {
		ResponseStructure<Admin> structure = new ResponseStructure<>();
		structure.setData(adminDao.saveAdmin(admin));
		structure.setMessage("Admin added successfully");
		structure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Admin>> updateAdmin(Admin admin) {
		ResponseStructure<Admin> structure = new ResponseStructure<>();
		Optional<Admin> recAdmin = adminDao.findAdminById(admin.getId());
		if (recAdmin.isPresent()) {
			Admin dbAdmin = recAdmin.get();
			dbAdmin.setName(admin.getName());
			dbAdmin.setEmail(admin.getEmail());
			dbAdmin.setGst_no(admin.getGst_no());
			dbAdmin.setPhone(admin.getPhone());
			dbAdmin.setTravels_name(admin.getTravels_name());
			dbAdmin.setPassword(admin.getPassword());
			structure.setData(adminDao.saveAdmin(admin));
			structure.setMessage("Admin Updated Successfully");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.ACCEPTED);
		} else {
			throw new AdminNotFoundException();
		}
	}

	public ResponseEntity<ResponseStructure<Admin>> loginByPhone(long phone, String password) {
		ResponseStructure<Admin> structure = new ResponseStructure<>();
		Optional<Admin> dbAdmin = adminDao.loginByPhone(phone, password);
		if (dbAdmin.isPresent()) {
			structure.setData(dbAdmin.get());
			structure.setMessage("Admin login successfully");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.OK);
		}
		throw new AdminNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Admin>> loginByEmail(String email, String password) {
		ResponseStructure<Admin> structure = new ResponseStructure<>();
		Optional<Admin> dbAdmin = adminDao.loginByEmail(email, password);
		if (dbAdmin.isPresent()) {
			structure.setData(dbAdmin.get());
			structure.setMessage("Admin login successfully");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.OK);
		}
		throw new AdminNotFoundException();
	}
	
	public ResponseEntity<ResponseStructure<Admin>> findAdminById(int id){
		ResponseStructure<Admin> structure=new ResponseStructure<>();
		Optional<Admin> dbAdmin=adminDao.findAdminById(id);
		if(dbAdmin.isPresent()) {
			structure.setData(dbAdmin.get());
			structure.setMessage("Admin login successfully");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.OK);
		}
		throw new AdminNotFoundException();
		
	}

}
