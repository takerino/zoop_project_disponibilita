package sk.fiit.takacErik.devices;

public class Device {

    private String vendor;
    private String type;

    public Device(String vendor, String type){
        this.type = type;
        this.vendor = vendor;
    }

    public String getType() {
        return type;
    }

    public String getVendor() {
        return vendor;
    }
}
