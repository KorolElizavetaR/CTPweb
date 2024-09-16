package web.jdbc.laba2.action;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

import web.jdbc.laba2.dao.StudentDAO;
import web.jdbc.laba2.pojo.Student;

public class StudentAction {
	@Autowired
	private StudentDAO dao;
    int st;

    public void insert(Student student) throws ClassNotFoundException, SQLException {
    	dao.insert(student);
    }

    public void update(Student student) throws ClassNotFoundException, SQLException {
    	dao.update(student);
    }

    public void delete(Long id) throws ClassNotFoundException, SQLException {
    	dao.delete(id);
    }

    public void fetchById(Long id) throws ClassNotFoundException, SQLException {
        Student student = dao.fetchById(id);
        System.out.println("Student Details are :");
        System.out.println(student);
    }

    public void fetchByEmailId(String emailId) {
        Student student = dao.fetchByEmailId(emailId);
        if (student.getId() == 0) {
            System.out.println("No Record Found");
        } else {
            System.out.println("Student Details are :");
            System.out.println(student);
        }
    }

    public void fetchByMobileNo(String mobileNo) {
        Student student = dao.fetchByMobileNo(mobileNo);
        if (student.getId() == 0) {
            System.out.println("No Record Found");
        } else {
            System.out.println("Student Details are :");
            System.out.println(student);
        }
    }

    public void searchByName(String name) {
        List<Student> studentList = dao.searchByName(name);
        if (studentList.isEmpty()) {
            System.out.println("No Record Found");
        } else {
            System.out.println("Student Details are :");
            for (Student student : studentList) {
                System.out.println(student);
            }
        }

    }

    public void fetchByCity(String city) {
        List<Student> studentList = dao.fetchByCity(city);
        if (studentList.isEmpty()) {
            System.out.println("No Record Found");
        } else {
            System.out.println("Student Details are :");
            for (Student student : studentList) {
                System.out.println(student);
            }
        }
    }

    public void fetchBySalaryRange(BigDecimal lowerSalary, BigDecimal higherSalary) {
        List<Student> studentList = dao.fetchBySalaryRange(lowerSalary, higherSalary);
        if (studentList.isEmpty()) {
            System.out.println("No Record Found");
        } else {
            System.out.println("Student Details are :");
            for (Student student : studentList) {
                System.out.println(student);
            }
        }
    }

    public void fetchByDob(Date dob) {
        List<Student> studentList = dao.fetchByDob(dob);
        if (studentList.isEmpty()) {
            System.out.println("No Record Found");
        } else {
            System.out.println("Student Details are :");
            for (Student student : studentList) {
                System.out.println(student);
            }
        }
    }

    public void fetchByRangeDoj(Date startDate, Date endDate) {
        List<Student> studentList = dao.fetchByRangeDoj(startDate, endDate);
        if (studentList.isEmpty()) {
            System.out.println("No Record Found");
        } else {
            System.out.println("Student Details are :");
            for (Student student : studentList) {
                System.out.println(student);
            }
        }
    }

    public void fetchAll() {
        List<Student> studentList = dao.fetchAll();
        if (studentList.isEmpty()) {
            System.out.println("No Record Found");
        } else {
            System.out.println("Student Details are :");
            for (Student student : studentList) {
                System.out.println(student);
            }
        }
    }

}
