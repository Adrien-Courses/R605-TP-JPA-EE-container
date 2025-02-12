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

    public void addStudent(StudentRequestModel studentRequestModel) {
    	Student student = new Student();
    	student.setName(studentRequestModel.getName());
    	student.setAge(studentRequestModel.getAge());
    	
    	studentDao.save(student);
    }

    public Student getStudent(Long id) {
    	// Student student = studentDao.find(id);
    	// Hibernate.initialize(student.getSoirees()); // horrible
    	
    	Student student = studentDao.findWithSoirees(id);
    	
        return student;
    }

    public List<Student> getAllStudents() {
        return studentDao.findAll();
    }

    public void updateStudent(StudentRequestModel student) {
    }

    public void deleteStudent(Long id) {
    }
    
    public List<Student> getStudentsPaginated(int page, int size) {
        return studentDao.findAllPaginated(page, size);
    }
    
    public List<Student> getStudentsPaginatedWithJoin(int page, int size) {
        return studentDao.findAllPaginatedAndJoin(page, size, List.of("soirees"));
    }

    public long getTotalStudentsCount() {
        return studentDao.count();
    }
    
    public List<Student> searchStudents(String name, Integer age) {
        return studentDao.searchStudents(name, age);
    }
}
