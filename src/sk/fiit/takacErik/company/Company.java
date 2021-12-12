package sk.fiit.takacErik.company;
import sk.fiit.takacErik.includes.Address;
import sk.fiit.takacErik.includes.Locality;

import java.util.ArrayList;

public class Company {

    //Konstruktor
    public Company(ArrayList<Technician> technicians, ArrayList<Sales> sales, ArrayList<Client> clients,
                   ArrayList <Address> addresses, ArrayList<String> names, ArrayList<String> surnames,
                   ArrayList<Locality> localities, Warehouse warehouse){

        this.names = names;
        this.surnames = surnames;
        this.technicians = technicians;
        this.sales = sales;
        this.clients = clients;
        this.addresses = addresses;
        this.localities = localities;
        this.warehouse = warehouse;
    }

    //Atributy
    private final ArrayList<String> names;
    private final ArrayList<String> surnames;
    private final ArrayList<Technician> technicians;
    private final ArrayList<Sales> sales;
    private final ArrayList<Client> clients;
    private final ArrayList<Locality> localities;
    private final ArrayList <Address> addresses;
    private final int firstClientID = 21000;
    private final Warehouse warehouse;

    //Metody
    public int getFirstClientID() {
        return firstClientID;
    }

    public ArrayList<Client> getClients(){
        return clients;
    }

    public ArrayList<Address> getAdresy() {
        return addresses;
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public ArrayList<String> getSurnames() {
        return surnames;
    }

    public ArrayList<Locality> getLocalities() {
        return localities;
    }

    public ArrayList<Sales> getSales() {
        return sales;
    }

    public ArrayList<Technician> getTechnicians() {
        return technicians;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void addClient(Client client){
        this.clients.add(client);
    }

    public void addLocality(Locality locality){
        localities.add(locality);
    }

    public void addLocalities(ArrayList<Locality> localities){
        this.localities.addAll(localities);
    }


}
