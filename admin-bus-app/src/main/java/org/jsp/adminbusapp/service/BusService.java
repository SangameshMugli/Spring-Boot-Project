package org.jsp.adminbusapp.service;

import java.lang.StackWalker.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.jsp.adminbusapp.dao.AdminDao;
import org.jsp.adminbusapp.dao.BusDao;
import org.jsp.adminbusapp.dto.Admin;
import org.jsp.adminbusapp.dto.Bus;
import org.jsp.adminbusapp.dto.ResponseStructure;
import org.jsp.adminbusapp.exception.AdminNotFoundException;
import org.jsp.adminbusapp.exception.BusNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BusService {
	@Autowired
	private BusDao busDao;

	@Autowired
	private AdminDao adminDao;

	public ResponseEntity<ResponseStructure<Bus>> saveBus(Bus bus, int admin_id) {
		ResponseStructure<Bus> structure = new ResponseStructure<>();
		Optional<Admin> recAdmin = adminDao.findAdminById(admin_id);
		if (recAdmin.isPresent()) {
			Admin admin = recAdmin.get();
			admin.getBuses().add(bus);
			bus.setAdmin(admin);
			adminDao.saveAdmin(admin);
			structure.setData(busDao.saveBus(bus));
			structure.setMessage("Bus Saved Successfully");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Bus>>(structure, HttpStatus.CREATED);
		}
		throw new AdminNotFoundException();

	}

	public ResponseEntity<ResponseStructure<Bus>> updateBus(Bus bus) {
		ResponseStructure<Bus> structure = new ResponseStructure<>();
		Optional<Bus> recBus = busDao.findById(bus.getId());
		if (recBus.isPresent()) {
			Bus dbBus = recBus.get();
			dbBus.setBus_number(bus.getBus_number());
			dbBus.setCost_per_seat(bus.getCost_per_seat());
			dbBus.setDate_of_departure(bus.getDate_of_departure());
			dbBus.setFrom_location(bus.getFrom_location());
			dbBus.setTo_location(bus.getTo_location());
			dbBus.setNo_of_seats(bus.getNo_of_seats());
			dbBus.setImage_url(bus.getImage_url());
			structure.setData(busDao.saveBus(bus));
			structure.setMessage("Bus updated Successfully");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Bus>>(structure, HttpStatus.ACCEPTED);
		}
		throw new BusNotFoundException("Bus Not Found");

	}

	public ResponseEntity<ResponseStructure<List<Bus>>> findByDateFromTo(LocalDate date_of_departure,
			String from_location, String to_location) {
		ResponseStructure<List<Bus>> structure = new ResponseStructure<>();
		List<Bus> buses = busDao.findByDateFromTo(date_of_departure, from_location, to_location);
		if (buses.size() > 0) {
			structure.setData(buses);
			structure.setMessage("Buses found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Bus>>>(structure, HttpStatus.OK);

		}
		throw new BusNotFoundException("Bus Not found with given data");
	}
	
	public ResponseEntity<ResponseStructure<List<Bus>>> findByAdminId(int id) {
		ResponseStructure<List<Bus>> structure = new ResponseStructure<>();
		List<Bus> buses = busDao.findByAdminId(id);
		if (buses.size() > 0) {
			structure.setData(buses);
			structure.setMessage("Buses found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Bus>>>(structure, HttpStatus.OK);

		}
		throw new BusNotFoundException("bus not found with given admin id");
	}
	
	public ResponseEntity<ResponseStructure<Bus>> findByBusNo(String bus_number){
		ResponseStructure<Bus> structure=new ResponseStructure<>();
		Optional<Bus> recBus=busDao.findByBusNo(bus_number);
		if(recBus.isPresent()) {
			structure.setData(recBus.get());
			structure.setMessage("Bus found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Bus>>(structure,HttpStatus.OK);
		}
		throw new BusNotFoundException("Invalid Bus number");
	}
	
	public ResponseEntity<ResponseStructure<List<Bus>>> findByTravels(String travels_name) {
		ResponseStructure<List<Bus>> structure = new ResponseStructure<>();
		List<Bus> buses = busDao.findByTravels(travels_name);
		if (buses.size() > 0) {
			structure.setData(buses);
			structure.setMessage("Buses found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Bus>>>(structure, HttpStatus.OK);

		}
		throw new BusNotFoundException("Invalid Travels name");
	
	
		
	}
	
	public ResponseEntity<ResponseStructure<List<Bus>>> findBusByDate(LocalDate date_of_departure) {
		ResponseStructure<List<Bus>> structure = new ResponseStructure<>();
		List<Bus> buses = busDao.findBusByDate(date_of_departure);
		if (buses.size() > 0) {
			structure.setData(buses);
			structure.setMessage("Buses found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Bus>>>(structure, HttpStatus.OK);

		}
		 throw new BusNotFoundException("Invalid date of departure ");
	
	
		
	}

}
