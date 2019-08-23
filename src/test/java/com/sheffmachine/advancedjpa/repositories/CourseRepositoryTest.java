package com.sheffmachine.advancedjpa.repositories;

import com.sheffmachine.advancedjpa.entities.Course;
import com.sheffmachine.advancedjpa.entities.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseRepositoryTest {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    EntityManager em;

    @Test
    public void findById_basic() {
        Course course = courseRepository.findById(10001L);
        logger.info("I am running a test... {}", course);
        assertNotNull(course);
        assertEquals("Course", course.getName());
    }

    @Test
    @DirtiesContext
    public void deleteById_basic() {
        courseRepository.deleteById(10001L);
        Course course = courseRepository.findById(10001L);
        assertNull(course);
    }

    @Test
    @DirtiesContext
    public void save_basic() {
        // Get the course
        Course course = courseRepository.findById(10001L);
        assertEquals("Course", course.getName());
        // Update the details
        course.setName("Microservices");
        courseRepository.save(course);

        // Test that the update took
        course = courseRepository.findById(10001L);
        assertEquals("Microservices", course.getName());

    }

    @Test
    public void playWithEntityManager() {
        courseRepository.playWithEntityManager();
    }

    @Test
    @Transactional
    public void retireveReviewsForCourse() {
        Course course = courseRepository.findById(10001L);
        List<Review> reviews = course.getReviews();
        logger.info("The reviews are -> {}", reviews);
    }

    @Test
    @Transactional
    public void retireveCourseForAReview() {
        Review review = em.find(Review.class,50001L);
        logger.info("The reviews are -> {}", review.getCourse());
    }
}
