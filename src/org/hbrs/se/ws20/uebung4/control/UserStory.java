package org.hbrs.se.ws20.uebung4.control;

import java.io.Serializable;

public class UserStory implements Comparable <UserStory>, Serializable {
    private final transient int id;
    private String name;
    private int aufwand = 0;
    private int mehrwert = 0;
    private int risiko = 0;
    private int strafe = 0;
    private double prio= 0;


    public UserStory(int id, String name,int aufwand, int mehrwert, int risiko, int strafe ) {
        this.id = id;
        this.aufwand = aufwand;
        this.mehrwert = mehrwert;
        this.risiko = risiko;
        this.strafe = strafe;
        assert (aufwand + risiko != 0);
        this.prio = (mehrwert + strafe) / (aufwand + risiko);
    }

    @Override
    public int compareTo(UserStory us) {
        if(id == us.getID()) return 1;
        return 0;


    }
    public int getID(){
        return this.id;
    }

}
