package com.example.constructionxpert.model;

public class Task {
    private int task_id, project_id, ressource_id;
    private String name, description, start_date, finish_date;

    public Task() {
    }

    public Task(int task_id, int project_id, int ressource_id, String name, String description, String start_date, String finish_date) {
        this.task_id = task_id;
        this.project_id = project_id;
        this.ressource_id = ressource_id;
        this.name = name;
        this.description = description;
        this.start_date = start_date;
        this.finish_date = finish_date;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public int getRessource_id() {
        return ressource_id;
    }

    public void setRessource_id(int ressource_id) {
        this.ressource_id = ressource_id;
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
}
