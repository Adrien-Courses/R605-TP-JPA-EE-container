package fr.adriencaubel.entity;

import fr.adriencaubel.generic.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Soiree extends BaseEntity {
    private String name;
    
    @ManyToOne
    private Student student;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}
