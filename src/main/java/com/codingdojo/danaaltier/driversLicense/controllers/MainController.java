package com.codingdojo.danaaltier.driversLicense.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingdojo.danaaltier.driversLicense.models.License;
import com.codingdojo.danaaltier.driversLicense.models.Person;
import com.codingdojo.danaaltier.driversLicense.services.LicenseService;
import com.codingdojo.danaaltier.driversLicense.services.PersonService;

@Controller
public class MainController {
	
	// Adding the license and person services as  dependencies
	private final PersonService personService;
	private final LicenseService licenseService;
	
	
	// Constructor
	public MainController(PersonService personService, LicenseService licenseService) {
		this.personService = personService;
		this.licenseService = licenseService;
	}
	
	
	// Redirect
	@RequestMapping("/")
	public String index() {
		return "redirect:/persons/new";
	}
	
	
	// Render new person form
	@RequestMapping("/persons/new")
	public String newPerson(@ModelAttribute("person") Person person) {
		return "newPerson.jsp";
	}
	
	
	// POST route for creating a new person
	@PostMapping("/persons/new")
	// @Valid checks for validation
	// @BindingResult after, checks for errors
	public String createPerson(@Valid @ModelAttribute("person") Person person, BindingResult result) {
		if (result.hasErrors()) {
			return "newPerson.jsp";
		} else {
			personService.createPerson(person);
			return "redirect:/persons/new";
		}
	}
	
	
	// Render new license form
	@RequestMapping("/licenses/new")
	public String newLicense(@ModelAttribute("license") License license, Model model) {
		List<Person> listPerson = personService.findAll();
		model.addAttribute("persons", listPerson);
		return "newLicense.jsp";
	}
	
	
	// POST route for creating a new license
	@PostMapping("/licenses/new")
	public String createLicense(@Valid @ModelAttribute("license") License license, BindingResult result) {
		if(result.hasErrors()) {
			return "newLicense.jsp";
		}else {
			licenseService.createLicense(license);
			return "redirect:/licenses/new";
		}
	}
	
	
	// Render user profile
	@RequestMapping("/persons/{id}")
	public String showProfile(@PathVariable("id") Long id, Model model) {
		Person findPerson = personService.findPerson(id);
		if (findPerson == null) {
			return "redirect:/";
		}else {
			model.addAttribute("person", findPerson);
			return "showPerson.jsp";
		}
	}
}
