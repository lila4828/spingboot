package com.example.mariadb.service;

import com.example.mariadb.entity.People;
import com.example.mariadb.entity.Student;

public interface PeopleService {

    People getPeople(Long id);
    People savePeople(People people);
    People changPeopleName(Long id, String name);
    void deletePeople(Long id);
}
