package com.classmatch.results.entity;

public class ResultsCard {
    String name;
    String code;
    String course;
    String semester;
    int students;
    int teachers;
    String interestStudents;
    String interestTeachers;
    int studentsScore;
    int teachersScore;

    ResultsCard() {
    }

    public ResultsCard(
            String name,
            String code,
            String course,
            int teachers,
            String interestTeachers,
            String interestStudents,
            int students,
            String semester,
            int studentsScore,
            int teachersScore
    ) {
        this.name = name;
        this.code = code;
        this.course = course;
        this.teachers = teachers;
        this.interestTeachers = interestTeachers;
        this.interestStudents = interestStudents;
        this.students = students;
        this.semester = semester;
        this.studentsScore = studentsScore;
        this.teachersScore = teachersScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public int getStudents() {
        return students;
    }

    public void setStudents(int students) {
        this.students = students;
    }

    public int getTeachers() {
        return teachers;
    }

    public void setTeachers(int teachers) {
        this.teachers = teachers;
    }

    public String getInterestStudents() {
        return interestStudents;
    }

    public void setInterestStudents(String interestStudents) {
        this.interestStudents = interestStudents;
    }

    public String getInterestTeachers() {
        return interestTeachers;
    }

    public void setInterestTeachers(String interestTeachers) {
        this.interestTeachers = interestTeachers;
    }

    public int getTeachersScore() {
        return teachersScore;
    }

    public void setTeachersScore(int teachersScore) {
        this.teachersScore = teachersScore;
    }

    public int getStudentsScore() {
        return studentsScore;
    }

    public void setStudentsScore(int studentsScore) {
        this.studentsScore = studentsScore;
    }
}
