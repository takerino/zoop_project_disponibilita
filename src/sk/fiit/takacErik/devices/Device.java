package sk.fiit.takacErik.devices;

public interface Device {

    public String description = "Some device";
    public String vendor = "None";
    public String type = "None";

    public String getDescription();
    public String getType();
    public String getVendor();

    public void finalize() throws Throwable;

    public void removeMe();

}
