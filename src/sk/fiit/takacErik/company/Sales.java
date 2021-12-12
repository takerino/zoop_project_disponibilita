package sk.fiit.takacErik.company;

import sk.fiit.takacErik.devices.Device;
import sk.fiit.takacErik.includes.Address;
import sk.fiit.takacErik.includes.Locality;
import sk.fiit.takacErik.includes.Person;

import java.util.ArrayList;

public class Sales extends Employee{

    //constructor
    public Sales(String name, String surname) {
        super(name, surname);
    }

    //ATTRIBUTES

    //sales si bude pamatat tieto data kvoli tomu, ze ich samo generuje..dane veci sa potom ukladaju uz pod company za behu programu
    private int clientId = 21000;
    //private int lastContractId;

    // METHODS

    //funkcia, ktora spravi z cloveka (ktory dalej existuje) klienta firmy a vrati jeho reference.. to sa nasledne prida
    public Client createClient(Person person){
        Client client = new Client(person.getFirstName(), person.getSurname());

        client.setId(this.clientId + 1);
        this.clientId++;
        client.setAddress(person.getAddress());

        addClientToCompany(client);

        return client;
    }

   public boolean checkClientAccess(Address clientAddr, ArrayList<Address> addresses){

        if (addresses.size() == 0){
            //System.out.println("Nemas pridelene adresy!!");
            return false;
        }

        for (Address value : addresses) {
            if (value.equals(clientAddr)) {
                //System.out.println("Klienta mozeme instalovat");
                return true;
            }
        }
        return false;
    }

    public Locality installClient(Client client, ArrayList<Locality> localities){

        for (Locality locality : localities) {
            if (locality.getAddress().equals(client.getAddress())) {
                System.out.println("Klient spracovaný obch. oddelením");
                return locality;
            }
        }
        return null;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId() {
        this.clientId += 1;
    }

    //ulohou sales je pridat klienta do firmy (akoby do internej databazy), po com firma klienta "vlastni"
    public void addClientToCompany(Client client){
        this.getCompany().addClient(client);
    }

    public void fillWarehouse(Device device, Warehouse warehouse){
        warehouse.addDevice(device, warehouse.getWarehouseState());
    }

    public void fillWarehouse(Device device, int n){
        this.getCompany().getWarehouse().addDevice(device, n);
    }

    public void printClients(){
        System.out.println("Zakaznici:");
        for (Client client: getCompany().getClients()
        ) {
            System.out.println("\t[" + client.getId() + ", " + client.getName() + ", " + printAddress(client.getAddress()) + ", " + (client.hasService ? "Pripojeny":"Nepripojeny") + "]");
        }
    }

    public Client getClientById(int id){
        for (Client client: getCompany().getClients()
             ) {
            if(client.getId() == id){
                return client;
            }
        }
        return null;
    }

    public boolean checkId(int id){

        int sizeOfClients = this.getCompany().getClients().size() - 1;

        return id < this.getCompany().getFirstClientID() || this.getCompany().getFirstClientID() + sizeOfClients < id;
    }

    public String printAddress(Address address){
        return address.getStreet() + " " +  address.getNumber() + ", " + address.getCity() + ", " + address.getCountry();
    }

    //TOTO asi prec uplne..

    //vyuzitie privatnej statickej triedy - manager bude osoba, ktora vie skontrolovat sales, ci robi svoju pracu dobre
    private static class Manager{
        private Manager(){

        }

        public void checkSales(){
            System.out.println("Sales je OK");
        }


    }
}
