package web.jdbc.laba2.pojo;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Accessors (chain = true)
public class Student {
	private long id;
    private String fname;
    private String lname;
    private String address;
    private String mobileNo;
    private String mailId;
    private String city;
    private String designation;
    private Date dob;
    private Date doj;
    private BigDecimal salary;
    private Timestamp addDate;
    
//	public Student(String fname, String lname, String address, String mobileNo, String mailId, String city,
//			String designation, Date dob, Date doj, BigDecimal salary, Timestamp addDate) {
//		super();
//		this.fname = fname;
//		this.lname = lname;
//		this.address = address;
//		this.mobileNo = mobileNo;
//		this.mailId = mailId;
//		this.city = city;
//		this.designation = designation;
//		this.dob = dob;
//		this.doj = doj;
//		this.salary = salary;
//		this.addDate = addDate;
//	}
	
	public Student(String fname, String lname, String mobileNo, String mailId, String city, Date dob) {
		this.fname = fname;
		this.lname = lname;
		this.mobileNo = mobileNo;
		this.mailId = mailId;
		this.city = city;
		this.dob = dob;
		this.addDate = new Timestamp(System.currentTimeMillis());
	}
    
}
