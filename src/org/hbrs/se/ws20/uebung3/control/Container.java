package org.hbrs.se.ws20.uebung3.control;

import org.hbrs.se.ws20.uebung3.persistence.PersistenceException;
import org.hbrs.se.ws20.uebung3.persistence.PersistenceStrategy;
import org.hbrs.se.ws20.uebung3.persistence.PersistenceStrategyStream;

import java.util.ArrayList;
import java.util.List;

public class Container {
    /*Es soll zur Laufzeit zugesichert werden,
    dass vonder Klasse Containernur ein einziges
    Mal ein Objekterzeugt werden kannund somit
    nur ein Objekt davon im Speicher existiert.
     */
    private static Container instance; //= null

    private Container(){} //singleton privat verhindert Zugriff auf Erzeugung
    public static void reSetInstance(){
        instance = null;
    }
    public static synchronized Container getInstance(){ //synchronized = max eine Benutzung der Methode um parallele Erzeugung zu verhindern
        if(instance == null){
            instance = new Container();
        }
        return instance;
    }
    public List<Member> aList = new ArrayList<Member>();

    public void addMember(Member member) throws ContainerException {
        //ist Objekt schon in Cointainer? Dann Exceptionopjekt erzeugen mit ID und schmeißen
        if (contains(member)){
            ContainerException exception = new ContainerException();
            exception.setExceptionID(member.getID());
            throw exception;
        }
        aList.add(member);
    }
    private boolean contains(Member member) { //Hilfsmethode
        for(Member x : aList){
            if(member.getID().equals(x.getID())){
                return true;
            }
        }
        return false;
    }
    private Member getMember(Integer id){ //Hilfsmethode
        for(Member x : aList){
            if(id.equals(x.getID())) {
                return x;
            }
        }
        return null;
    }
    public List<Member> getCurrentList(){
        return aList;
    }
    public String deleteMember (Integer id) {
        Member x = getMember(id);
        if(x == null) {
            return "Member ist nicht vorhanden";
        }
        else {
            aList.remove(x);
            return "Der Member mit der ID "+ id + " wurde erfolgreich gelöscht";
        }
    }
    public void dump(){
        for(Member x : aList){
            System.out.print(x.toString());
        }
    }

    public int size(){
        return aList.size();
    }
    public void store() throws PersistenceException{

    }

    public void load() throws PersistenceException{
        //aList = PersistenceStrategyStream.load();
    }
}
