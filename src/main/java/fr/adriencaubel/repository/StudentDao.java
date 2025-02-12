package fr.adriencaubel.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.adriencaubel.entity.Student;
import fr.adriencaubel.generic.GenericDao;
import jakarta.ejb.Stateless;

@Stateless
public class StudentDao extends GenericDao<Student> {

    public StudentDao() {
        super(Student.class);
    }
    
    public Student findWithSoirees(Long id) {
        return em.createQuery(
                "SELECT s FROM Student s LEFT JOIN FETCH s.soirees WHERE s.id = :id", Student.class)
            .setParameter("id", id)
            .getSingleResult();
    }


    public List<Student> searchStudents(String name, Integer age) {
        Map<String, Object> criteria = new HashMap<>();
        if (name != null && !name.isEmpty()) {
            criteria.put("name", name);
        }
        if (age != null) {
            criteria.put("age", age);
        }

        // Permet le JOIN à soirée
        return findByCriteria(criteria, List.of("soirees"));
     }
}
