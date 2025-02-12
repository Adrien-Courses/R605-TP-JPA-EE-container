package fr.adriencaubel.controller;

import java.util.List;
import java.util.stream.Collectors;

import fr.adriencaubel.entity.Student;

public class StudentResponseModel {
    private Long id;
    private String name;
    private int age;
    private List<SoireeResponseModel> soirees;

    public StudentResponseModel(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.age = student.getAge();
        this.soirees = student.getSoirees().stream()
                              .map(SoireeResponseModel::new)
                              .collect(Collectors.toList());
    }

    // Getters uniquement (éviter les setters pour rendre immuable)
    public Long getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public List<SoireeResponseModel> getSoirees() { return soirees; }
}