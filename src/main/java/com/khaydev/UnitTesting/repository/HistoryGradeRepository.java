package com.khaydev.UnitTesting.repository;

import com.khaydev.UnitTesting.model.HistoryGrade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryGradeRepository extends JpaRepository<HistoryGrade, Integer> {
}
