package com.sheffmachine.advancedjpa.repositories;

import com.sheffmachine.advancedjpa.entities.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NativeQueriesTest {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    EntityManager entityManager;

    @Test
    public void native_findById_basic() {
        Query query = entityManager.createNativeQuery("SELECT * FROM COURSE", Course.class);
        List resultList = query.getResultList();
        logger.info("Query is SELECT * FROM COURSE -> {}", resultList);
    }


    @Test
    public void native_findById_where() {
        Query query = entityManager.createNativeQuery("SELECT * FROM COURSE WHERE ID = ?", Course.class);
        query.setParameter(1, 10001L);
        List resultList = query.getResultList();
        logger.info("Query is SELECT * FROM COURSE WHERE ID = ? -> {}", resultList);
    }

    @Test
    public void native_findById_named_parameters() {
        Query query = entityManager.createNativeQuery("SELECT * FROM COURSE WHERE ID = :id", Course.class);
        query.setParameter("id", 10001L);
        List resultList = query.getResultList();
        logger.info("Query is SELECT * FROM COURSE WHERE ID = ? -> {}", resultList);
    }

    @Test
    @Transactional
    public void native_update() {
        Query query = entityManager.createNativeQuery("UPDATE COURSE SET LAST_UPDATED_DATE=sysdate()", Course.class);
        int numberOfRowsUpdates = query.executeUpdate();
//        List resultList = query.getResultList();
        logger.info("Query is UPDATE COURSE WHERE SET LAST_UPDATED_DATE=sysdate(), number of rows updated is -> {}", numberOfRowsUpdates);
    }
}
