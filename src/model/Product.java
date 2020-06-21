package model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {

    public ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private static int count = 1;
    int id;
    String name;
    double price;
    int stock;
    int min;
    int max;


    //CONSTRUCTOR
    public Product(String name, double price, int stock, int min, int max) {
        this.id = count++;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    //SETTERS

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    //GETTERS

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    //Add associated part to product
    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }

    //delete associated part from product
    public boolean deleteAssociatedPart(Part part){
        if(associatedParts.contains(part)){
            associatedParts.remove(part);
            return true;
        }
        return false;
    }

    public ObservableList<Part> getAllAssociatedParts(){
        return associatedParts;
    }


}
