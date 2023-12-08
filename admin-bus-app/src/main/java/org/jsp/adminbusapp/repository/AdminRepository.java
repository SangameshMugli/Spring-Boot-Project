package org.jsp.adminbusapp.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.adminbusapp.dto.Admin;
import org.jsp.adminbusapp.dto.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<Admin, Integer>{

	@Query("select a from Admin a where a.phone=?1 and a.password=?2")
	public Optional<Admin>  loginByPhone(long phone,String password);
	
	@Query("select a from Admin a where a.email=?1 and a.password=?2")
	public Optional<Admin> loginByEmail(String email,String password);
	
	
 }
