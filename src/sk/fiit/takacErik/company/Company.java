package sk.fiit.takacErik.company;
import sk.fiit.takacErik.Client;
import sk.fiit.takacErik.parts.Address;

import java.util.ArrayList;

public class Company {
    private int companyId;

    private ArrayList<Technician> technicians = new ArrayList<>();
    private ArrayList<Client> clients = new ArrayList<>();
    private ArrayList<String> adresy = new ArrayList<>();

    //isty sposob ako demonstrovat prekonavanie tried je vytvorenie si vlastnej a jej zavolanie
    //java.Lang obsahuje triedu Address, ktora ale sluzi pre ine ucely, ja som si to chcel customizovat, tak som
    // si vytvoril akoby vlastny typ premennej, ktora uchovava udaje o fyzickej adrese ludi
    private ArrayList<Address> addresses = new ArrayList<>();




    public void pridajAdresu(String adresa){
        this.adresy.add(adresa);
    }

    public ArrayList<String> getAdresy() {
        return adresy;
    }
}
