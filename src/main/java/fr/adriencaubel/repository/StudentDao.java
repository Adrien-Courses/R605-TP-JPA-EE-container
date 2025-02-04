package fr.adriencaubel.repository;

import fr.adriencaubel.entity.Student;
import fr.adriencaubel.generic.GenericDao;
import jakarta.ejb.Stateless;

@Stateless
public class StudentDao extends GenericDao<Student> {

    public StudentDao() {
        super(Student.class);
    }
}
