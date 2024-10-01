package korol.web.hibernate.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import korol.web.hibernate.model.AuthUser;
import korol.web.hibernate.model.Company;
import korol.web.hibernate.model.Person;
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
	public void updateCompany(Company company, Integer id)
	{
		Company oldCompany = session.find(Company.class, id);
		oldCompany.setCars(company.getCars()).
			setCompanyCountry(company.getCompanyCountry()).
			setCompanyName(company.getCompanyName());
	};
	
	@Transactional (readOnly = false)
	public void deleteCompany(int id)
    {
		Company company = session.find(Company.class, id);
		session.remove(company);
    };
    
    public List<Company> showCompanies()
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

}
