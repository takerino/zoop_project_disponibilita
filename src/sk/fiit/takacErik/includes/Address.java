package sk.fiit.takacErik.includes;

public class Address {
    private final String country;
    private final String city;
    private final String street;
    private final int number;

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
