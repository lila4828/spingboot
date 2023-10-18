package com.example.mariadb.service.impl;

import com.example.mariadb.entity.People;
import com.example.mariadb.repository.PeopleRepository;
import com.example.mariadb.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class PeopleServiceImpl implements PeopleService {

    @Autowired
    private PeopleRepository peopleRepository;

    @Override
    public People getPeople(Long id) {
        Optional<People> people = peopleRepository.findById(id);
        if(people.isPresent()) {
            return people.get();
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public People savePeople(People people) {
        People savePeople = peopleRepository.save(people);
        System.out.println(savePeople);
        return savePeople;
    }

    @Override
    public People changPeopleName(Long id, String new_name) {
        Optional<People> people = peopleRepository.findById(id);
        People newPeople;
        if(people.isPresent()) {
            newPeople = people.get();
            newPeople.setName(new_name);
        } else {
            throw new EntityNotFoundException();
        }
        return newPeople;
    }

    @Override
    public void deletePeople(Long id) {
        Optional<People> selectPeople = peopleRepository.findById(id);
        if(selectPeople.isPresent()) {
            People people = selectPeople.get();
            peopleRepository.delete(people);
        } else {
            throw new EntityNotFoundException();
        }
    }
}
