package com.sheffmachine.advancedjpa.repositories;

import com.sheffmachine.advancedjpa.entities.Passport;
import com.sheffmachine.advancedjpa.entities.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentRepositoryTest {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    StudentRepository courseRepository;

    @Autowired
    EntityManager em;

    @Test
    @Transactional
    public void retrieveStudentAndPassportDetails() {
        Student student = em.find(Student.class, 20001L);
        Passport passport = student.getPassport();

        logger.info("Student is -> {}", student);
        logger.info("Passport of Student is -> {}", passport);
    }

    @Test
    @Transactional
    public void retrievePassportAndAssociatedStudent() {
        Passport passport = em.find(Passport.class, 40001L);
        Student student = passport.getStudent();

        logger.info("Student is -> {}", student);
        logger.info("Passport of Student is -> {}", passport);
    }

    @Test
    @Transactional
    public void someTest() {
        // DB Op 1 - Retrieve Student
        Student student = em.find(Student.class, 20001L); // Persistence context (student)
        // DB Op 2 - Retrieve Passport
        Passport passport = student.getPassport(); // Persistence context (student, passport)
        // DB Op 3 - Update Passport
        passport.setNumber("Z123456"); // Persistence context (student, passport++)
        // DB Op 4 - Update Student
        student.setName("New Guy"); // Persistence context (student++, passport++)

        //Only after all the changes succeed, will this be stored to the DB.
    }

    @Test
    @Transactional
    public void retrieveStudentAndCourses() {
        Student student1 = em.find(Student.class, 20001L);
        logger.info("Student is -> {}", student1);
        logger.info("Courses are -> {}", student1.getCourses());
    }
}
