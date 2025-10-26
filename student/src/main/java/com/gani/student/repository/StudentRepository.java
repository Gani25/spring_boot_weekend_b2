package com.gani.student.repository;

import com.gani.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findByPhone(String phone);

    Optional<Student> findByEmail(String email);
}
