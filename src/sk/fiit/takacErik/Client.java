package sk.fiit.takacErik;

import sk.fiit.takacErik.parts.Address;
import sk.fiit.takacErik.parts.Person;

import java.util.ArrayList;

public class Client extends Person {

    private int id;
    private Address homeAddress;
    private Address connAddress;

    public boolean hasService = false;

    public Client(){

    }



    public boolean askInstallation(String adresa, ArrayList adresses){
        if(adresses.contains(adresa)){
            System.out.println("Viem nainstalovat");
            //zahaji sa proces instalacie
            return true;
        }
        return false;
    }



}
