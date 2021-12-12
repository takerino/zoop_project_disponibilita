package sk.fiit.takacErik.includes;

//Trieda slúži ako úložisko informácii
public class Locality {

    public Locality(Address address, String description, String locType){
        this.address = address;
        this.description = description;
        this.locType = locType;
    }

    protected Address address;
    private final String description;
    private final String locType;

    public Address getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public String getLocType() {
        return locType;
    }


}
