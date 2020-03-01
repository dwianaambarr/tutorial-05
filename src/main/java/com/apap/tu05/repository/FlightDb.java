package com.apap.tu05.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tu05.model.FlightModel;

/**
 * FlightDb
 * 
 * @author Dwi Ana Ambar Rofiqoh
 *
 */

public interface FlightDb extends JpaRepository<FlightModel, Long>{
	FlightModel findByFlightNumber(String flightNumber);
	FlightModel findById(long id);
}
