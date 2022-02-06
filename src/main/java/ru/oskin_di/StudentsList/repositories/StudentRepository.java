package ru.oskin_di.StudentsList.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.oskin_di.StudentsList.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Modifying
    @Query ("update Student s set s.mark = :mark where s.id = :id")
    void updateMark(int id, int mark);
}
