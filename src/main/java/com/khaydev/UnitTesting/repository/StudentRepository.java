package com.khaydev.UnitTesting.repository;

import com.khaydev.UnitTesting.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
