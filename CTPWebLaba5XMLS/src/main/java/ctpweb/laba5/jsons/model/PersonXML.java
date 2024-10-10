package ctpweb.laba5.jsons.model;

import java.sql.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@XmlType(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonXML {
	private String id;
	private String surname;
	private String forename;
	private String patronymic;
	@XmlJavaTypeAdapter(DateTimeAdapter.class)
	private Date dob;
	private byte sex;
	private String passportSeries;
	private String passportNumber;
	private String city;
	private String address;
	private String phoneHome;
	private String phoneMobile;
	private String citizenship;
	private byte dutyBound;
	private QueryType qtype;
	
	public Person getEntity() {
        Person emp = new Person();
        emp.setId(id);
        emp.setSurname(surname);
        emp.setForename(forename);
        emp.setPatronymic(patronymic);
        emp.setDob(dob);
        emp.setSex(sex);
        emp.setPassportSeries(passportSeries);
        emp.setPassportNumber(passportNumber);
        emp.setCity(city);
        emp.setAddress(address);
        emp.setPhoneHome(phoneHome);
        emp.setPhoneMobile(phoneMobile);
        emp.setCitizenship(citizenship);
        emp.setDutyBound(dutyBound);
        return emp;
    }
	
	public PersonXML(Person origin) {
        this.id = origin.getId();
        this.surname = origin.getSurname();
        this.forename = origin.getForename();
        this.patronymic = origin.getPatronymic();
        this.dob = origin.getDob();
        this.sex = origin.getSex();
        this.passportSeries = origin.getPassportSeries();
        this.passportNumber = origin.getPassportNumber();
        this.city = origin.getCity();
        this.address = origin.getAddress();
        this.phoneHome = origin.getPhoneHome();
        this.phoneMobile = origin.getPhoneMobile();
        this.citizenship = origin.getCitizenship();
        this.dutyBound = origin.getDutyBound();
        this.qtype = null;
    }


}
