package model;

public class InHouse extends Part {
    //MEMBER
    private int machineID;

    //CONSTRUCTOR
    public InHouse(String name, double price, int stock, int min, int max, int machineID) {
        super(name, price, stock, min, max);
        this.machineID = machineID;
    }

    //SETTER
    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }

    //GETTER
    public int getMachineID() {
        return machineID;
    }
}
