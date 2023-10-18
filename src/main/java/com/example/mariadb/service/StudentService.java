package com.example.mariadb.service;

import com.example.mariadb.entity.Student;

public interface StudentService {
    Student getStudent(Long id);
    Student saveStudent(Student student);
    Student changStudentDept(Long id, String dept);
    void deleteStudent(Long id);
}
