package sk.fiit.takacErik.devices.active;

import sk.fiit.takacErik.devices.Device;

public class ONT implements Device {

    private final String description = "Optical netvwork terminal";

    public void finalize(){
       // System.out.println(this.getDescription() + " was deleted");
       ;
    }

    @Override
    public void removeMe() {
        this.finalize();
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public String getVendor() {
        return null;
    }
}
