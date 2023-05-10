package com.khaydev.UnitTesting.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;

    @OneToMany(mappedBy = "student")
    private List<HistoryGrade> historyGrades;

    @OneToMany(mappedBy = "student")
    private List<ScienceGrade> scienceGrades;

    @OneToMany(mappedBy = "student")
    private List<EnglishGrade> englishGrades;

}
