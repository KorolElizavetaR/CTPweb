package com.ctpweb.junit.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctpweb.junit.exception.ProgrammerIsNotFoundException;
import com.ctpweb.junit.model.Programmer;
import com.ctpweb.junit.service.ProgrammerService;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/programmer")
public class ProgrammerController {
	private final ProgrammerService programmerService;

	@PostMapping("/add")
	public ResponseEntity<Programmer> getProgrammerById(@RequestBody Programmer programmer) {
		return ResponseEntity.ok(programmerService.addProgrammer(programmer));
	}
	
	@GetMapping
	public ResponseEntity<List<Programmer>> getProgrammers()
	{
		return ResponseEntity.ok(programmerService.fetchAllProgrammers());
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity<Programmer> getProgrammer(@PathVariable ("id") Integer id)
	{
		return ResponseEntity.ok(programmerService.fetchProgrammerById(id));
	}
	
	@PatchMapping ("/{id}")
	public ResponseEntity<Programmer> patchProgrammer(@PathVariable ("id") Integer id, @RequestBody Programmer newCompany)
	{
		return ResponseEntity.ok(programmerService.updateProgrammer(newCompany, id));
	}
	
	@DeleteMapping ("/{id}")
	public ResponseEntity<String> deleteProgrammer(@PathVariable ("id") Integer id)
	{
		return ResponseEntity.ok("Programmer is succesfully deleted");
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> handleConstraintViolation(ConstraintViolationException ex) {
		Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
		String errorMessage = violations.stream().map(ConstraintViolation::getMessage)
				.collect(Collectors.joining(", "));
		return ResponseEntity.badRequest().body(errorMessage);
	}
	
	@ExceptionHandler(ProgrammerIsNotFoundException.class)
	public ResponseEntity<String> handleConstraintViolation(ProgrammerIsNotFoundException ex) {
		return ResponseEntity.notFound().build();
	}
}