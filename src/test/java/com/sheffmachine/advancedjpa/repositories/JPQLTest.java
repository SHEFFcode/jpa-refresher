package com.sheffmachine.advancedjpa.repositories;

import com.sheffmachine.advancedjpa.entities.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JPQLTest {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    EntityManager entityManager;

    @Test
    public void qpql_findById_basic() {
        Query query = entityManager.createNamedQuery("query_get_all_courses");
        List resultList = query.getResultList();
        logger.info("Query is select c from Course c -> {}", resultList);
    }

    @Test
    public void jpql_findById_typed() {
        TypedQuery<Course> result = entityManager.createQuery("select c from Course c", Course.class);
        List<Course> resultList = result.getResultList();
        logger.info("Query is select c from Course c -> {}", resultList);
    }

    @Test
    public void jpql_findById_basic_where() {
        TypedQuery<Course> result = entityManager.createNamedQuery("query_get_angular_courses", Course.class);
        List<Course> resultList = result.getResultList();
        logger.info("Query is select c from Course c where name like '%Angular' -> {}", resultList);
    }

}
