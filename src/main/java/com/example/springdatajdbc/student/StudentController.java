package com.example.springdatajdbc.student;

import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class StudentController {

    @PostMapping("/student/add")
    public Student insertStudent(@RequestBody Student student) {
        if (student != null) {
            try {
                StudentUtils.getInstance().insert(student);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return student;
    }

    @PutMapping("/student/{id}")
    public Student updateStudentById(@PathVariable long id, @RequestBody Student student) {
        if (id > 0 && student != null) {
            try {
                StudentUtils.getInstance().update(id, student);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            return StudentUtils.getInstance().getStudent(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        try {
            return StudentUtils.getInstance().getAllStudents();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/student/{id}")
    public Student deleteStudentById(@PathVariable long id) {
        if (id > 0) {
            try {
                Student student = StudentUtils.getInstance().getStudent(id);
                StudentUtils.getInstance().delete(id);
                return student;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
