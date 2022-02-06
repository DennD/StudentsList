package ru.oskin_di.StudentsList.dtos;

import lombok.Data;
import ru.oskin_di.StudentsList.model.Student;

@Data
public class StudentDto {

    private int id;
    private String name;
    private int mark;

    public StudentDto(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.mark = student.getMark();
    }
}
