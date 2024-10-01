package korol.web.hibernate.dto;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CompanyDTO {
	@Column(name = "company_name")
	@NotNull
	private String companyName;

	@Column(name = "company_country")
	@NotNull
	private String companyCountry;

	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<CarDTO> cars;
}
