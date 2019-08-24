package com.sheffmachine.advancedjpa.repositories;

import com.sheffmachine.advancedjpa.entities.Course;
import com.sheffmachine.advancedjpa.entities.Passport;
import com.sheffmachine.advancedjpa.entities.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class StudentRepository {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager entityManager;

    public Student findById(Long id) {
        return entityManager.find(Student.class, id);
    }

    public void deleteById(Long id) {
        Student studentToDelete = entityManager.find(Student.class, id);

        entityManager.remove(studentToDelete);
    }

    public Student save(Student student) {
        if (student.getId() == null) {
            // Insert
            entityManager.persist(student);
        } else {
            // Update
            entityManager.merge(student);
        }

        return student;
    }

    public void saveStudentWithPassport() {
        Passport passport = new Passport("Z123456");
        entityManager.persist(passport);

        Student newStudent = new Student("Mike");
        newStudent.setPassport(passport);

        entityManager.persist(newStudent);
    }

    public void insertHardCodedStudentAndCourse() {
        Student student = new Student("Jack");
        Course course = new Course("A super duper course");

        entityManager.persist(student);
        entityManager.persist(course);

        student.addCourse(course);
        course.addStudent(student);

        entityManager.persist(student);
    }

    public void insertStudentAndCourse(Student student, Course course) {
        student.addCourse(course);
        course.addStudent(student);

        entityManager.persist(student);
        entityManager.persist(course);
    }
}
