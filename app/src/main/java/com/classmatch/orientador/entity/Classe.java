package com.classmatch.orientador.entity;

public class Classe {

    String id;
    String nome;
    String codigo;
    String curso;
    int semestre;
    boolean requisito;

    public Classe() {}

    public Classe(String id, String nome, String codigo, String curso, int semestre, boolean requisito) {
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
        this.curso = curso;
        this.semestre = semestre;
        this.requisito = requisito;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getNome() { return nome; }

    public void setNome(String name) {
        this.nome = name;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getSemestre() {
        return semestre;
    }

    public boolean isRequisito() { return requisito; }

    public void setRequisito(boolean requisito) { this.requisito = requisito; }
}
