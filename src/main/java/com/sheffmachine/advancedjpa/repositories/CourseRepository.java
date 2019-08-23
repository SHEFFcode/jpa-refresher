package com.sheffmachine.advancedjpa.repositories;

import com.sheffmachine.advancedjpa.entities.Course;
import com.sheffmachine.advancedjpa.entities.Review;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

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

    public void addReviewForCourse() {
        // Get the course 10003
        Course course = findById(10003L);
        //add 2 reviews to it
        Review review1 = new Review("This is an awesome ass course", "5");
        review1.setCourse(course);
        Review review2 = new Review("This is a decent course", "3");
        review2.setCourse(course);

        Collections.addAll(course.getReviews(), review1, review2);

        //save it to the Db

        entityManager.persist(review1);
        entityManager.persist(review2);

    }

    public void addReviewForCourse(Long courseId, List<Review> reviews) {
        // Get the course 10003
        Course course = findById(courseId);
        //add 2 reviews to it
        reviews.forEach(review -> {
            review.setCourse(course);
            course.addReview(review);
            entityManager.persist(review);
        });
    }
}
