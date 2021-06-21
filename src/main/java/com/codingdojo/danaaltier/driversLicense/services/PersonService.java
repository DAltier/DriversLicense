package com.codingdojo.danaaltier.driversLicense.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.danaaltier.driversLicense.models.Person;
import com.codingdojo.danaaltier.driversLicense.repositories.PersonRepository;

@Service
public class PersonService {
	
	// Adding the person repository as dependency 
	private final PersonRepository personRepo;
	
	
	// Constructor
	public PersonService(PersonRepository personRepo) {
		this.personRepo = personRepo;
	}
	
	
	// Create new person
	public Person createPerson(Person person) {
		return personRepo.save(person);
	}
	
	
	// Find all persons
	public List<Person> findAll(){
		return personRepo.findAll();
	}
	
	
	// Find user by Id
	public Person findPerson(Long id) {
		Optional<Person> findPerson = personRepo.findById(id);
		if(findPerson.isPresent()) {
			System.out.println(findPerson.get());
			return findPerson.get();
		} else {
			return null;
		}
	}
}
