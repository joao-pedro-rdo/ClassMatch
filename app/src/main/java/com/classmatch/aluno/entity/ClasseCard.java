package com.classmatch.aluno.entity;

public class ClasseCard {
    private Classe classe;
    private boolean selecionada;

    public ClasseCard(Classe classe, boolean selecionada) {
        this.classe = classe;
        this.selecionada = selecionada;
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
}
