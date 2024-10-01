package korol.web.hibernate.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CarDTO {
	
	@Column(name = "name")
	@NotNull
	private String name;

	@Column(name = "year")
	@NotNull
	@Min (value = 1900)
	@Max (value = 2024)
	private int year;

	@Column(name = "distance")
	@NotNull
	@Positive
	private int distance;

	@Column(name = "fuel")
	@NotNull
	private String fuel;

	@Column(name = "fuel_consumption")
	@NotNull
	private String fuelConsumption;

	@Column(name = "price")
	@NotNull
	@Positive
	private int price;

}
