package com.classmatch.home.entity;

public class ClientCard {
    private String name;
    private String course;
    private String code;
    private String semestre;
    private boolean hasPrerequisites;

    // Construtor vazio necessário para Firebase
    public ClientCard() {
    }

    // Construtor com todos os campos
    public ClientCard(String name, String course, String code, String semestre, boolean hasPrerequisites) {
        this.name = name;
        this.course = course;
        this.code = code;
        this.semestre = semestre;
        this.hasPrerequisites = hasPrerequisites;
    }

    // Getters e Setters
    public String getName() {
        return name;
    }
    public String geCourse() {
        return course;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public boolean hasPrerequisites() {
        return hasPrerequisites;
    }

    public void setHasPrerequisites(boolean hasPrerequisites) {
        this.hasPrerequisites = hasPrerequisites;
    }
}
