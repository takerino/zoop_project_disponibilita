package sk.fiit.takacErik.projBuilder;
import sk.fiit.takacErik.company.*;
import sk.fiit.takacErik.devices.Device;
import sk.fiit.takacErik.devices.active.ONT;
import sk.fiit.takacErik.devices.active.Router;
import sk.fiit.takacErik.devices.active.Switch;
import sk.fiit.takacErik.devices.passive.Cable;
import sk.fiit.takacErik.devices.passive.Outlet;
import sk.fiit.takacErik.includes.Address;
import sk.fiit.takacErik.includes.Locality;

import java.util.ArrayList;

//Tato trieda sluzi pre vytvorenie skeletu aplikacie -> vytvori vsetko potrebne preto, aby svet mohol fungovat na zaklade ziskanych parametrov

public class BaseBuilder {

    //builderu posleme na zaciatok pocet klientov, zamestnancov a pocet zasob na sklade - vsetky zariadenia budu vytvorene v danom pocte
    public BaseBuilder(int nClients, int nEmployees, int whState){
        if(nEmployees < 2){
            nEmployees = 2;
        }

        this.nClients = nClients;
        this.nEmployees = nEmployees;
        this.whState = whState;
    }

    //ATTRIBUTES

    private final ArrayList<String> names = new ArrayList<>();
    private final ArrayList<String> surnames = new ArrayList<>();
    private final ArrayList<String> clientSurnames = new ArrayList<>();
    private final ArrayList<Address> addresses = new ArrayList<>();
    private final ArrayList<Locality> localities = new ArrayList<>();

    private Warehouse warehouse;
    private final int nClients;
    private final int nEmployees;
    private final int whState;

    // METHODS

    public void addNames(){
        this.names.add("Peter");
        this.names.add("Milan");
        this.names.add("Andrej");
        this.names.add("Jakub");
        this.names.add("Lukáš");
        this.names.add("Juraj");
    }

    public void addSurnames(){
        this.surnames.add("Červený");
        this.surnames.add("Dvorský");
        this.surnames.add("Blaho");
        this.surnames.add("Kováč");
        this.surnames.add("Molnár");
        this.surnames.add("Lukáč");
    }

    public void addClientSurenames(){
        this.clientSurnames.add("Varga");
        this.clientSurnames.add("Toth");
        this.clientSurnames.add("Balaz");
        this.clientSurnames.add("Nagy");
        this.clientSurnames.add("Balog");
        this.clientSurnames.add("Szabo");
    }

    public void addAddreses(){
        this.addresses.add(new Address("Slovensko", "Bratislava", "Sancova", 4));
        this.addresses.add(new Address("Slovensko", "Bratislava", "Slnecna", 2));
        this.addresses.add(new Address("Slovensko", "Bratislava", "Biela", 9));
        this.addresses.add(new Address("Slovensko", "Bratislava", "Mytna", 12));
        this.addresses.add(new Address("Slovensko", "Bratislava", "Grunty", 46));
        this.addresses.add(new Address("Slovensko", "Bratislava", "Ilkovicova", 46));
    }

    /*
    public ArrayList <Employee> createEmployees(){
        int sizeOfNames = this.names.size() - 1;
        int sizeOfSurnames = this.surnames.size() - 1;
        int i;

        ArrayList <Employee> employees = new ArrayList<>();

        int randomNum;
        int randomNum2;

        for(i = 0; i < this.nEmployees; i++){
            randomNum = (int)(Math.random()*sizeOfNames);
            randomNum2 = (int)(Math.random()*sizeOfSurnames);

            //System.out.println("Ramdoms: " + randomNum + " " + randomNum2);

            Employee employee = new Employee(this.names.get(randomNum), this.surnames.get(randomNum2));
            employees.add(employee);
        }

        //System.out.println("Vytvoril som " + i +" zamestnancov...");
        return employees;
    }
    */

    public ArrayList <Client> createClients(Sales sales){
        ArrayList <Client> clients = new ArrayList<>();
        int sizeOfNames = this.names.size();
        int sizeOfSurnames = this.clientSurnames.size();
        int sizeOfAddreses = this.addresses.size();
        int i;

        int randomNum, randomNum2, randomNum3;

        //System.out.println("Size of names: " + sizeOfNames);

        for(i = 0; i < this.nClients; i++){
            randomNum = (int)(Math.random()*sizeOfNames);
            randomNum2 = (int)(Math.random()*sizeOfSurnames);
            randomNum3 = (int)(Math.random()*sizeOfAddreses);

            Client client = new Client(this.names.get(randomNum), this.clientSurnames.get(randomNum2), sales.getClientId(), this.addresses.get(randomNum3));
            sales.setClientId(); //iba zvysim id pre dalsieho klienta
            clients.add(client);
        }

        //System.out.println("Vytvoril som " + i +" klientov...");
        return clients;
    }

    public ArrayList <Technician> assignTechnicians(){
        ArrayList <Technician> technicians = new ArrayList<>();

        int numOfTech = nEmployees / 2;
        int rand1, rand2;

        for (int i = 0; i < numOfTech; i++){
            rand1 = (int)(Math.random() * (names.size())-1);
            rand2 = (int)(Math.random() * (surnames.size())-1);

            technicians.add(new Technician(this.names.get(rand1), this.surnames.get(rand2)));
        }

        return technicians;
    }

    public ArrayList<Sales> assignSales(){
        ArrayList<Sales> sales = new ArrayList<>();

        int numOfSales = nEmployees / 2;
        int rand1, rand2;

        for (int i = 0; i < numOfSales; i++){
            rand1 = (int)(Math.random() * (names.size())-1);
            rand2 = (int)(Math.random() * (surnames.size())-1);

            sales.add(new Sales(this.names.get(rand1), this.surnames.get(rand2)));
        }

        return sales;
    }

    public ArrayList<Address> getAddresses() {
        return addresses;
    }

    public ArrayList<Locality> getLocalities() {
        return localities;
    }

    public void addLocality(Locality locality){
        localities.add(locality);
    }

    public ArrayList<Locality> createLocality(ArrayList<Address> addresses){
        ArrayList<Locality> localities = new ArrayList<>();
        int num = 1;
        for (Address addr: addresses
             ) {
            if(num % 2 == 1){
                localities.add(new Locality(addr, "BA"+num, "fiber"));
            }
            else {
                localities.add(new Locality(addr, "BA"+num, "metalic"));
            }
            //System.out.println(localities.get(num-1).getDescription());
            num++;
        }

        return localities;
    }

    public Warehouse createWarehouse(int numOfDevices){
        Warehouse warehouse = new Warehouse();

        for(int i = 0; i < numOfDevices; i++){
            warehouse.getOnts().add(new ONT());
            warehouse.getRouters().add(new Router());
            warehouse.getSwitches().add(new Switch());
            warehouse.getCables().add(new Cable());
            warehouse.getOutlets().add(new Outlet());
        }
        warehouse.setWarehouseState(numOfDevices);

        return warehouse;
    }

    private void printInfo(ArrayList<Client> clients, ArrayList<Technician> technicians, ArrayList<Sales> sales, ArrayList<Address> addresses, ArrayList<Locality> localities){
        System.out.println("Created clients:");
        for (Client client: clients
        ) {
            System.out.println("\t[" + client + ", " + client.getFirstName() + ", " + client.getSurname() + ", " + client.getAddress() + "]");
        }

        System.out.println("Created Technicians:");
        for (Technician techician: technicians
        ) {
            System.out.println("\t[" + techician + ", " + techician.getFirstName() + ", " + techician.getSurname() +"]");
        }

        System.out.println("Created Sales:");
        for (Sales sale:sales
        ) {
            System.out.println("\t["+ sale + ", " + sale.getFirstName() + ", " + sale.getSurname() +"]");
        }

        System.out.println("Created addresses:");
        for (Address address:addresses
        ) {
            System.out.println("\t["+ address + ", " + address.getCity() + ", " + address.getStreet() +"]");
        }

        System.out.println("Created localities:");
        for (Locality locality:localities
             ) {
            System.out.println("\t[" + locality.getDescription() + ", " + locality.getAddress().getCountry() + ", " + locality.getAddress().getCity() + ", " + locality.getLocType() +"]");
        }
    }

    //funkcia vytvori default triedu company, ktora bude mat nejake zakladne parametre pre fungovanie
    public Company createCompany(){
        this.addNames();
        this.addSurnames();
        this.addClientSurenames();
        this.addAddreses();

        Warehouse warehouse = createWarehouse(whState);
        //ArrayList<Employee> employees = createEmployees();
        ArrayList<Technician> technicians = assignTechnicians();
        ArrayList<Sales> sales = assignSales();
        ArrayList<Client> clients = createClients(sales.get(0));
        ArrayList<Locality> localities = createLocality(addresses);

        Company company = new Company(technicians, sales, clients, this.getAddresses(), names, surnames, localities, warehouse);

        for (Sales sale :sales
                ) {
            sale.setCompany(company);
        }
        for (Technician technician:technicians
             ) {
            technician.setCompany(company);
        }

        //V pripade, ak chcete vidieť, čo táto trieda robí, odkomentujte riadok nižšie a spraví sa výpis priradených hodnôt a adries
        //printInfo(clients, technicians, sales, addresses, localities);
        return company;
    }

}
