package com.classmatch.professor.entity;

public class ClasseCard {
    private Classe classe;
    private boolean selecionada;
    private int interesse = 0;

    public ClasseCard(Classe classe, boolean selecionada, int interesse) {
        this.classe = classe;
        this.selecionada = selecionada;
        this.interesse = interesse;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public boolean isSelecionada() {
        return selecionada;
    }

    public void setSelecionada(boolean selecionada) {
        this.selecionada = selecionada;
    }

    public int getInteresse() {
        return interesse;
    }

    public void setInteresse(int interesse) {
        this.interesse = interesse;
    }
}
