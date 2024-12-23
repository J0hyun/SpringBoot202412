package com.shop.repository;

import com.shop.entity.Person;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void insertPerson() {
        Person person = Person.builder()
                .userName("test")
                .age(25)
                .phone("010-1234-5678")
                .address("Seoul")
                .regDate(LocalDateTime.now())
                .build();

        personRepository.save(person);
    }
}