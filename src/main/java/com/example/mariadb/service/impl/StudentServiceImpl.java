package com.example.mariadb.service.impl;

import com.example.mariadb.entity.Student;
import com.example.mariadb.repository.StudentRepository;
import com.example.mariadb.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student getStudent(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()) {
            return student.get();
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Student saveStudent(Student student) {
        Student saveStudent = studentRepository.save(student);
        System.out.println(saveStudent);
        return saveStudent;
    }

    @Override
    public Student changStudentDept(Long id, String new_dept) {
        Optional<Student> student = studentRepository.findById(id);
        Student newStudent;
        if(student.isPresent()) {
            newStudent = student.get();
            newStudent.setDept(new_dept);
        } else {
            throw new EntityNotFoundException();
        }
        return newStudent;
    }

    @Override
    public void deleteStudent(Long id) {
        Optional<Student> selectstudent = studentRepository.findById(id);
        if(selectstudent.isPresent()) {
            Student student = selectstudent.get();
            studentRepository.delete(student);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
