package korol.web.hibernate.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Table (name = "companies")
@Data
@NoArgsConstructor
@Accessors (chain = true)
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "company_id")
	private int companyId;

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "company_country")
	private String companyCountry;

	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Car> cars;

	public Company(String companyName, String companyCountry) {
		this.companyName = companyName;
		this.companyCountry = companyCountry;
	}
	
	public void addCar(Car car) {
		car.setCompany(this);
		if (cars == null)
			cars = new ArrayList<>();
		this.cars.add(car);
	}

}