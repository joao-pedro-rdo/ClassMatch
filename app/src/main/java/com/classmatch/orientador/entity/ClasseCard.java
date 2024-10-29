package com.classmatch.orientador.entity;

public class ClasseCard {
    private Classe classe;
    private int totalAlunos;
    private int totalProfessores;

    public ClasseCard(Classe classe, int totalProfessores, int totalAlunos) {
        this.classe = classe;
        this.totalProfessores = totalProfessores;
        this.totalAlunos = totalAlunos;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public int getTotalAlunos() {
        return totalAlunos;
    }

    public void setTotalAlunos(int totalAlunos) {
        this.totalAlunos = totalAlunos;
    }

    public int getTotalProfessores() {
        return totalProfessores;
    }

    public void setTotalProfessores(int totalProfessores) {
        this.totalProfessores = totalProfessores;
    }
}
