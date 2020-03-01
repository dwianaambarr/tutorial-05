package com.apap.tu05.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tu05.model.PilotModel;
import com.apap.tu05.repository.PilotDb;

/**
 * PilotServiceImpl
 * 
 * @author Dwi Ana Ambar Rofiqoh
 *
 */

@Service
@Transactional
public class PilotServiceImpl implements PilotService{
	@Autowired
	private PilotDb pilotDb;
	
	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		return pilotDb.findByLicenseNumber(licenseNumber);
	}
	
	@Override
	public PilotModel getPilotDetailById(long id) {
		return pilotDb.findById(id);
	}
	
	@Override
	public void addPilot(PilotModel pilot) {
		pilotDb.save(pilot);
	}
	
	@Override
	public void deletePilot(String licenseNumber) {
		pilotDb.deleteByLicenseNumber(licenseNumber);
	}

	@Override
	public void updatePilot(String licenseNumber, PilotModel pilot) {
		PilotModel oldPilot = this.getPilotDetailByLicenseNumber(licenseNumber);
		oldPilot.setName(pilot.getName());
		oldPilot.setFlyHour(pilot.getFlyHour());
	}
}
