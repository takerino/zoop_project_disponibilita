package sk.fiit.takacErik;

import sk.fiit.takacErik.company.*;
import sk.fiit.takacErik.devices.active.ONT;
import sk.fiit.takacErik.devices.active.Router;
import sk.fiit.takacErik.devices.active.Switch;
import sk.fiit.takacErik.devices.passive.Cable;
import sk.fiit.takacErik.devices.passive.Outlet;
import sk.fiit.takacErik.includes.Address;
import sk.fiit.takacErik.includes.Locality;
import sk.fiit.takacErik.includes.Person;
import sk.fiit.takacErik.includes.TextColor;
import sk.fiit.takacErik.projBuilder.BaseBuilder;

import java.util.ArrayList;
import java.util.Scanner;


//Hlavna trieda celeho programu, ktora sluzi na spustenie celej aplikacie
public class Runtime {

    private ArrayList<Person> people;
    Scanner volba = new Scanner(System.in);

    public ArrayList<Person> createPeople(int n, ArrayList<String> names, ArrayList<String> surnames, ArrayList<Address> addresses){
        ArrayList<Person> people = new ArrayList<>();

        int sizeOfNames = names.size() -1 ;
        int sizeOfSurnames = surnames.size() -1;
        int sizeOfAddr = addresses.size() -1;
        int randomNum, randomNum2, randomNum3;

        for(int i = 0; i < n; i++){
            randomNum = (int)(Math.random() * sizeOfNames);
            randomNum2 = (int)(Math.random()* sizeOfSurnames);
            randomNum3 = (int)(Math.random() * sizeOfAddr);
            people.add(new Person(names.get(randomNum), surnames.get(randomNum2), addresses.get(randomNum3)));
        }
        return people;
    }

    //Funkcia pre beh programu v automatickom rezime - vytvori si klientov, overi dostupnost sluzby, overi dostupnost zariadeni v sklade a nainstaluje klientovi sluzbu
    public void run(Company company, int numOfClients, ArrayList<Person> people){
        System.out.println("\n--------------------------- AUTOMATIZOVANE PRIDANIE A ADMINISTRACIA ----------------------------------------");

        Sales sales = company.getSales().get(0);
        Technician technician = company.getTechnicians().get(0);

        // Pridam pozadovany pocet klientov do firmy a nainstalujem im sluzbu
        for(int i = 0; i < numOfClients; i++){
            Client client = sales.createClient(people.get(i));
            this.installClient(client, company, technician);
        }
    }

    //Vytvorenie klienta v manualnom rezime
    private Client createClient(Company company, ArrayList<String> params){
        Sales sales = company.getSales().get(0);

        Client client = sales.createClient(new Person(params.get(0), params.get(1)));
        Address clientAddress = new Address(params.get(2), params.get(3), params.get(4), Integer.parseInt(params.get(5)));
        client.setAddress(clientAddress);
        client.setId(sales.getClientId());
        sales.setClientId();

        return client;
    }

    //Vyziadanie informacie o buducom klientovi pre pridanie, problem ale bude, ze takyto klient sa neda nainstalovat nikdy, nakolko bude adresa neznama
    private ArrayList<String> getClientInfo(){
        ArrayList<String> params = new ArrayList<>();

        System.out.println("Zadaj meno:");
        params.add(this.volba.next());
        System.out.println("Zadaj priezvisko:");
        params.add(this.volba.next());

        System.out.println("Zadaj stat:");
        params.add(this.volba.next());
        System.out.println("Zadaj mesto:");
        params.add(this.volba.next());
        System.out.println("Zadaj ulicu - jednoslovne:");
        params.add(this.volba.next());
        System.out.println("Zadaj popisne cislo:");
        params.add(this.volba.next());

        return params;
    }

    private boolean installClient(Client client, Company company, Technician technician){
        Sales sales = company.getSales().get(0);

        if(sales.checkClientAccess(client.getAddress(), company.getAdresy())){
            Locality clientLocality = sales.installClient(client, company.getLocalities());

            while(!technician.installClient(client, clientLocality)){
                System.out.println(TextColor.red + "Nedostupné zariadenia - zadavam poziadavku sales" + TextColor.reset);

                try {
                    Thread.sleep(100);
                }
                catch (Exception e){
                    System.out.println(e.toString());
                }
            }

            try {
                Thread.sleep(251);
            }
            catch (Exception e){
                System.out.println("Exception!");
                System.out.println(e.toString());
            }

            if (client.hasService){
                System.out.println("Klient nainštalovaný technikom:");
                System.out.println("\t[" + client.getId() + ": " + client.getName() + ", " + printAddress(client.getAddress()) + ", " + "Funkcny]\n");
            }
            else {
                System.out.println("Klienta sa nepodarilo nainštalovať:");
                System.out.println("\t[" + client.getName() + ", " + printAddress(client.getAddress()) + ", " + "Nefunkcny]\n");
            }
        }
        return true;
    }

    //Podla zvolenej moznosti sa vykona pozadovana cinnostp prgramu
    private void manualRun(int option, Company company){
        int wanted;
        Client client;

        switch (option){
            case 0:
                System.out.println("Pocet pridanych klientov: ");
                int input = this.volba.nextInt();
                this.run(company, input , people);
                break;
            case 1:
                System.out.println("Pridanie klienta -> vytvorim rovno klienta, ziadne somriny!!");
                client = createClient(company, getClientInfo());
                System.out.println(client);
                break;
            case 2:
                System.out.println("Zadaj id klienta, ktoremu chces skontrolovat dostupnost:");

                wanted = this.volba.nextInt();

                if (company.getSales().get(0).checkId(wanted)){
                    System.out.println(TextColor.red + "Zadal si zle ID" + TextColor.reset);

                }
                else{
                    System.out.println("Id je v poriadku");
                    client = company.getSales().get(0).getClientById(wanted);


                    if(company.getSales().get(0).checkClientAccess(client.getAddress(), company.getAdresy())){
                        System.out.println(TextColor.green + "\tDostupne pripojenie" + TextColor.reset);
                    }
                    else {
                        System.out.println(TextColor.red + "\tNedostupne pripojenie" + TextColor.reset);
                    }
                }
                break;
            case 3:
                //vypyta si ID klienta, ktoreho chce nainstalovat, ak uz je nainstalovany, tak to vypise a nespravi nic
                System.out.println("Zadaj id klienta pre instalaciu:");
                wanted = this.volba.nextInt();

                if (company.getSales().get(0).checkId(wanted)){
                    System.out.println(TextColor.red + "Zle ID" + TextColor.reset);
                }
                else{
                    client = company.getSales().get(0).getClientById(wanted);

                    if(client.hasService){
                        System.out.println(TextColor.red + "Ma sluzbu, neinstalujem!" + TextColor.reset);
                        break;
                    }

                    if (installClient(client, company, company.getTechnicians().get(0))) {
                        System.out.println("Hotovo");
                    }
                }
                break;
            case 4:
                company.getSales().get(0).printClients();
                break;
            case 5:
                company.getWarehouse().getWarehouseDetails();
                break;
            case 6:
                System.out.println("Pocet typov zariadeni: ");
                int pocet = this.volba.nextInt();
                getTypeOfDevices(pocet, company);
                break;
            default:
                System.out.println(TextColor.red + "\tZadany zly parameter!" + TextColor.reset);
                printMenu();
                break;
        }

    }

    //Vyziadanie typu zariadenia a pridanie do skladu
    public void getTypeOfDevices(int n, Company company){

        System.out.println("Zadaj typ a za nim ciselne pocet ks");
        System.out.println("ONT - o");
        System.out.println("Router - r");
        System.out.println("Switch - s");
        System.out.println("Zasuvka - z");
        System.out.println("Kábel - k");

        int pc;
        for (int i = 0; i < n; i++){
            switch (this.volba.next()){
                case "o":
                    pc = this.volba.nextInt();
                    company.getSales().get(0).fillWarehouse(new ONT(), pc);
                    break;
                case "r":
                    pc = this.volba.nextInt();
                    company.getSales().get(0).fillWarehouse(new Router(), pc);
                    break;
                case "s":
                    pc = this.volba.nextInt();
                    company.getSales().get(0).fillWarehouse(new Switch(), pc);
                    break;
                case "z":
                    pc = this.volba.nextInt();
                    company.getSales().get(0).fillWarehouse(new Outlet(), pc);
                    break;
                case "k":
                    pc = this.volba.nextInt();
                    company.getSales().get(0).fillWarehouse(new Cable(), pc);
                    break;
                default:
                    return; //ukonci sa pridavanie

            }

            if(this.volba.next().equals("o")){
                System.out.println("Onts");
            }


        }



    }

    //Vypis adresy vo formate Ulica, Popisne cislo, Mesto, Stat
    public String printAddress(Address address){
        return address.getStreet() + " " +  address.getNumber() + ", " + address.getCity() + ", " + address.getCountry();
    }

    public static void printMenu() {
        System.out.println("Zvol možnosť číslom:");
        System.out.println("\t0 - automatizovane spravanie");
        System.out.println("\t1 - pridaj -  pridanie klienta do systemu");
        System.out.println("\t2 - sluzba - kontrola dostupnosti sluzby pre klienta a instalacia"); //skrz instalaciu u sales dostanem info...
        System.out.println("\t3 - install - pre zadanie instalacie sluzby pre klienta");
        System.out.println("\t4 - vypis klientov - pre jednoduchsie zadavanie parametrov");
        System.out.println("\t5 - kontrola skladu - vypis poctu jednotlivych zariadeni v sklade");
        System.out.println("\t6 - nakup - vyziada si typ a pocet na kupu");
        System.out.println("\t7 - ukonci - ukonci bez programu");
    }

    private void printInitialInfo(){
        System.out.println("Vitaj v simulatore ISP, pre zaciatok prosim zvol mod:");
        System.out.println("\tmanual - zadas pociatocne informacie pre vytvorenie firmy");
        System.out.println("\tauto - firma sa vytvori sama s pociat. parametrami");
    }

    //Ziskanie informacie pre vytvorenie zakladu firmy - pocet zamestnancov, klientov a zariadeni v sklade
    private ArrayList<Integer> getAutoModeParams (){
        ArrayList<Integer> params = new ArrayList<>();

        System.out.println("Pocet klientov:");
        params.add(this.volba.nextInt());
        System.out.println("Pocet zamestnancov:");
        params.add(this.volba.nextInt());
        System.out.println("Pocet zasob skladu (per item):");
        params.add(this.volba.nextInt());

        checkParams(params);

        return params;
    }

    //kontrola spravnosti zadanych udajov pouzivatelom
    private void checkParams(ArrayList<Integer> params){
        for (int i = 0; i < params.size(); i++){
            if (params.get(i) < 0){
                params.set(i, Math.abs(params.get(i)));
            }
        }
    }

    public static void main(String[] args) {

        Runtime runtime = new Runtime();

        runtime.printInitialInfo();

        String start = runtime.volba.next();

        ArrayList<Integer> params;

        if(start.equals("manual")){
            params = runtime.getAutoModeParams();
        }
        else{
            params = new ArrayList<>();
            params.add(2);
            params.add(2);
            params.add(5);
        }

        // Builder postavi zaklad aplikacie, nasledne sa dane parametre ulozia pod instanciu triedy Company a vytvorim si osoby, z ktorych cerpam v momente, kedy treba vytvarat klientov
        BaseBuilder builder = new BaseBuilder(params.get(0), params.get(1), params.get(2));
        Company company = builder.createCompany();
        runtime.people = runtime.createPeople(params.get(0) + 2, company.getNames(), company.getSurnames(), company.getAdresy());

        printMenu();

        int option;

        while((option = runtime.volba.nextInt()) != 7){
            runtime.manualRun(option, company);
            System.out.println("Zadaj dalsiu volbu: ");
        }
        System.out.println("Program skoncil uspesne");
    }
}