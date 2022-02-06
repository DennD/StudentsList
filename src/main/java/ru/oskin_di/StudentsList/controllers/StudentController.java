package ru.oskin_di.StudentsList.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.oskin_di.StudentsList.dtos.StudentDto;
import ru.oskin_di.StudentsList.services.StudentService;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
@Validated
public class StudentController {

    private final StudentService studentService;

    @GetMapping()
    public List<StudentDto> getAllStudents() {
        return studentService.findAll().stream().map(StudentDto::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public StudentDto getStudentById(@PathVariable int id) {
        return new StudentDto(studentService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteStudentById(@PathVariable int id) {
        studentService.remove(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> createMarkForStudent(@PathVariable int id, @RequestParam @Min(1) @Max(5) int mark) {
        studentService.createMark(id, mark);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<?> addStudent(@RequestParam String name, @RequestParam @Min(1) @Max(5) int mark) {
        studentService.add(name, mark);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
