package sk.fiit.takacErik.company;

import sk.fiit.takacErik.devices.Device;
import sk.fiit.takacErik.includes.Address;
import sk.fiit.takacErik.includes.Person;

import java.util.ArrayList;

//táto trieda slúži prevažne ako úložisko a identifikáciu klienta, nerobí prakticky nič podstatné
public class Client extends Person {
    //Kostruktory
    public Client(String name, String surname, int id, Address connAddress){
        super(name, surname, connAddress);
        this.id = id;
    }

    public Client(String name, String surname){
        super(name, surname);
    }

    //Atribúty
    private int id;
    private ArrayList<Device> devices;
    public boolean hasService = false;

    //Metody
    public int getId() {
        return id;
    }

    public ArrayList<Device> getDevices() {
        return devices;
    }

    public void setDevices(ArrayList<Device> devices) {
        this.devices = devices;
    }

    public void setId(int id) {
        this.id = id;
    }

}
