package com.apap.tu05.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.tu05.model.FlightModel;
import com.apap.tu05.model.PilotModel;
import com.apap.tu05.service.FlightService;
import com.apap.tu05.service.PilotService;

import java.util.List;

/**
 * FlightController
 * 
 * @author Dwi Ana Ambar Rofiqoh
 *
 */

@Controller
public class FlightController {
	@Autowired
	private FlightService flightService;
	
	@Autowired
	private PilotService pilotService;
	
	
	@RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
	private String add(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		FlightModel flight = new FlightModel();
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		flight.setPilot(pilot);
		model.addAttribute("flight", flight);
		return "addFlight";
	}
	
	@RequestMapping(value = "/flight/add", method = RequestMethod.POST)
	private String addFlightSubmit(@ModelAttribute FlightModel flight) {
		flightService.addFlight(flight);
		return "add";
	}
	
	@RequestMapping(value = "/flight/delete", method = RequestMethod.POST)
	private String deleteFlight(@ModelAttribute PilotModel pilot, Model model) {
		for(FlightModel flight : pilot.getPilotFlight()) {
			flightService.deleteFlightById(flight.getId());
		}
		return "delete";
	}
//		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
//		FlightModel flight = flightService.getFlightDetailByFlightNumber(flightNumber);
//		model.addAttribute("pilot", pilot);
//		model.addAttribute("flight", flight);
//		return "deleteFlight";
//	}
//	
//	@RequestMapping(value = "/pilot/{licenseNumber}/flight/delete/{flightNumber}", method = RequestMethod.POST)
//	private String deleteFlight(@PathVariable(value = "flightNumber") String flightNumber, @PathVariable(value = "licenseNumber") String licenseNumber) {
//			//String license = pilot.getLicenseNumber();
//			flightService.deleteFlight(flightNumber);
//			return "delete";
//		}
	
	@RequestMapping(value = "/pilot/{licenseNumber}/flight/update/{flightNumber}", method = RequestMethod.GET)
	private String updateFlight(@PathVariable (value = "licenseNumber") String licenseNumber, @PathVariable(value = "flightNumber") String flightNumber, Model model) {
		FlightModel oldFlight = flightService.getFlightDetailByFlightNumber(flightNumber);
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		model.addAttribute("oldFlight",  oldFlight);
		model.addAttribute("pilot", pilot);
		model.addAttribute("newFlight", new FlightModel());
		return "updateFlight";
	}
	
	@RequestMapping(value = "pilot/{licenseNumber}/flight/update/{flightNumber}", method = RequestMethod.POST)
	private String updateFlightSubmit(@ModelAttribute FlightModel newFlight, @PathVariable (value="licenseNumber") String licenseNumber, @PathVariable(value = "flightNumber") String flightNumber, Model model) {
		flightService.updateFlight(flightNumber, newFlight);
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		model.addAttribute("newPilot", pilot);
		return "update";
	}
	
	// Latihan 5
    // Menampilkan seluruh daftar penerbangan
	@RequestMapping(value = "/listFlights", method = RequestMethod.GET)
	private String viewFlights(Model model) {
		List<FlightModel> penerbangan = flightService.getAllFlight();
		model.addAttribute("flight", penerbangan);
		return "listAllFlight";
	}
	
}
