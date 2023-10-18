package com.example.mariadb.service.impl;

import com.example.mariadb.entity.Student;
import com.example.mariadb.repository.StudentRepository;
import com.example.mariadb.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service  //@Service 지정
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Override
    public Student getStudent(Long id){
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return student.get();
        } else {
            // 엔티티가 존재하지 않는 경우 처리
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Student saveStudent(Student student){
        Student savedStudent = studentRepository.save(student);
        System.out.println(savedStudent);
        return savedStudent;
    }

    @Override
    public Student changStudentDept(Long id, String new_dept){
        Optional<Student> student = studentRepository.findById(id);
        Student newStudent;
        if(student.isPresent()){
            newStudent = student.get();
            newStudent.setDept(new_dept);  //new dept 저장
        }else{
            throw new EntityNotFoundException();
        }
        return newStudent;
    }

    @Override
    public void deleteStudent(Long id) {
        Optional<Student> selectedStudent = studentRepository.findById(id);

        if (selectedStudent.isPresent()) {
            Student student = selectedStudent.get();
            studentRepository.delete(student);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
