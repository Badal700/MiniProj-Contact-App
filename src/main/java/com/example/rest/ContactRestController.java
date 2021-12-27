package com.example.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bindings.ContactForm;
import com.example.service.ContactService;

@RestController
public class ContactRestController {
	
	@Autowired
	private ContactService contactService;
	
	@PostMapping("/submit")
	public String submitContact(@RequestBody ContactForm form) {
		return contactService.saveContact(form);
	}
	
	@GetMapping("/view")
	public List<ContactForm> viewAllContacts() {
		return contactService.viewContacts();
	}
	
	@GetMapping("/edit/{contactId}")
	public ContactForm editContacts(@PathVariable Integer contactId) {
		return contactService.editContact(contactId);
	}
	
	@DeleteMapping("/delete/{contactId}")
	public List<ContactForm> deleteContact(@PathVariable Integer contactId) {
		return contactService.deleteContact(contactId);
	}
}
