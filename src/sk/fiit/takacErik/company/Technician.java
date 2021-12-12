package sk.fiit.takacErik.company;

import sk.fiit.takacErik.devices.Device;
import sk.fiit.takacErik.devices.active.ONT;
import sk.fiit.takacErik.devices.active.Router;
import sk.fiit.takacErik.devices.active.Switch;
import sk.fiit.takacErik.devices.passive.Cable;
import sk.fiit.takacErik.devices.passive.Outlet;
import sk.fiit.takacErik.includes.Locality;

import java.util.ArrayList;

public class Technician extends Employee{
    public Technician(String name, String surname) {
        super(name, surname);
    }

    //technik pri instalacii skontroluje ci ma dostatok zariadeni, ak nie, posle ziadost sales
    public boolean installClient(Client client, Locality locality){
        ArrayList<Device> installDevices = whatDevices(locality);
        ArrayList<Device> missingDevices= new ArrayList<>();
        boolean added = false;

        for (Device device: installDevices
             ) {
            if(!checkAvalibilityOfDevices(device,this.getCompany().getWarehouse())){
                missingDevices.add(device);
                added = true;
            }
        }

        if(missingDevices.size() > 0 && added){
            //System.out.println("Sales naskladnuje...-> " + missingDevices.size());
            for (Device device:missingDevices
                 ) {
                this.getCompany().getSales().get(0).fillWarehouse(device, this.getCompany().getWarehouse());
            }
            return false;
        }

        System.out.println("Zariadenia dostupne, instalujem...");

        client.setDevices(installDevices);
        client.hasService = true;

        //ak chcete vidieť, či sa zariadenia zmazu, odkomentujte nasledovne sekcie
        //printWarehouse("Pred");

        //dane zariadenia treba vyskladnit.. potom spravit opat check, aby naskladnene boli
        this.removeDevices(installDevices);

        //aj tuto - stav po odstraneni
        //printWarehouse("Po");

        return true;
    }

    //dane zariadenia sa odstrania zo skladu
    private void removeDevices(ArrayList<Device> devices){
        for (Device device:devices
             ) {
           this.getCompany().getWarehouse().removeDevice(device);
        }
        System.out.println("\tZariadenia vyskladnene");
    }

    private ArrayList<Device> whatDevices(Locality locality){
        ArrayList<Device> devices = new ArrayList<>();

        if(locality.getLocType().equals("fiber")){
            //System.out.println("Fiber");
            devices.add(new ONT());

        }
        else if(locality.getLocType().equals("metalic")){
            //System.out.println("Metalic");
            devices.add(new Router());
        }

        devices.add(new Cable());
        devices.add(new Outlet());

        return devices;
    }

    public boolean checkAvalibilityOfDevices(Device device, Warehouse warehouse){

        if (device instanceof ONT){
            return warehouse.getOnts().size() > 0;
        }
        else if (device instanceof Router){
            return warehouse.getRouters().size() > 0;
        }
        else if (device instanceof Switch){
            return warehouse.getSwitches().size() > 0;
        }
        else if (device instanceof Cable){
            return warehouse.getCables().size() > 0;
        }
        else if (device instanceof Outlet){
            return warehouse.getOutlets().size() > 0;
        }

        return false;
    }
}
