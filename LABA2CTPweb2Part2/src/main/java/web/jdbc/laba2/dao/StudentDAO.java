package web.jdbc.laba2.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import web.jdbc.laba2.pojo.Student;

@Component ("studentDAO")
public class StudentDAO {
	@Autowired
 	ConnectionFactory connectionFactory;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public void insert(Student student) throws SQLException, ClassNotFoundException {
    	Connection conn = connectionFactory.getConnection();
    	String query = "insert into student(fname,lname,address,mobile_no,email_id,city,designation,dob,doj,salary) "
    			+ "values(?,?,?,?,?,?,?,?,?,?)";
        ps = conn.prepareStatement(query);
        ps.setString(1, student.getFname());
        ps.setString(2, student.getLname());
        ps.setString(3, student.getAddress());
        ps.setString(4, student.getMobileNo());
        ps.setString(5, student.getMailId());
        ps.setString(6, student.getCity());
        ps.setString(7, student.getDesignation());
        ps.setDate(8, student.getDob());
        ps.setDate(9, student.getDoj());
        ps.setBigDecimal(10, student.getSalary());
        ps.executeUpdate();
        conn.close();
    }

    public void update(Student student) throws ClassNotFoundException, SQLException {
    	Connection con = connectionFactory.getConnection();
        String query = "update student set fname=?,lname=?,address=?,mobile_no=?,email_id=?,city=?, "
                + "designation=?,dob=?,doj=?,salary=? where id=?";
        ps = con.prepareStatement(query);
        ps.setString(1, student.getFname());
        ps.setString(2, student.getLname());
        ps.setString(3, student.getAddress());
        ps.setString(4, student.getMobileNo());
        ps.setString(5, student.getMailId());
        ps.setString(6, student.getCity());
        ps.setString(7, student.getDesignation());
        ps.setDate(8, student.getDob());
        ps.setDate(9, student.getDoj());
        ps.setBigDecimal(10, student.getSalary());
        ps.setLong(11, student.getId());
        ps.executeUpdate();
        con.close();
    }

    public void delete(long id) throws ClassNotFoundException, SQLException {
    	Connection con = connectionFactory.getConnection();
		String query = "delete from student where id=? ";
	    ps = con.prepareStatement(query);
	    ps.setLong(1, id);
	    ps.executeUpdate();
        con.close();
    }

    public Student fetchById(long id) throws ClassNotFoundException, SQLException {
        Student student = new Student();
        Connection con = connectionFactory.getConnection();
        String query = "select * from student where id=?";
        ps = con.prepareStatement(query);
        ps.setLong(1, id);
        rs = ps.executeQuery();
        // while
        rs.next();
            student.setId(rs.getLong("id"));
            student.setFname(rs.getString("fname"));
            student.setLname(rs.getString("lname"));
            student.setAddress(rs.getString("address"));
            student.setMobileNo(rs.getString("mobile_no"));
            student.setMailId(rs.getString("email_id"));
            student.setCity(rs.getString("city"));
            student.setDesignation(rs.getString("designation"));
            student.setDob(rs.getDate("dob"));
            student.setDoj(rs.getDate("doj"));
            student.setSalary(rs.getBigDecimal("salary"));
            student.setAddDate(rs.getTimestamp("add_date"));
        con.close();
        return student;
    }

    public Student fetchByEmailId(String emailId) throws ClassNotFoundException, SQLException {
        Student student = new Student();
        Connection con = connectionFactory.getConnection();
        
        // Найти студентов по emailID
        
        con.close();
        return student;
    }

    public Student fetchByMobileNo(String mobileNo) throws ClassNotFoundException, SQLException {
    	 Student student = new Student();
         Connection con = connectionFactory.getConnection();
         
         // Найти студентов по Номеру телефона
         
         con.close();
         return student;
    }

    public List <Student> searchByName(String name) throws ClassNotFoundException, SQLException {
        List<Student> studentList = new ArrayList<>();
        Connection con = connectionFactory.getConnection();
        
        // Найти студентов по Имени
        // Модернизируйте метод. С возможностью искать и по имени и по фамилии с помощью слова like

        con.close();
        return studentList;
    }

    public List<Student> fetchByCity(String city) throws ClassNotFoundException, SQLException {
        List<Student> studentList = new ArrayList<>();
        Connection con = connectionFactory.getConnection();
        String query = "select * from student where city=?";
        ps = con.prepareStatement(query);
        ps.setString(1, city);
        rs = ps.executeQuery();
        while (rs.next()) {
            Student student = new Student();
            student.setId(rs.getLong("id"));
            student.setFname(rs.getString("fname"));
            student.setLname(rs.getString("lname"));
            student.setAddress(rs.getString("address"));
            student.setMobileNo(rs.getString("mobile_no"));
            student.setMailId(rs.getString("email_id"));
            student.setCity(rs.getString("city"));
            student.setDesignation(rs.getString("designation"));
            student.setDob(rs.getDate("dob"));
            student.setDoj(rs.getDate("doj"));
            student.setSalary(rs.getBigDecimal("salary"));
            student.setAddDate(rs.getTimestamp("add_date"));
            studentList.add(student); }
        con.close();
        return studentList;
    }

    public List<Student> fetchBySalaryRange(BigDecimal lowerSalary, BigDecimal higherSalary) throws ClassNotFoundException, SQLException {
        List<Student> studentList = new ArrayList<>();
        Connection con = connectionFactory.getConnection();
        
        // Найти студентов с ЗП в пределах От и До
        
        con.close();
        return studentList;
    }

    public List<Student> fetchByDob(Date dob) throws SQLException, ClassNotFoundException {
        List<Student> studentList = new ArrayList<Student>();
        Connection con = connectionFactory.getConnection();
        String query = "select * from student where dob=?";
        ps = con.prepareStatement(query);
        ps.setDate(1, dob);
        rs = ps.executeQuery();
        while (rs.next()) {
            Student student = new Student();
            student.setId(rs.getLong("id"));
            student.setFname(rs.getString("fname"));
            student.setLname(rs.getString("lname"));
            student.setAddress(rs.getString("address"));
            student.setMobileNo(rs.getString("mobile_no"));
            student.setMailId(rs.getString("email_id"));
            student.setCity(rs.getString("city"));
            student.setDesignation(rs.getString("designation"));
            student.setDob(rs.getDate("dob"));
            student.setDoj(rs.getDate("doj"));
            student.setSalary(rs.getBigDecimal("salary"));
            student.setAddDate(rs.getTimestamp("add_date"));
            studentList.add(student);
        }
        con.close();
        return studentList;
    }

    public List<Student> fetchByRangeDoj(Date startDate, Date endDate) throws ClassNotFoundException, SQLException {
        List<Student> studentList = new ArrayList<Student>();
        Connection con = connectionFactory.getConnection();
       
        // Найти студентов с датами От и До

        con.close();
        return studentList;
    }

    public List<Student> fetchAll() throws ClassNotFoundException, SQLException {
        List<Student> studentList = new ArrayList<Student>();
        Connection con = connectionFactory.getConnection();
        
            // Модернизируйте поиск - отсортировав от большего к меньшему по полю id

        String query = "select * from student";
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();
        while (rs.next()) {
            Student student = new Student();
            student.setId(rs.getLong("id"));
            student.setFname(rs.getString("fname"));
            student.setLname(rs.getString("lname"));
            student.setAddress(rs.getString("address"));
            student.setMobileNo(rs.getString("mobile_no"));
            student.setMailId(rs.getString("email_id"));
            student.setCity(rs.getString("city"));
            student.setDesignation(rs.getString("designation"));
            student.setDob(rs.getDate("dob"));
            student.setDoj(rs.getDate("doj"));
            student.setSalary(rs.getBigDecimal("salary"));
            student.setAddDate(rs.getTimestamp("add_date"));
            studentList.add(student);
        }
        con.close();
        return studentList;
    }
}
