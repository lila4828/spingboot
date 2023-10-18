package com.example.mariadb.service;

import com.example.mariadb.entity.Student;
import com.example.mariadb.repository.StudentRepository;
import com.example.mariadb.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;

@SpringBootTest
public class StudentServiceTest {
    @Autowired
    private StudentRepository studentRepository;
    private StudentServiceImpl studentService;

    Student givenStudent;

    @BeforeEach
    public void setUpTest() {
        studentService = new StudentServiceImpl(studentRepository);

        givenStudent = new Student();  //givneStudent: Test 용도
        givenStudent.setDept("CE");
        givenStudent.setName("Peter");
        givenStudent.setYear("3학년");
        givenStudent.setGpa(3.3f);
        givenStudent.setEmail("test@naver.com");
        givenStudent.setAge(23);
    }

    @Test
    @DisplayName("student 조회하기")
    void getStudent() {
        Student savedStudent = studentService.saveStudent(givenStudent);

        Student foundStudent = studentService.getStudent(savedStudent.getId());

        Assertions.assertEquals(foundStudent.getId(), givenStudent.getId());
        Assertions.assertEquals(foundStudent.getName(), givenStudent.getName());
        Assertions.assertEquals(foundStudent.getDept(), givenStudent.getDept());
        Assertions.assertEquals(foundStudent.getYear(), givenStudent.getYear());
    }

    @Test
    @DisplayName("student 저장하기")
    void saveStudent() {
        Student savedStudent = studentService.saveStudent(givenStudent);
        System.out.println(savedStudent);

        Assertions.assertEquals(savedStudent.getName(), "Peter");
    }

    @Test
    @DisplayName("student 수정하기")
    void changStudentDept(){
        Student savedStudent = studentService.saveStudent(givenStudent);
        Student foundStudent = studentService.getStudent(savedStudent.getId());
        System.out.println(foundStudent);
        Student updatedStudent = studentService.changStudentDept(foundStudent.getId(), "Arch");

        Assertions.assertEquals(updatedStudent.getDept(), "Arch");
    }

    @Test
    @DisplayName("student 삭제하기")
    void deleteStudent(){
        Student savedStudent = studentService.saveStudent(givenStudent);
        studentService.deleteStudent(savedStudent.getId());

        //Assertions.assertNull(studentRepository.findById(savedStudent.getId()).orElse(null));
        Assertions.assertThrows(EntityNotFoundException.class, ()->studentService.getStudent(savedStudent.getId()));
    }
}
