package com.tutorial.entitymanagerfactorytest;

import com.tutorial.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@SpringBootTest
public class EntityManagerFactoryTest {

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Test
    void testEntityManager(){

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        transaction.commit();
        entityManager.close();
        entityManagerFactory.close();

    }

    @Test
    void testFindtUsername(){

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        User user = entityManager.find(User.class, 1);
        Assertions.assertEquals("budhi", user.getUsername());

        log.info("username== {}", user.getUsername());
        log.info("authorities== {}", user.getAuthorities());


        transaction.commit();
        entityManager.close();
        entityManagerFactory.close();

    }



}
