package sk.fiit.takacErik.parts;

public class Address {
    private String country;
    private String city;
    private String street;
    private int number;


    public Address(String country, String city, String street, int number){
        this.country = country;
        this.city = city;
        this.street = street;
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getStreet() {
        return street;
    }
}
