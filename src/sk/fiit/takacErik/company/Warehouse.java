package sk.fiit.takacErik.company;
import java.util.Random;
import sk.fiit.takacErik.devices.Device;
import sk.fiit.takacErik.devices.active.ONT;
import sk.fiit.takacErik.devices.active.Router;
import sk.fiit.takacErik.devices.active.Switch;
import sk.fiit.takacErik.devices.passive.Cable;
import sk.fiit.takacErik.devices.passive.Outlet;

import java.util.ArrayList;

public class Warehouse {

    private int warehouseState; //full or not
    private final ArrayList<ONT> onts = new ArrayList<>();
    private final ArrayList<Router> routers = new ArrayList<>();
    private final ArrayList<Switch> switches = new ArrayList<>();
    private final ArrayList<Cable> cables = new ArrayList<>();
    private final ArrayList<Outlet> outlets = new ArrayList<>();

    public ArrayList<ONT> getOnts() {
        return onts;
    }

    public ArrayList<Cable> getCables() {
        return cables;
    }

    public ArrayList<Outlet> getOutlets() {
        return outlets;
    }

    public ArrayList<Router> getRouters() {
        return routers;
    }

    public ArrayList<Switch> getSwitches() {
        return switches;
    }

    public void setWarehouseState(int warehouseState) {
        this.warehouseState = warehouseState;
    }

    //funkcia dostane instanciu zariadenia a tolko ich nasledne vytvori a pri do arrayu
    public void addDevice(Device device, int numOfDevices){

        if(device instanceof ONT){
            System.out.println(device.getDescription());
            for(int i = 0; i < numOfDevices; i++){
                this.onts.add(new ONT());
            }
        }
        else if(device instanceof Router){
            //System.out.println(device.getDescription());
            for(int i = 0; i < numOfDevices; i++){
                routers.add(new Router());
            }
        }
        else if(device instanceof Switch){
            //System.out.println(device.getDescription());
            for(int i = 0; i < numOfDevices; i++){
                switches.add(new Switch());
            }
        }
        else if(device instanceof Cable){
            //System.out.println(device.getDescription());
            for(int i = 0; i < numOfDevices; i++){
                cables.add(new Cable());
            }
        }
        else if(device instanceof Outlet){
            //System.out.println(device.getDescription());
            for(int i = 0; i < numOfDevices; i++){
                outlets.add(new Outlet());
            }
        }
        else {
            System.out.println("Unknown device type...");
        }
    }

    //Technik zavola funkciu, ktora vymaze zariadenia zo skladu a zaroven prestanu extistovat -> pouzije sa destruktor a garbage collector javy
    public void removeDevice(Device device){
        //System.out.println("Device: " + device);

        Random rand = new Random();
        if(device instanceof ONT){
            int index = rand.nextInt(onts.size());
            ONT ont = onts.get(index); //zariadenie ktore sa vrati a potom vymaze destruktorom
            this.onts.remove(index);

            //destruktor pre potomkov rozhrania Device
            ont.removeMe();
            ont = null;
            System.gc();
        }
        else if(device instanceof Router){
            int index = rand.nextInt(routers.size());
            Router router = routers.get(index); //zariadenie ktore sa vrati a potom vymaze destruktorom
            this.routers.remove(index);

            //destruktor pre potomkov rozhrania Device
            router.removeMe();
            router = null;
            System.gc();
        }
        else if(device instanceof Switch){
            int index = rand.nextInt(switches.size());
            Switch aswitch = switches.get(index); //zariadenie ktore sa vrati a potom vymaze destruktorom
            this.switches.remove(index);

            //destruktor pre potomkov rozhrania Device
            aswitch.removeMe();
            aswitch = null;
        }
        else if(device instanceof Cable){
            int index = rand.nextInt(cables.size());
            Cable cable = cables.get(index); //zariadenie ktore sa vrati a potom vymaze destruktorom
            this.cables.remove(index);

            //destruktor pre potomkov rozhrania Device
            cable.removeMe();
            cable = null;
        }
        else if(device instanceof Outlet){
            int index = rand.nextInt(outlets.size());
            Outlet outlet = outlets.get(index); //zariadenie ktore sa vrati a potom vymaze destruktorom
            this.outlets.remove(index);

            //destruktor pre potomkov rozhrania Device
            outlet.removeMe();
            outlet = null;
        }

        System.gc();
    }

    public int getWarehouseState() {
        return warehouseState;
    }

    public void getWarehouseDetails(){
        System.out.println("\tPolozka \tKS");
        System.out.println("------------------------------");
        System.out.println("\tONT: \t\t| " + onts.size());
        System.out.println("\tRouters: \t| " + routers.size());
        System.out.println("\tSwitches: \t| " + switches.size());
        System.out.println("\tCables: \t| " + cables.size());
        System.out.println("\tOutlets: \t| " + outlets.size());
    }
}
