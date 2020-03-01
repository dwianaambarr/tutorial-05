package com.apap.tu05.service;

import java.util.List;

import com.apap.tu05.model.FlightModel;

/**
 * FlightService
 * 
 * @author Dwi Ana Ambar Rofiqoh
 *
 */

public interface FlightService {
	void addFlight(FlightModel flight);
	List<FlightModel> getAllFlight();
	void deleteFlight(String flightNumber);
	FlightModel getFlightDetailByFlightNumber(String flightNumber);
	FlightModel getFlightDetailById(long id);
	void updateFlight(String flightNumber, FlightModel flight);
	void deleteFlightById(long id);
}
