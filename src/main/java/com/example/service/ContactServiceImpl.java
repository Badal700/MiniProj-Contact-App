package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bindings.ContactForm;
import com.example.entities.Contact;
import com.example.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {
	
	@Autowired
	private ContactRepository contactRepo;

	@Override
	public String saveContact(ContactForm form) {
		//We have received form binding object as parameter
		//Repository save(entity) method expecting entity object
		//So copy the data from form binding object to entity object
		Contact entity = new Contact();
		BeanUtils.copyProperties(form, entity);
		entity.setActiveSw("Y");
		entity = contactRepo.save(entity);
		
		if(entity.getContactId() != null) {
			return "Contact Saved Successfully";
		}
		return "Contact Failed to Save";
	}

	@Override
	public List<ContactForm> viewContacts() {
		List<ContactForm> dataList = new ArrayList<>();
		List<Contact> findAll = contactRepo.findAll();
		
		for(Contact entity : findAll) {
			ContactForm form = new ContactForm();
			BeanUtils.copyProperties(entity, form);
			dataList.add(form);
		}
		return dataList;
	}

	@Override
	public ContactForm editContact(Integer contactId) {
		Optional<Contact> findById = contactRepo.findById(contactId);
		if(findById.isPresent()) {
			Contact entity = findById.get();
			ContactForm form = new ContactForm();
			BeanUtils.copyProperties(entity, form);
			return form;
		}
		return null;
	}

	@Override
	public List<ContactForm> deleteContact(Integer contactId) {
		contactRepo.deleteById(contactId);
		
		//getting latest data from table and returning
		List<ContactForm> dataList = new ArrayList<>();
		List<Contact> findAll = contactRepo.findAll();
		
		for(Contact entity : findAll) {
			ContactForm form = new ContactForm();
			BeanUtils.copyProperties(entity, form);
			dataList.add(form);
		}
		return dataList;
	}

}
