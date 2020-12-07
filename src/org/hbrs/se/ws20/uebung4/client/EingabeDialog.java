package org.hbrs.se.ws20.uebung4.client;

import org.hbrs.se.ws20.uebung4.persistence.PersistenceStrategyStream;
import org.hbrs.se.ws20.uebung4.control.Container;
import org.hbrs.se.ws20.uebung4.control.ContainerException;
import org.hbrs.se.ws20.uebung4.control.UserStory;
import org.hbrs.se.ws20.uebung4.persistence.PersistenceException;
import org.hbrs.se.ws20.uebung4.persistence.PersistenceStrategy;
import org.hbrs.se.ws20.uebung4.view.AusgabeDialog;
import org.hbrs.se.ws20.uebung4.view.Main;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EingabeDialog {



    public void beginnEingabe() {


        String name = "";
        int id = 0;
        int aufwand = 1;
        int mehrwert = 1;
        int risiko = 1;
        int strafe = 1;
        double prio = ((double)mehrwert + (double)strafe) / ((double)aufwand + (double)risiko);
        UserStory us = new UserStory(id,name,aufwand,mehrwert,risiko,strafe);
        String eingabe = null;
        //Container container = Main.container;
        Container container = Container.getInstance();
        //PersistenceStrategy<UserStory> usPss = new PersistenceStrategyStream<UserStory>();
        //container.setPss(new PersistenceStrategyStream<UserStory>());
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
                default:  System.out.println("Für Hilfe schreiben sie 'help'");
                break;
                case "help":
                    System.out.println("Folgende Befehle können ausgeführt werden: enter, store, load, dump, exit, help)");
                    break;
                case "enter":

                    try {
                        System.out.println("Bitte geben sie die ID als Zahl ein:");
                        id = Integer.parseInt(reader.readLine());

                        System.out.println("Bitte geben sie den Namen der Userstory ein:");
                        name = reader.readLine();
                        System.out.println("Bitte geben sie einen Wert der Fibonacci-Folge für den Aufwand ein:");
                        aufwand = Integer.parseInt(reader.readLine());
                        if(isFib(aufwand) == false ){ System.out.println("Bitte starten sie erneut und geben sie einen gültigen Wert ein!"); break;}
                        System.out.println("Bitte geben sie einen Wert zwischen 1 und 5 für den Mehrwert ein:");
                        mehrwert = Integer.parseInt(reader.readLine());
                        System.out.println("Bitte geben sie einen Wert zwischen 1 und 5 für das Risiko ein:");
                        risiko = Integer.parseInt(reader.readLine());
                        System.out.println("Bitte geben sie einen Wert zwischen 1 und 5 für die Strafe ein:");
                        strafe = Integer.parseInt(reader.readLine());


                        //prio = ((double)mehrwert + (double)strafe) / ((double)aufwand + (double)risiko);


                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (NumberFormatException e) {
                        System.out.println("Falsches Format, starten sie erneut mit enter!");
                    }

                    us = new UserStory(id, name, aufwand, mehrwert, risiko, strafe);
                    try {
                        //Container.getInstance();
                        container.addUserstory(us);
                    } catch (ContainerException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Größe des Containers: " +container.size());
                    //System.out.println(us.toString());

                    break;
                case "store":

                        //container.setPss(new PersistenceStrategyStream<UserStory>());
                        try {
                            Container.getInstance().store();

                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (PersistenceException e) {
                            e.printStackTrace();
                        }
                        break;
                case "load":
                    if (arrayEingaben.length > 1) {


                        if (arrayEingaben[1].equals("merge")) { //force load


                            try {
                                container.load(true); //false = force
                            } catch (EOFException | PersistenceException e) {
                                e.printStackTrace();
                                break;
                            }
                            System.out.println("Load merge erfolgreich durchgeführt!");
                            break;
                        } else if (arrayEingaben[1].equals("force")) { //merge load


                            try {
                                container.load(false); //true = merge
                            } catch (EOFException e) {
                                e.printStackTrace();
                                break;
                            } catch (PersistenceException e) {
                                e.printStackTrace();
                                break;
                            }
                            System.out.println("Load force erfolgreich durchgeführt!");
                            break;
                        } else System.out.println("Es wurde kein gültiger Parameter übergeben");
                    }
                    System.out.println("Schreiben sie ausschließlich 'load force' für force oder 'load merge' für merge");

                case "dump":
                    AusgabeDialog ad = new AusgabeDialog();
                    Collections.sort(container.getCurrentList());
                    if (arrayEingaben.length > 1) {
                        if (arrayEingaben[1].equals("aufwand")) {
                            int dAufwand = Integer.parseInt(arrayEingaben[2]);
                            ad.dumpParameter(container, dAufwand); //Aufruf von Ausgabedialog in View mit Parameter
                            break;
                        }
                    }
                    ad.dump();//Aufruf von Ausgabedialog in View ohne Parameter
                    break;
                case "exit":
                case "stop":
                    System.out.println("Eingabe wurde beendet!");
                    status = false;
                    break;
//                case "clear":
//
//
//                    try {
//                        usPss.save(new ArrayList<UserStory>());
//                        //Container.getInstance().store();
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } catch (PersistenceException e) {
//                        e.printStackTrace();
//                    }
//                    break;


            }

        }
    }
    public boolean isFib(int i) {
        int firstTerm = 0;
        int secondTerm = 1;
        int thirdTerm = 0;
        while (thirdTerm < i) {
            thirdTerm = firstTerm + secondTerm;
            firstTerm = secondTerm;
            secondTerm = thirdTerm;
        }
        if (thirdTerm == i) {
            return true;
        } else {
            return false;
        }
    }

}
