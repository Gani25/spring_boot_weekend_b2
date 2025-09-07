package com.sprk.student_management.repository;

import com.sprk.student_management.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    // JPQL Methods
    List<Student> findByGender(String gender);

    Student findByEmail(String email);

    boolean existsByEmail(String email);


}
