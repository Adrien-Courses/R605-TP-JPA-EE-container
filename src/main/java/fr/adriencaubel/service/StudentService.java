package fr.adriencaubel.service;

import fr.adriencaubel.controller.StudentRequestModel;
import fr.adriencaubel.entity.Student;
import fr.adriencaubel.repository.StudentDao;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;

@Stateless
public class StudentService {

    @Inject
    private StudentDao studentDao;

    public void addStudent(StudentRequestModel student) {
    }

    public Student getStudent(Long id) {
        return studentDao.find(id);
    }

    public List<Student> getAllStudents() {
        return null;
    }

    public void updateStudent(StudentRequestModel student) {
    }

    public void deleteStudent(Long id) {
    }
}
