package sk.fiit.takacErik.includes;

public class Person {

    public Person(String name, String surname){
        this.name = name;
        this.surname = surname;
    }

    public Person(String name, String surname, Address address){
        this.name = name;
        this.surname = surname;
        this.address = address;
    }

    private final String name, surname;
    private Address address;

    public String getName() {
        return name+ " " + surname;
    }

    public String getSurname(){
        return surname;
    }

    public String getFirstName(){
        return name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }
}
