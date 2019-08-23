package com.sheffmachine.advancedjpa.repositories;

import com.sheffmachine.advancedjpa.entities.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class CourseRepository {
    Logger logger = LoggerFactory.getLogger(this.getClass());

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

    public void playWithEntityManager() {
        logger.info("Play with entity manager start...");
        Course newCourse = new Course("Web Service");
        entityManager.persist(newCourse);

        Course course2 = findById(10001L);
        course2.setName("Web Service Updated");

//        Course newCourse2 = new Course("Another web service Angular");
//        entityManager.persist(newCourse2);
//
//        entityManager.flush();
//
//        entityManager.detach(newCourse2);
//
//        newCourse2.setName("Web Service");

    }
}
