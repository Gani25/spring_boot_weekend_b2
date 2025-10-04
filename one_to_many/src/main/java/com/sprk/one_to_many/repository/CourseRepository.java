package com.sprk.one_to_many.repository;

import com.sprk.one_to_many.entity.Course;
import com.sprk.one_to_many.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    Optional<Course> findByCourseIdAndStudent(int courseId, Student student);
}
