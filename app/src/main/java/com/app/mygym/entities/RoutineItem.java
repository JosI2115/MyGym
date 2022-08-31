package com.app.mygym.entities;

public class RoutineItem {

    private String name;
    private String description;
    private String trainer;

    public RoutineItem() {
    }

    public RoutineItem(String name, String description, String trainer) {
        this.name = name;
        this.description = description;
        this.trainer = trainer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }
}
