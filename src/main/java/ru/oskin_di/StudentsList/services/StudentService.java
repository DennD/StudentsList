package ru.oskin_di.StudentsList.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.oskin_di.StudentsList.model.Student;
import ru.oskin_di.StudentsList.repositories.StudentRepository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    @PostConstruct
    public void init() throws URISyntaxException, IOException {
        List<String> names = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File(Objects.requireNonNull(StudentService.class.getResource("/names.txt")).toURI())));
        String line = reader.readLine();
        while (line != null) {
            names.add(line);
            line = reader.readLine();
        }

        /** Генератор 1000 случайных студентов*/
        for (int i = 0; i < 10; i++) {
            add(names.get((int) (Math.random() * (names.size()))), (int) (Math.random() * 5 + 1));
        }
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(int id) {
        return studentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public void add(String name, int mark) {
        studentRepository.save(new Student(name, mark));
    }

    public void remove(int id) {
        studentRepository.deleteById(id);
    }

    @Transactional
    public void createMark(int id, int mark) {
        studentRepository.updateMark(id, mark);
    }
}
