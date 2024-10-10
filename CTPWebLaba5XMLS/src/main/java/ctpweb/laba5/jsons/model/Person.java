package ctpweb.laba5.jsons.model;

import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@NoArgsConstructor
public class Person {
	@Id
	@Column(name = "id", nullable = false, length = 45)
	private String id;

	@Column(name = "surname", nullable = false, length = 45)
	private String surname;
	
	@Column(name = "forename", nullable = false, length = 45)
	private String forename;
	
	@Column(name = "patronymic", nullable = false, length = 45)
	private String patronymic;
	
	@Column(name = "DOB", nullable = false)
	private Date dob;
	
	@Column(name = "sex", nullable = false)
	private byte sex;
	
	@Column(name = "passport_series", nullable = false, length = 45)
	private String passportSeries;
	
	@Column(name = "passport_number", nullable = false, length = 45)
	private String passportNumber;
	
	@Column(name = "city", nullable = false, length = 45)
	private String city;
	
	@Column(name = "address", nullable = false, length = 45)
	private String address;
	
	@Column(name = "phone_home", length = 45)
	private String phoneHome;
	
	@Column(name = "phone_mobile", length = 45)
	private String phoneMobile;
	
	@Column(name = "citizenship", nullable = false, length = 45)
	private String citizenship;
	
	@Column(name = "duty_bound", nullable = false)
	private byte dutyBound;

}
