package sk.fiit.takacErik.devices.passive;

import sk.fiit.takacErik.devices.Device;

public class Outlet implements Device {
    private final String description = "Outlet";

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public String getVendor() {
        return null;
    }

    @Override
    public void finalize(){
        ;//System.out.println("Device deleted");
    }

    @Override
    public void removeMe() {
        this.finalize();
    }
}
