package com.example.constructionxpert.model;

public class Project {
    private int project_id;
    private String name, description, start_date, finish_date;
    private double budget;

    public Project(int project_id, String name, String description, String start_date, String finish_date, double budget) {
        this.project_id = project_id;
        this.name = name;
        this.description = description;
        this.start_date = start_date;
        this.finish_date = finish_date;
        this.budget = budget;
    }

    public Project(String name, String description, String start_date, String finish_date, double budget) {
        this.name = name;
        this.description = description;
        this.start_date = start_date;
        this.finish_date = finish_date;
        this.budget = budget;
    }

    public Project() {
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getFinish_date() {
        return finish_date;
    }

    public void setFinish_date(String finish_date) {
        this.finish_date = finish_date;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
}
