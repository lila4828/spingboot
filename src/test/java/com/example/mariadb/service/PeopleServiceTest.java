package com.example.mariadb.service;

import com.example.mariadb.entity.People;
import com.example.mariadb.repository.PeopleRepository;
import com.example.mariadb.service.impl.PeopleServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;

@SpringBootTest
public class PeopleServiceTest {
    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private PeopleServiceImpl peopleService;

    People givenPeople;      //Test 용도

    @BeforeEach
    void setUpTest() {
        givenPeople = new People();  //givnePeople: Test 용도
        givenPeople.setName("Test");
    }

    @Test
    @DisplayName("People 조회하기")
    void getPeople() {
        //People savePeople = peopleService.savePeople(givenPeople);

        People foundPeople = peopleService.getPeople(1L);

        Assertions.assertEquals(foundPeople.getId(), 1L);
        //Assertions.assertEquals(foundPeople.getName(), "");
    }

    @Test
    @DisplayName("People 저장하기")
    void savePeople(People people) {
        People savedStudent = peopleService.savePeople(givenPeople);
        System.out.println(savedStudent);

        Assertions.assertEquals(savedStudent.getName(), "Peter");
    }

    @Test
    @DisplayName("People 수정하기")
    void changPeopleName(Long id, String name) {
        People savedPeople = peopleService.savePeople(givenPeople);
        People foundPeople = peopleService.getPeople(savedPeople.getId());
        System.out.println(foundPeople);
        People updatedPeople = peopleService.changPeopleName(foundPeople.getId(), "Arch");

        Assertions.assertEquals(updatedPeople.getName(), "Arch");
    }

    @Test
    @DisplayName("People 삭제하기")
    void deletePeople(Long id) {
        People savedStudent = peopleService.savePeople(givenPeople);
        peopleService.deletePeople(savedStudent.getId());

        //Assertions.assertNull(studentRepository.findById(savedStudent.getId()).orElse(null));
        Assertions.assertThrows(EntityNotFoundException.class, ()->peopleService.getPeople(savedStudent.getId()));
    }
}
