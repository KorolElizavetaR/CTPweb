package korol.web.hibernate.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity
@Table (name = "cars")
@Getter
@Setter
@NoArgsConstructor
@Accessors (chain = true)
public class Car {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "car_id")
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
	@ToString.Exclude
	@JsonBackReference
	private Company company;

	public Car(@NotNull String name, @NotNull @Min(1900) @Max(2024) int year, @NotNull @Positive int distance,
			@NotNull String fuel, @NotNull String fuelConsumption, @NotNull @Positive int price) {
		this.name = name;
		this.year = year;
		this.distance = distance;
		this.fuel = fuel;
		this.fuelConsumption = fuelConsumption;
		this.price = price;
	}

}
