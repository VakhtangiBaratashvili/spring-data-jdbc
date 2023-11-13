package com.example.springdatajdbc.student;

import com.example.springdatajdbc.connections.DB_Connection;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentUtils {

    private static final Statement statement = DB_Connection.getStatement();
    private static StudentUtils utils;
    private StudentUtils() {}
    public static StudentUtils getInstance() {
        if (utils == null) {
            utils = new StudentUtils();
        }
        return utils;
    }

    public void insert(Student student) throws SQLException {
        String sql = "insert into student(id, name, email, dob, age) values ("
                + student.getId() + ", "
                + student.getName() + ", "
                + student.getEmail() + ", "
                + Date.valueOf(student.getDob()) + ", "
                + student.getAge() + ")";
        statement.executeUpdate(sql);
    }

    public void update(long id, Student student) throws SQLException{
        String sql = "update student set name = '" + student.getName()
                + "', email = '" + student.getEmail()
                + "', dob = '" + student.getDob()
                + "', age = " + student.getAge()
                + " where id = " + id;
        statement.executeUpdate(sql);
    }

    public List<Student> getAllStudents() throws SQLException{
        String sql = "select * from student";
        ResultSet resultSet = statement.executeQuery(sql);
        List<Student> students = new ArrayList<>();
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            LocalDate dob = resultSet.getDate("dob").toLocalDate();
            Integer age = resultSet.getInt("age");
            students.add(new Student(id, name, email, dob, age));
        }
        return students;
    }

    public Student getStudent(long id) throws SQLException{
        String sql = "select * from student where id = " + id;
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            Long studentId = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            LocalDate dob = resultSet.getDate("dob").toLocalDate();
            Integer age = resultSet.getInt("age");
            return new Student(studentId, name, email, dob, age);
        }
        return null;
    }

    public void delete(long id) throws SQLException {
        String sql = "delete from student where id = " + id;
        statement.executeUpdate(sql);
    }
}
