package com.ctpweb.junit.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.ctpweb.junit.exception.ProgrammerIsNotFoundException;
import com.ctpweb.junit.model.Programmer;
import com.ctpweb.junit.repository.ProgrammerRepository;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional (readOnly = true)
public class ProgrammerService {
	private final Validator validator;
	private final ProgrammerRepository programmerRepository;

	@Transactional (readOnly = false)
	public Programmer addProgrammer(Programmer programmer)
	{
		Set<ConstraintViolation<Programmer>> violations = validator.validate(programmer);

        if (!violations.isEmpty()) throw new ConstraintViolationException(violations);
        
		return programmerRepository.save(programmer);
	}
	
	@Transactional (readOnly = false)
	public Programmer updateProgrammer(@Valid Programmer newProgrammer, Integer id)
	{
		Programmer oldProgrammer = programmerRepository.findById(id).orElseThrow(()->new ProgrammerIsNotFoundException());
		newProgrammer.setId(oldProgrammer.getId());
		return programmerRepository.save(newProgrammer);
	}
	
	public List<Programmer> fetchAllProgrammers()
	{
		return programmerRepository.findAll();
	}
	
	public Programmer fetchProgrammerById(Integer id)
	{
		return programmerRepository.findById(id).orElseThrow(()->new ProgrammerIsNotFoundException());
	}
	
	@Transactional (readOnly = false)
	public void deleteProgrammer(Integer id)
	{
		Programmer programmer = programmerRepository.findById(id).orElseThrow(()->new ProgrammerIsNotFoundException());
		programmerRepository.delete(programmer);
	}
}
