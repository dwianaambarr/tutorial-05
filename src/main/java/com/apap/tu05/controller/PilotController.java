package com.apap.tu05.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tu05.model.FlightModel;
import com.apap.tu05.model.PilotModel;
import com.apap.tu05.service.FlightService;
import com.apap.tu05.service.PilotService;

/**
 * PilotController
 * 
 * @author Dwi Ana Ambar Rofiqoh
 *
 */

@Controller
public class PilotController {
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping("/")private String home(Model model) {
		String navigation = "HOME";
		model.addAttribute("navigation", navigation);
		return "home";
	}
	
	@RequestMapping(value = "/pilot/add", method = RequestMethod.GET)
	private String add(Model model) {
		String navigation = "Tambah Pilot";
		model.addAttribute("navigation", navigation);
		model.addAttribute("pilot", new PilotModel());
		return "addPilot";
	}
	
	@RequestMapping(value = "/pilot/add", method = RequestMethod.POST)
	private String addPilotSubmit(@ModelAttribute PilotModel pilot, @RequestParam(value = "licenseNumber") String licenseNumber, Model model) {
		PilotModel pilotCheck = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		if(pilotCheck != null) {
			String navigation = "Exist!";
			model.addAttribute("navigation", navigation);
			return "exist";
		}
		pilotService.addPilot(pilot);
		String navigation = "Berhasil!";
		model.addAttribute("navigation", navigation);
		return "add";
	}
	
	
	@RequestMapping(value = "/pilot/view")
	public String viewPilot(@RequestParam(value = "licenseNumber") String licenseNumber, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		String navigation = "View Pilot";
		model.addAttribute("navigation", navigation);
		model.addAttribute("pilot", pilot);
//		model.addAttribute("listFlight", pilot.getPilotFlight());
		return "view-pilot";
	}
	
//	@RequestMapping(value = "/pilot/view", method = RequestMethod.GET)
//	private String viewPilot(@RequestParam("licenseNumber")String licenseNumber, Model model) {
//		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
//		List<FlightModel> listFlight = pilot.getPilotFlight();
//		model.addAttribute("pilot", pilot);
//		model.addAttribute("pilotFlight", listFlight);
//		return "view-pilot";
//	}
	
	@RequestMapping(value = "/pilot/view/{licenseNumber}", method = RequestMethod.GET)
	private String viewPilot1(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		if(pilot == null) {
			String navigation = "Tidak Ditemukan!";
			model.addAttribute("navigation", navigation);
			return "notfound";
		}
		model.addAttribute("pilot", pilot);
		model.addAttribute("listFlight", pilot.getPilotFlight());
		return "view-pilot";
	}
//	

	@RequestMapping(value = "/pilot/delete/{licenseNumber}", method = RequestMethod.GET)
	private String deletePilot(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
			PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
			model.addAttribute("pilot", pilot);
			String navigation = "Hapus Pilot";
			model.addAttribute("navigation", navigation);
			return "deletePilot";
		}
	
	@RequestMapping(value = "/pilot/delete/{licenseNumber}", method = RequestMethod.POST)
	private String deletePilot1(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
			pilotService.deletePilot(licenseNumber);
			String navigation = "Berhasil!";
			model.addAttribute("navigation", navigation);
			return "delete";
		}
	
	@RequestMapping(value = "/pilot/update/{licenseNumber}", method = RequestMethod.GET)
	private String updatePilot(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		PilotModel oldPilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		String navigation = "Update Pilot";
		model.addAttribute("navigation", navigation);
		model.addAttribute("oldPilot", oldPilot);
		model.addAttribute("newPilot", new PilotModel());
		return "updatePilot";
	}
	
	@RequestMapping(value = "/pilot/update/{licenseNumber}", method = RequestMethod.POST)
	private String updatePilot(@ModelAttribute PilotModel newPilot, @PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		pilotService.updatePilot(licenseNumber, newPilot);
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		String navigation = "Berhasil";
		model.addAttribute("navigation", navigation);
		model.addAttribute("pilot", pilot);
		return "update";
	}
	
}
