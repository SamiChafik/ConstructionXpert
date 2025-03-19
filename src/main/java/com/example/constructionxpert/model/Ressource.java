package com.example.constructionxpert.model;

public class Ressource {
    private int ressource_id, quantity;
    private String name, type, supplier;

    public Ressource() {
    }

    public Ressource(int ressource_id, String name, String type, int quantity, String supplier) {
        this.ressource_id = ressource_id;
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.supplier = supplier;
    }

    public Ressource(String supplier, String type, int quantity,String name) {
        this.supplier = supplier;
        this.type = type;
        this.quantity = quantity;
        this.name = name;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}
