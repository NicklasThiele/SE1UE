package org.hbrs.se.ws20.uebung4.model;

import org.hbrs.se.ws20.uebung4.model.persistence.PersistenceException;
import org.hbrs.se.ws20.uebung4.model.persistence.PersistenceStrategy;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Container {
    /*Es soll zur Laufzeit zugesichert werden,
    dass vonder Klasse Containernur ein einziges
    Mal ein Objekterzeugt werden kannund somit
    nur ein Objekt davon im Speicher existiert.
     */
    private static Container instance; //= null
    private PersistenceStrategy pss = null;

    private Container(){} //singleton privat verhindert Zugriff auf Erzeugung



    //PersistenceStrategy<Member>  pss = new PersistenceStrategyStream<Member>();



    public static void reSetInstance(){
        instance = null;
    }
    public static synchronized Container getInstance(){ //synchronized = max eine Benutzung der Methode um parallele Erzeugung zu verhindern
        if(instance == null){
            instance = new Container();
        }
        return instance;
    }
    public List<UserStory> aList = new ArrayList<>();

    public void addUserstory(UserStory userStory) throws ContainerException {
        //ist Objekt schon in Cointainer? Dann Exceptionopjekt erzeugen mit ID und schmeißen
        if (contains(userStory)){
            ContainerException exception = new ContainerException();
            exception.setExceptionID(userStory.getID());
            throw exception;
        }
        aList.add(userStory);
    }
    private boolean contains(UserStory us) { //Hilfsmethode
        for(UserStory x : aList){
            if(us.getID() == x.getID()){
                return true;
            }
        }
        return false;
    }
    private UserStory getUserstory(Integer id){ //Hilfsmethode
        for(UserStory x : aList){
            if(id.equals(x.getID())) {
                return x;
            }
        }
        return null;
    }
    public List<UserStory> getCurrentList(){

        return aList;
    }

    public String deleteUserStory (Integer id) {
        UserStory x = getUserstory(id);
        if(x == null) {
            return "UserStory ist nicht vorhanden";
        }
        else {
            aList.remove(x);
            return "Der UserStory mit der ID "+ id + " wurde erfolgreich gelöscht";
        }
    }
    public void dump(){
        for(UserStory x : aList){
            System.out.print(x.toString());
        }
    }

    public int size(){

        return aList.size();
    }

    public void store() throws PersistenceException, IOException {

        if(this.pss == null){
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "no Strategy set");
        }
        pss.save(aList);


    }
    public void setPss(PersistenceStrategy<UserStory> pss) {
        this.pss = pss;
    }

    public void load(boolean memoryType) throws PersistenceException, EOFException {
        if (this.pss == null) {
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "Strategy not initialized");
        }
        List<UserStory> liste = this.pss.load();
        if(memoryType) {
            try {
                for (UserStory us : liste) {
                    this.addUserstory(us);
                }
            } catch (ContainerException e) {
                e.printStackTrace();
            }
        } else {
            aList = liste;
        }
    }
    public void sortContainer (){
        Collections.sort(getCurrentList());
    }

}
