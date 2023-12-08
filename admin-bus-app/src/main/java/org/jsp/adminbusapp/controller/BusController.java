package org.jsp.adminbusapp.controller;

import java.time.LocalDate;
import java.util.List;

import org.jsp.adminbusapp.dto.Bus;
import org.jsp.adminbusapp.dto.ResponseStructure;
import org.jsp.adminbusapp.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusController {
	@Autowired
	private BusService busService;

	@PostMapping("/buses/{admin_id}")
	public ResponseEntity<ResponseStructure<Bus>> saveBus(@RequestBody Bus bus,@PathVariable int admin_id) {
		return busService.saveBus(bus, admin_id);
	}
	
	@PutMapping("/buses")
	public ResponseEntity<ResponseStructure<Bus>> updateBus(@RequestBody Bus bus) {
		return busService.updateBus(bus);
	}
	
	@GetMapping("/buses/location")
	public ResponseEntity<ResponseStructure<List<Bus>>> findByDateFromTo(@RequestParam String date,
			@ RequestParam String from_location,@RequestParam String to_location) {
		LocalDate date_of_departure=LocalDate.parse(date);
		return busService.findByDateFromTo(date_of_departure, from_location, to_location);
	}
	@GetMapping("/buses/admin/{id}")
	public ResponseEntity<ResponseStructure<List<Bus>>> findByAdminId(@PathVariable int id) {
		return busService.findByAdminId(id);
	}
	@GetMapping("/buses/number/{bus_number}")
	public ResponseEntity<ResponseStructure<Bus>> findByBusNo(@PathVariable String bus_number){
		return busService.findByBusNo(bus_number);
	}
	
	@GetMapping("/buses/travels/{travels_name}")
	public ResponseEntity<ResponseStructure<List<Bus>>> findByTravels(@PathVariable String travels_name) {
		return busService.findByTravels(travels_name);
	}
	
	@GetMapping("/buses/date/{date_of_departure}")
	public ResponseEntity<ResponseStructure<List<Bus>>> findBusByDate(@PathVariable LocalDate date_of_departure) {
		return busService.findBusByDate(date_of_departure);
	}
}
