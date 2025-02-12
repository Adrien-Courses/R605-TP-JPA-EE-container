package fr.adriencaubel.controller;

import fr.adriencaubel.entity.Soiree;

public class SoireeResponseModel {
    private Long id;
    private String name;

    public SoireeResponseModel(Soiree soiree) {
        this.id = soiree.getId();
        this.name = soiree.getName();
    }

    public Long getId() { return id; }
    public String getName() { return name; }
}
