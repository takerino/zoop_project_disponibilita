package sk.fiit.takacErik.company;
import sk.fiit.takacErik.Client;
import sk.fiit.takacErik.devices.Device;

import java.util.ArrayList;

public class Contract {

    private String typeOfService; //describes, if it is internet/iptv or voip
    private int contractId;
    private Client client; //exact instance of client class, which is the part of contract
    private boolean state; //connected and up or not connected and down

    private ArrayList<Device> devices;
    private String contractNote;

    //check method for information about service
    public boolean isConnected(){
        if (state){
            System.out.println("Connected");
            return true;
        }

        System.out.println("Not connected");
        return false;
    }

    //in a case, there is no paymant or so
    public void setState(boolean state) {
        this.state = state;
    }

    public ArrayList<Device> getDevices() {
        return devices;
    }

    public String getTypeOfService() {
        return typeOfService;
    }
}
