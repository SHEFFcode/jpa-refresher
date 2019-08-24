package com.sheffmachine.advancedjpa.repositories;

import com.sheffmachine.advancedjpa.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "/courses")
public interface CourseSpringDataRepository extends JpaRepository<Course, Long> {
    List<Course> findByName(String name);
    List<Course> findByNameAndId(String name, Long id);
    int countByName(String name);
    List<Course> findByNameOrderByIdDesc(String name);
    int deleteByName(String name);

    @Query("select c from Course c where name like '%Course'")
    List<Course> coursesWithCourseInTheirName(String name);


    @Query(value = "select * from Course where name like '%Course'", nativeQuery = true)
    List<Course> coursesWithCourseInTheirNameNative(String name);
}
