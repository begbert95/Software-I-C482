package main;

public class InHouse extends Part{
    private int machineID;

    public InHouse(int id, String name, double price, int stock, int min, int max, int machineID) {
        super(id, name, price, stock, min, max);
        this.machineID = machineID;
        Inventory.addPart(this);
    }

    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
    
    public int getMachineID() {
        return this.machineID;
    }
}
