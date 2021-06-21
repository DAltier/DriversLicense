package com.codingdojo.danaaltier.driversLicense.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.danaaltier.driversLicense.models.License;
import com.codingdojo.danaaltier.driversLicense.repositories.LicenseRepository;

@Service
public class LicenseService {
	
	// Adding the license repository as a dependency
	private final LicenseRepository licenseRepo;
	
	
	private static String newNumber = "000000";
	
	
	// Constructor
	public LicenseService(LicenseRepository licenseRepo) {
		this.licenseRepo = licenseRepo;
	}
	
	
	
	// Create a new license
	public License createLicense(License license) {
		newNumber = String.format("%06d", Integer.parseInt(newNumber) + 1);
		license.setNumber(newNumber);
		return licenseRepo.save(license);
	}
	
	
	// Find license by Id
	public License findLicense(Long id) {
		Optional<License> findL = licenseRepo.findById(id);
		if (findL.isPresent()) {
			return findL.get();
		} else {
			return null;
		}
	}
}
