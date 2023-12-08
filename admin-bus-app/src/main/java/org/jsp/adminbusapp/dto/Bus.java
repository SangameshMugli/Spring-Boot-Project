package org.jsp.adminbusapp.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Bus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String bus_number;
	@Column(nullable = false)
	private LocalDate date_of_departure;
	@Column(nullable = false)
	private int no_of_seats;
	@Column(nullable = false)
	private double cost_per_seat;
	@Column(nullable = false)
	private String from_location;
	@Column(nullable = false)
	private String to_location;
	@Column(nullable = false)
	private String image_url;
	@ManyToOne
	@JoinColumn(name="admin_id")
	private Admin admin;

	@JsonIgnore
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBus_number() {
		return bus_number;
	}

	public void setBus_number(String bus_number) {
		this.bus_number = bus_number;
	}

	public LocalDate getDate_of_departure() {
		return date_of_departure;
	}

	public void setDate_of_departure(LocalDate date_of_departure) {
		this.date_of_departure = date_of_departure;
	}

	public int getNo_of_seats() {
		return no_of_seats;
	}

	public void setNo_of_seats(int no_of_seats) {
		this.no_of_seats = no_of_seats;
	}

	public double getCost_per_seat() {
		return cost_per_seat;
	}

	public void setCost_per_seat(double cost_per_seat) {
		this.cost_per_seat = cost_per_seat;
	}

	public String getFrom_location() {
		return from_location;
	}

	public void setFrom_location(String from_location) {
		this.from_location = from_location;
	}

	public String getTo_location() {
		return to_location;
	}

	public void setTo_location(String to_location) {
		this.to_location = to_location;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
}
