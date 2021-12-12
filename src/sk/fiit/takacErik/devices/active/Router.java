package sk.fiit.takacErik.devices.active;

import sk.fiit.takacErik.devices.Device;

public class Router implements Device {

    private final String description = "Router";

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void removeMe() {
        this.finalize();
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
        ;
    }
}
