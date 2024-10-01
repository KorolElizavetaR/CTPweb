package korol.web.hibernate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Table (name = "cars")
@Data
@NoArgsConstructor
@Accessors (chain = true)
public class Car {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "car_id")
	@NotNull
	private int carId;

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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	@NotNull
	private Company company;

}
