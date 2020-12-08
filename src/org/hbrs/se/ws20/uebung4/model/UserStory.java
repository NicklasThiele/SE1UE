package org.hbrs.se.ws20.uebung4.model;

import java.io.Serializable;

public class UserStory implements Comparable <UserStory>, Serializable {
    private int id;
    private String name;



    private int aufwand = 0;
    private int mehrwert = 0;
    private int risiko = 0;
    private int strafe = 0;
    private double prio= 0.0;


    public UserStory(int id, String name,int aufwand, int mehrwert, int risiko, int strafe) {
        this.id = id;
        this.name = name;
        this.aufwand = aufwand;
        this.mehrwert = mehrwert;
        this.risiko = risiko;
        this.strafe = strafe;
        //assert (aufwand + risiko != 0);
        this.prio = ((double)mehrwert + (double)strafe) / ((double)aufwand + (double)risiko);
    }
    public double getPrio(){
        return prio;
    }
    public int getAufwand() { return aufwand; }

    @Override
    public int compareTo(UserStory us) {
        if(us.getPrio() == this.getPrio()) {
            return 0;
        } else if(us.getPrio() > this.getPrio()) {
            return 1;
        }
        return -1;
    }
    public int getID(){
        return this.id;
    }
    public String getName(){return this.name;}

    @Override
    public String toString() {
        return "UserStory:" +
                " \n ID:    \t\t" + getID() +
                " \n Priorität:\t\t" + prio +
                " \n Name:  \t\t" + name +
                " \n Aufwand:\t\t" + aufwand +
                " \n Mehrwert:\t\t" + mehrwert +
                " \n Risiko:\t\t" + risiko +
                " \n Strafe:\t\t" + strafe +"\n\n";

    }
}
