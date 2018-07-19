package com.shujia.bean;

public class Student_score {

    private String id;
    private String name;
    private String clazz;
    private String cource;
    private int score;

    public Student_score(String id, String name, String clazz, String cource, int score) {
        this.id = id;
        this.name = name;
        this.clazz = clazz;
        this.cource = cource;
        this.score = score;
    }

    public Student_score() {
    }

    @Override
    public String toString() {
        return "Student_score{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", clazz='" + clazz + '\'' +
                ", cource='" + cource + '\'' +
                ", score=" + score +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getCource() {
        return cource;
    }

    public void setCource(String cource) {
        this.cource = cource;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
