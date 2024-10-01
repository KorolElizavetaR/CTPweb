package korol.web.hibernate.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import korol.web.hibernate.model.*;
import lombok.RequiredArgsConstructor;

@Component
@Transactional (readOnly = true)
@RequiredArgsConstructor
public class CompanyDAO {
	@PersistenceContext
	private final EntityManager session;
	
	@Transactional (readOnly = false)
	public void addCompany(Company company)
	{
		session.persist(company);
	}
	
	@Transactional (readOnly = false)
	public boolean updateCompany(Company company, Integer id)
	{
		Company oldCompany = findCompanyById(id);
		if (company == null)
			return false;
		oldCompany.setCars(company.getCars()).
			setCompanyCountry(company.getCompanyCountry()).
			setCompanyName(company.getCompanyName());
		return true;
	};
	
	@Transactional (readOnly = false)
	public boolean deleteCompany(int id)
    {
		Company company = findCompanyById(id);
		if (company == null)
			return false;
		session.remove(company);
		return true;
    };
    
    public List<Company> getCompanies()
    {
        return session.createQuery("FROM Company", Company.class).getResultList();
    };
    
    public Company findCompanyById(int id)
    {
    	return session.find(Company.class, id);
    };
    
    public Company findCompanyByName(String name)
    {
    	return session.createQuery("FROM Company WHERE companyName = :companyName", Company.class).
    			setParameter("companyName", name).
    			getSingleResult();
    };
    
    @Transactional (readOnly = false)
    public boolean addCar(Integer company_id, Car car)
    {
    	Company company = findCompanyById(company_id);
    	if (company == null)
			return false;
    	car.setCompany(company);
    	company.addCar(car);
    	session.persist(car);
    	return true;
    }
    
    public Car showCar(Integer company_id, Integer car_id)
    {
    	Company company = findCompanyById(company_id);
    	return company.getCars().stream().filter(c -> c.getCarId() == car_id).findFirst().orElse(null);
    }
    
    
    public List<Car> getCars(Integer company_id)
    {
    	Company company = findCompanyById(company_id);
    	return company.getCars();
    }

    @Transactional (readOnly = false)
    public boolean deleteCar(Integer company_id, Integer car_id)
    {
    	Company company = findCompanyById(company_id);
    	if (company == null)
    		return false;
    	Car car = company.getCars().stream().filter(c -> c.getCarId() == car_id).findFirst().orElse(null);
    	if (car == null)
    		return false;
    	session.remove(car);
    	company.getCars().remove(car);
    	return true;
    }
}
