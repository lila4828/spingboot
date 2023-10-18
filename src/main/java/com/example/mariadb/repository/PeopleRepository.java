package com.example.mariadb.repository;

import com.example.mariadb.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleRepository extends JpaRepository<People, Long> {

}
