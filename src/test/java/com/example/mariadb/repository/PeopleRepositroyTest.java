package com.example.mariadb.repository;

import com.example.mariadb.entity.People;
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
public class PeopleRepositroyTest {

    @Autowired
    private PeopleRepository peopleRepository;

    People givenPeople;      //Test 용도

    @BeforeEach
    public void setUpTest() {
        givenPeople = new People();  //givnePeople: Test 용도
        givenPeople.setName("Test");
    }

    @Test
    @DisplayName("student 10개 생성")
    void save() {
        // given
        for(int i=1;i<=5;i++) {
            People people = new People();
            people.setName("Kim"+i);

            // when
            People savedPeople = peopleRepository.save(people);

            // then
            assertEquals(people.getName(), savedPeople.getName());
        }
    }

    @Test
    @DisplayName("student ID로 조회하기")
    void findById() {
        People savedPeople = peopleRepository.save(givenPeople);
        Optional<People> foundPeople = peopleRepository.findById(savedPeople.getId());

        if (foundPeople.isPresent()) {
            People people = foundPeople.get();
            System.out.println(people.toString());
        } else {
            // 값이 없는 경우 처리
            System.out.println("조회된 값이 없습니다.");
        }

        Assertions.assertEquals(foundPeople.get().getName(), foundPeople.get().getName());
    }

    @Test
    @DisplayName("People 모두 조회하기")
    void findAll(){
        List<People> foundPeople = peopleRepository.findAll();
        foundPeople.forEach(item -> System.out.println(item));

        Assertions.assertEquals(foundPeople.size(), foundPeople.size());
    }

    @Test
    @DisplayName("student 수정하기")
    void update(){
        People savedPeople = peopleRepository.save(givenPeople);
        Optional<People> optionalEntity = peopleRepository.findById(savedPeople.getId());
        People foundPeople;
        if (optionalEntity.isPresent()) {
            foundPeople = optionalEntity.get();
            foundPeople.setName("updated");

            // 업데이트된 엔티티 저장
            peopleRepository.save(foundPeople);
        } else {
            throw new EntityNotFoundException("Entity not found with ID: " + savedPeople.getId());
        }

        Assertions.assertEquals(foundPeople.getName(), "updated");
    }

    @Test
    @DisplayName("student ID로 삭제하기")
    void delete(){
        People savedPeople = peopleRepository.save(givenPeople);
        peopleRepository.deleteById(savedPeople.getId());

        Assertions.assertTrue(()->peopleRepository.findById(savedPeople.getId()).isEmpty());
    }
}
