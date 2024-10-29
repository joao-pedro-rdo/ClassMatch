package com.classmatch.orientador.entity;

public class ProfessorCard {

    private String nome;
    private int interesse;

    public ProfessorCard(String nome, int interesse) {
        this.nome = nome;
        this.interesse = interesse;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getInteresse() {
        return interesse;
    }

    public void setInteresse(int interesse) {
        this.interesse = interesse;
    }
}
