package fr.adriencaubel.entity;

import fr.adriencaubel.generic.BaseEntity;
import jakarta.persistence.Entity;

@Entity
public class Student extends BaseEntity {
    private String name;
    private int age;

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
}
