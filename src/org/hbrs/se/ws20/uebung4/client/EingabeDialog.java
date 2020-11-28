package org.hbrs.se.ws20.uebung4.client;

import org.hbrs.se.ws20.uebung4.control.Container;
import org.hbrs.se.ws20.uebung4.persistence.PersistenceException;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;

public class EingabeDialog {

    public void beginnEingabe() {
        String eingabe = null;
        boolean status = true;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (status){
            try{
                eingabe = reader.readLine();
            }
            catch(IOException e){
                e.printStackTrace();
            }
            String [] arrayEingaben = eingabe.split(" ");

            switch(arrayEingaben[0]) {
                case "help":
                    System.out.println("Folgende Befehle können ausgeführt werden: enter, store, store, load, dump, exit, help)");
                    break;
                case "enter":

                    break;
                case "store":
                    try{
                        Container.getInstance().store();

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (PersistenceException e) {
                        e.printStackTrace();
                    }
                    break;
                case "load":
                    try {
                        Container.getInstance().load();
                    } catch (EOFException e) {
                        e.printStackTrace();
                    } catch (PersistenceException e) {
                        e.printStackTrace();
                    }
                    break;
                case "dump":
                    break;
                case "exit":
                    System.out.println("Eingabe wurde beendet!");
                    status = false;
                    break;
            }

        }
    }
}
