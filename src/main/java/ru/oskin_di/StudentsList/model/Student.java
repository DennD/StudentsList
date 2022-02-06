package ru.oskin_di.StudentsList.model;

import lombok.*;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;


    @Column(name = "mark")
    private int mark;

    public Student(String name, int mark) {
        this.name = name;
        this.mark = mark;
    }
}
