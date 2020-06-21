package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Inventory {

static ObservableList<Part> allParts = FXCollections.observableArrayList();
static ObservableList<Product> allProducts = FXCollections.observableArrayList();


//CONSTRUCTOR
public Inventory(){

}

//add Part to inventory
public void addNewPart(Part newPart){
    allParts.add(newPart);
}

//Add Product to inventory
public void addNewProduct(Product newProduct){
    allProducts.add(newProduct);
}

/****LOOKUP ITEMS****/
    //parts by ID
        public static Part lookupPart(int partID) {
            for (Part part : allParts) {
                if (part.getId() == partID) {
                    return part;
                }
            }
            return null;
        }

    //parts by String
        public static ObservableList<Part> lookupPart(String partName){
            for (Part part : allParts) {
                if(part.getName().equals(partName)){
                    ObservableList<Part> filteredPartList = FXCollections.observableArrayList();
                    filteredPartList.add(part);
                    return filteredPartList;
                }
            }
            return null;
        }

        public static Product lookupProduct(int productID) {
            for (Product product : allProducts) {
                if (product.getId() == productID) {
                    return product;
                }
            }
            return null;
        }

        //parts by String
        public static ObservableList<Product> lookupProduct(String productName){
            for (Product product : allProducts) {
                if(product.getName().equals(productName)){
                    ObservableList<Product> filteredPartList = FXCollections.observableArrayList();
                    filteredPartList.add(product);
                    return filteredPartList;
                }
            }
            return null;
        }

//update part
    public static void updatePart(int id, Part selectedPart){
        int index = allParts.indexOf(selectedPart);
        allParts.set(index, selectedPart);
    }

//public
    public static void updateProduct(int id, Product selectedProduct){
        int index = allProducts.indexOf(selectedProduct);
        allProducts.set(index, selectedProduct);
    }

//Delete part from inventory
    public static boolean deletePart(Part partSelected){
        for(Part part : allParts) {
            allParts.remove(partSelected);
            return true;
        }
        return false;
    }

//Delete product from inventory
public static boolean deleteProduct(Product productSelected){
    for(Product product : allProducts) {
        allProducts.remove(productSelected);
        return true;
    }
    return false;
}

//Get all parts
public static ObservableList<Part> getAllParts() {
        return allParts;
    }

//Get all products
public static ObservableList<Product>  getAllProducts() {
        return allProducts;
    }

}
