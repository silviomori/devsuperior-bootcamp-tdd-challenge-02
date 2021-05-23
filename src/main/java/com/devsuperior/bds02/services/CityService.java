package com.devsuperior.bds02.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repositories.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository repository;

	@Transactional(readOnly = true)
	public List<CityDTO> findAll(String sortBy) {
		return repository.findAll(Sort.by(sortBy))
				.stream().map(entity -> new CityDTO(entity))
				.collect(Collectors.toList());
	}

	public void delete(Long id) {
		City entity = repository.getOne(id);
		repository.delete(entity);
	}

}
