package com.classmatch.professor.entity;

public class ProfessorClasse {

    String id;
    int nota;

    public ProfessorClasse() {}

    public ProfessorClasse(String id, int nota) {
        this.id = id;
        this.nota = nota;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public int getNota() { return nota; }

    public void setNota(int nota) {
        this.nota = nota;
    }
}
