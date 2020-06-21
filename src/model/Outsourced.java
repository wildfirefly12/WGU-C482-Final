package model;

public class Outsourced extends Part{
    //METHODS
    String companyName;

    //CONSTRUCTOR
    public Outsourced(String name, double price, int stock, int min, int max, String companyName) {
        super(name, price, stock, min, max);
        this.companyName = companyName;
    }

    //SETTER
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    //GETTER
    public String getCompanyName() {
        return companyName;
    }
}
