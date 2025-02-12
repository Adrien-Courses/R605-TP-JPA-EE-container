package fr.adriencaubel.entity;

import java.util.List;

import fr.adriencaubel.generic.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Student extends BaseEntity {
    private String name;
    private int age;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Soiree> soirees;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

	public List<Soiree> getSoirees() {
		return soirees;
	}

	public void setSoirees(List<Soiree> soirees) {
		this.soirees = soirees;
	}
}
