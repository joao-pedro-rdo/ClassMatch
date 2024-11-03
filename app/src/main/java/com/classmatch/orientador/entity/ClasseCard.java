package com.classmatch.orientador.entity;

public class ClasseCard {
    private int alunos;
    private int professores;
    String id;
    String nome;
    String codigo;
    String curso;
    int semestre;
    boolean requisito;

    public ClasseCard() {
    }

    public ClasseCard(int alunos, int professores, String id, String nome, String codigo, String curso, int semestre, boolean requisito) {
        this.alunos = alunos;
        this.professores = professores;
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
        this.curso = curso;
        this.semestre = semestre;
        this.requisito = requisito;
    }

    public boolean isRequisito() {
        return requisito;
    }

    public void setRequisito(boolean requisito) {
        this.requisito = requisito;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getProfessores() {
        return professores;
    }

    public void setProfessores(int professores) {
        this.professores = professores;
    }

    public int getAlunos() {
        return alunos;
    }

    public void setAlunos(int alunos) {
        this.alunos = alunos;
    }
}
