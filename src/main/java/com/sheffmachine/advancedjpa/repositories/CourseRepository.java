package com.sheffmachine.advancedjpa.repositories;

import com.sheffmachine.advancedjpa.entities.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class CourseRepository {
    @Autowired
    EntityManager entityManager;

    public Course findById(Long id) {
        return entityManager.find(Course.class, id);
    }

    public void deleteById(Long id) {
        Course courseToDelete = entityManager.find(Course.class, id);

        entityManager.remove(courseToDelete);
    }

    public Course save(Course course) {
        if (course.getId() == null) {
            // Insert
            entityManager.persist(course);
        } else {
            // Update
            entityManager.merge(course);
        }

        return course;
    }
}
