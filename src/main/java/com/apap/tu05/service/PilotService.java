package com.apap.tu05.service;

import com.apap.tu05.model.PilotModel;

/**
 * PilotService
 * 
 * @author Dwi Ana Ambar Rofiqoh
 *
 */
public interface PilotService {
	PilotModel getPilotDetailByLicenseNumber(String licenseNumber);
	PilotModel getPilotDetailById(long id);
	void addPilot(PilotModel pilot);
	void deletePilot(String licenseNumber);
	void updatePilot(String licenseNumber, PilotModel pilot);

}
