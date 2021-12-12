package sk.fiit.takacErik.company;

import sk.fiit.takacErik.includes.Person;

public abstract class Employee extends Person {
    public Employee(String name, String surname) {
        super(name, surname);
    }

    //zamestnanec pracuje prave pre danu firmu
    private Company company;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void installClient(){
        System.out.println("Instalujem klienta..");
    }
}
