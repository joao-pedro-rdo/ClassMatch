package com.classmatch.orientador.entity;

public class ProfessorCard {

    private String nome;
    private int nota;

    public ProfessorCard() {
    }

    public ProfessorCard(String nome, int nota) {
        this.nome = nome;
        this.nota = nota;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
}
