package korol.web.hibernate.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import korol.web.hibernate.dao.CompanyDAO;
import korol.web.hibernate.model.Car;
import korol.web.hibernate.model.Company;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping ("/company")
@RequiredArgsConstructor
public class CompanyController {
	private final CompanyDAO companyDAO;
	
	// вот так писать кринж великий, но я усталь
	@GetMapping
	public ResponseEntity <List<Company>> getCompanies(@RequestParam (value = "name", required = false) String name)
	{
		if (name != null && !(name.isBlank()))
		{
			Company company = companyDAO.findCompanyByName(name);
			if (company == null) 
			{
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(Collections.singletonList(company));
		}
		
		List<Company> companies = companyDAO.getCompanies();
		if (companies.isEmpty())
		{
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(companies);
	}
	
	@GetMapping ("/{id}")
	public ResponseEntity <Company> getCompany(@PathVariable ("id") Integer id)
	{
		Company company = companyDAO.findCompanyById(id);
		if (company == null)
		{
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(company);
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
	public ResponseEntity<Company> editCompany(@PathVariable ("id") Integer id, @RequestBody @Valid Company company, BindingResult result)
	{
		if (result.hasErrors())
		{
			return ResponseEntity.badRequest().build();
		}
		if (!companyDAO.updateCompany(company, id))
		{
			return ResponseEntity.notFound().build();
		}
		// под вопросикаом
		return ResponseEntity.ok(company);
	} 
	
	@DeleteMapping ("/{id}")
	public ResponseEntity<String> deleteCompany(@PathVariable ("id") Integer id)
	{
		if (!companyDAO.deleteCompany(id))
		{
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok("Company is succesfully deleted");
	}
	
	@PostMapping ("/{id}/cars/add")
	public ResponseEntity <Company> addCar (@PathVariable ("id") Integer id, @RequestBody @Valid Car car, BindingResult result)
	{
		if (result.hasErrors())
		{
			return ResponseEntity.badRequest().build();
		}
		if (!companyDAO.addCar(id, car))
		{
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(companyDAO.findCompanyById(id));
	}
	
	@GetMapping ("/{id}/cars/{car_id}")
	public ResponseEntity<Car> showCar(@PathVariable ("id") Integer id, @PathVariable ("car_id") Integer car_id)
	{
		Car car = companyDAO.showCar(id, car_id);
		if (car == null)
		{
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(car);
	}
	
	@GetMapping ("/{id}/cars")
	public ResponseEntity<List<Car>> showCars(@PathVariable ("id") Integer id)
	{
		List<Car> cars = companyDAO.getCars(id);
		if (cars.isEmpty())
		{
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cars);
	}
	
	@DeleteMapping ("/{id}/cars/{car_id}")
	public ResponseEntity<String> showCars(@PathVariable ("id") Integer id, @PathVariable ("car_id") Integer car_id)
	{
		if (!companyDAO.deleteCar(id, car_id))
		{
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok("Car is succesfully deleted");
	}
}
