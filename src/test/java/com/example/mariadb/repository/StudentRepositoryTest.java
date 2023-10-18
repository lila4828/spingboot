package com.example.mariadb.repository;

import com.example.mariadb.entity.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    Student givenStudent;      //Test 용도

    @BeforeEach
    public void setUpTest() {
        givenStudent = new Student();  //givneStudent: Test 용도
        givenStudent.setDept("CE");
        givenStudent.setName("Peter");
        givenStudent.setYear("3학년");
        givenStudent.setGpa(3.3f);
        givenStudent.setEmail("test@naver.com");
        givenStudent.setAge(23);
    }

    @Test
    @DisplayName("student 10개 생성")
    void save() {
        // given
        for(int i=1;i<=10;i++) {
            Student student = new Student();
            student.setName("Kim"+i);
            student.setAge(20+i);
            student.setDept("CE"+i);
            student.setEmail("student"+i+"@gamil.com");
            student.setGpa((float) (3.0+i*0.1));
            if(i<=3)
                student.setYear("1학년");
            else if(4<=i && i<=6)
                student.setYear("2학년");
            else if(7<=i && i<=8)
                student.setYear("3학년");
            else if(9<=i && i<=10)
                student.setYear("4학년");

            // when
            Student savedStudent = studentRepository.save(student);

            // then
            assertEquals(student.getName(), savedStudent.getName());
            assertEquals(student.getDept(), savedStudent.getDept());
            assertEquals(student.getYear(), savedStudent.getYear());
        }
    }

    @Test
    @DisplayName("student ID로 조회하기")
    void findById() {
        //Student savedStudent = studentRepository.save(givenStudent);
        Optional<Student> foundStudent = studentRepository.findById(6L);

        if (foundStudent.isPresent()) {
            // 값이 존재하는 경우 처리
            Student student = foundStudent.get();
            System.out.println(student.toString());
        } else {
            // 값이 없는 경우 처리
            System.out.println("조회된 값이 없습니다.");
        }

        Assertions.assertEquals(foundStudent.get().getId(), 6);
        Assertions.assertEquals(foundStudent.get().getName(), givenStudent.getName());
        Assertions.assertEquals(foundStudent.get().getDept(), givenStudent.getDept());
        Assertions.assertEquals(foundStudent.get().getYear(), givenStudent.getYear());
    }

    @Test
    @DisplayName("student_table 모두 조회하기")
    void findAll(){
        List<Student> foundStudent = studentRepository.findAll();
        foundStudent.forEach(item -> System.out.println(item));

        Assertions.assertEquals(18, foundStudent.size());   //student_table의 row개수: 60
    }

    @Test
    @DisplayName("student 수정하기")
    void update(){
        Student savedStudent = studentRepository.save(givenStudent);
        Optional<Student> optionalEntity = studentRepository.findById(savedStudent.getId());
        Student foundStudent;
        if (optionalEntity.isPresent()) {
            foundStudent = optionalEntity.get();
            // 엔티티의 gpa, email 업데이트
            foundStudent.setGpa(1.2f);
            foundStudent.setEmail("updated@naver.com");

            // 업데이트된 엔티티 저장
            studentRepository.save(foundStudent);
        } else {
            throw new EntityNotFoundException("Entity not found with ID: " + savedStudent.getId());
        }

        Assertions.assertEquals(foundStudent.getGpa(), 1.2f);
        Assertions.assertEquals(foundStudent.getEmail(), "updated@naver.com");
    }

    @Test
    @DisplayName("student ID로 삭제하기")
    void delete(){
        Student savedStudent = studentRepository.save(givenStudent);
        studentRepository.deleteById(savedStudent.getId());

        Assertions.assertTrue(()->studentRepository.findById(savedStudent.getId()).isEmpty());
    }
}
