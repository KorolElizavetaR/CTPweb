package korol.web.hibernate.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import korol.web.hibernate.dao.CompanyDAO;
import korol.web.hibernate.model.Company;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping ("/company")
@RequiredArgsConstructor
public class CompanyController {
	private final CompanyDAO companyDAO;
	
	@GetMapping
	public ResponseEntity <List<Company>> getCompanies()
	{
		List<Company> companies = companyDAO.getCompanies();
		if (companies == null)
		{
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(companies);
	}
	
	@PostMapping ("/add")
	public ResponseEntity<Company> addCompany(@RequestBody @Valid Company company, BindingResult result)
	{
		if (result.hasErrors())
		{
			return ResponseEntity.badRequest().build();
		}
		companyDAO.addCompany(company);
		// под вопросикаом
		return ResponseEntity.ok(company);
	}
	
	@PatchMapping ("/{id}")
	ResponseEntity<Company> editCompany(@PathVariable ("id") Integer id, @RequestBody @Valid Company company, BindingResult result)
	{
		if (result.hasErrors())
		{
			return ResponseEntity.badRequest().build();
		}
		companyDAO.addCompany(company);
		// под вопросикаом
		return ResponseEntity.ok(company);
	} 
}
