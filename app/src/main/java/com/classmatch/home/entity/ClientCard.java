package com.classmatch.home.entity;

public class ClientCard {
    String name;
    String company;
    String code;
    String photo;

    public ClientCard() {
    }

    public ClientCard(String name, String company, String code, String photo) {
        this.name = name;
        this.company = company;
        this.code = code;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
