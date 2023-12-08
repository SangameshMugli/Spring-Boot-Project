package org.jsp.adminbusapp.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.jsp.adminbusapp.dto.Bus;
import org.jsp.adminbusapp.repository.AdminRepository;
import org.jsp.adminbusapp.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BusDao {

	@Autowired
	private BusRepository busRepository;

	@Autowired
	private AdminRepository adminRepository;

	public Bus saveBus(Bus bus) {
		return busRepository.save(bus);
	}
	
	public Optional<Bus> findById(int id){
		return busRepository.findById(id);
	}

	public Bus updateBus(Bus bus) {
		return busRepository.save(bus);
	}

	public List<Bus> findByDateFromTo(LocalDate date_of_departure, String from_location, String to_location) {
		return busRepository.findByDateFromTo(date_of_departure, from_location, to_location);
	}

	public List<Bus> findByAdminId(int id) {
		return busRepository.findByAdminId(id);
	}

	public Optional<Bus> findByBusNo(String bus_number) {
		return busRepository.findByBusNo(bus_number);
	}

	public List<Bus> findByTravels(String travels_name) {
		return busRepository.findByTravels(travels_name);
	}

	public List<Bus> findBusByDate(LocalDate date_of_departure) {
		return busRepository.findBusByDate(date_of_departure);
	}
}
