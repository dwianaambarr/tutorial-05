package com.apap.tu05.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tu05.model.PilotModel;

/**
 * *PilotDb
 *
 * @author Dwi Ana Ambar Rofiqoh
 *
 */

public interface PilotDb extends JpaRepository<PilotModel, Long>{
	PilotModel findByLicenseNumber(String licenseNumber);
	PilotModel findById(long id);
	void deleteByLicenseNumber(String licenseNumber);
}
