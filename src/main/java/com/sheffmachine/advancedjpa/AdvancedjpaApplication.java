package com.sheffmachine.advancedjpa;

import com.sheffmachine.advancedjpa.entities.Course;
import com.sheffmachine.advancedjpa.repositories.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdvancedjpaApplication implements CommandLineRunner {
    @Autowired
    CourseRepository courseRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) {
        SpringApplication.run(AdvancedjpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Get the course
        Course course = courseRepository.findById(10001L);
        // Update the details
        course.setName("Microservices");
        courseRepository.save(course);

        course = courseRepository.findById(10001L);
//        logger.info("Course 10001 is --> {}", course);
//        courseRepository.deleteById(10001L);
//        logger.info("Inserting course -> {}", courseRepository.save(new Course("Microservices")));

        courseRepository.playWithEntityManager();
    }
}
