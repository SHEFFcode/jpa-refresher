package com.sheffmachine.advancedjpa.repositories;

import com.sheffmachine.advancedjpa.entities.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseSpringRepositoryTest {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    CourseSpringDataRepository courseRepository;

    @Autowired
    EntityManager em;

    @Test
    public void findById_basic() {
        Optional<Course> courseOptional = courseRepository.findById(10001L);
        logger.info("Is there a course? -> {}", courseOptional.isPresent());
        assertTrue(courseOptional.isPresent());
    }

    @Test
    public void playAroundWithSpringData() {
//        courseRepository.save(new Course("Microservices!!"));
        List<Course> allCourses = courseRepository.findAll();
        long numberOfCourses = courseRepository.count();

        logger.info("The courses in repo are -> {} and their count is -> {}", allCourses, numberOfCourses);
    }

    @Test
    public void springDataSort() {
        // sort by name in descending order
        Sort sort = new Sort(Sort.Direction.DESC, "name");
        List<Course> sortedCourses = courseRepository.findAll(sort);
        logger.info("Sorted courses are -> {}", sortedCourses);
    }

    @Test
    public void pagination() {
//        PageRequest pageRequest = new PageRequest(0, 2); // deprecated
        PageRequest pageRequest = PageRequest.of(0, 2);
        Page<Course> firstPage = courseRepository.findAll(pageRequest);

        logger.info("First page is -> {}", firstPage.getContent());

        Pageable nextPageable = firstPage.nextPageable();
        Page<Course> secondPage = courseRepository.findAll(nextPageable);

        logger.info("Second page is -> {}", secondPage.getContent());
    }

    @Test
    public void dynamicPagination() {
        PageRequest pageRequest = PageRequest.of(0, 2);
        Page<Course> cPage = courseRepository.findAll(pageRequest);

        while (cPage.hasNext()) {
            Pageable nextPageable = cPage.nextPageable();
            logger.info("Current page content is -> {}", cPage.getContent());
            cPage = courseRepository.findAll(nextPageable);
        }
    }

    @Test
    public void findByName() {
        logger.info("Find by name -> {}", courseRepository.findByName("Course"));
    }
}
