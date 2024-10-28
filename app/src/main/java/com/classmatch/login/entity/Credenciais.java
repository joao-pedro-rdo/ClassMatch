package com.classmatch.login.entity;

public class Credenciais {
    private String email;
    private String senha;

    public Credenciais() {
    }

    public Credenciais(String senha, String email) {
        this.senha = senha;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
