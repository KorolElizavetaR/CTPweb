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
	private int carId;

	@Column(name = "name")
	private String name;

	@Column(name = "year")
	private int year;

	@Column(name = "distance")
	private int distance;

	@Column(name = "fuel")
	private String fuel;

	@Column(name = "fuel_consumption")
	private String fuelConsumption;

	@Column(name = "price")
	private int price;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	private Company company;

}
