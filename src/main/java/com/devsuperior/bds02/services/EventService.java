package com.devsuperior.bds02.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.exceptions.ResourceNotFoundException;
import com.devsuperior.bds02.repositories.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository repository;

	public EventDTO update(Long id, EventDTO dto) {
		try {
			Event entity = repository.getOne(id);
			entity.setDate(dto.getDate());
			entity.setName(dto.getName());
			entity.setUrl(dto.getUrl());
			entity.setCity(new City(dto.getCityId(), ""));
			repository.save(entity);
			dto = new EventDTO(entity);
			return dto;
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(String.format("Event ID %d not found", id));
		}
	}

}
