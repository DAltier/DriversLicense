package com.codingdojo.danaaltier.driversLicense.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.danaaltier.driversLicense.models.License;


@Repository
public interface LicenseRepository extends CrudRepository<License, Long> {
	
	List<License> findAll();
	
}