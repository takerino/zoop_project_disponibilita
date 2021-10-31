package sk.fiit.takacErik;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import sk.fiit.takacErik.company.Company;
import sk.fiit.takacErik.devices.Device;

import java.util.Scanner;

public class Runtime {

    Scanner volba = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Vitaj v simulatore pre instalaciu sluzby u klienta, vyber si z nasledovnych moznosti:");
        System.out.println("1 - pridaj, pre pridanie klienta do systemu");
        System.out.println("2 - sluzba - pre kontrolu dostupnosti sluzby pre klienta");
        System.out.println("3 - install - pre zadanie instalacie sluzby pre klienta");

        Runtime beh = new Runtime();

        String volba = beh.volba.next();

        System.out.println(volba);

        Scanner udaje = new Scanner(System.in);

        switch (volba){
            case "pridaj":
                //vypytam si udaje o klientovi a pridam ho do "db"
                System.out.println("Zadaj meno klienta: ");
                Client client = new Client();


                break;
            case "sluzba":
                //vypytam si klienta - id
                break;
            case "install":
                //vypytam si klienta a nasledne typ sluzby, ktory chce nainstalovat, potom sa skontroluje moznost vykonania
                break;
        }

        Company company = new Company();
        company.pridajAdresu("Novakova ulica");

        company.pridajAdresu("Stromova");
        Client client = new Client();

        boolean info = client.askInstallation("Novakova ulica", company.getAdresy());

        if (info){
            System.out.println("Na danej adrese poskytujeme sluzbu");
        }

        Scanner s = new Scanner(System.in);

        String string = s.next();

        if (string.equals("volba")){
            System.out.println("Vyber z moznosti");
        }


        Device nieco = new Device("Dlink", "pass");
        System.out.println(nieco);


    }
}
