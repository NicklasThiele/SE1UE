package org.hbrs.se.ws20.uebung2.control;

import java.util.ArrayList;
import java.util.List;

public class Container {
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
    private boolean contains(Member member) {
        for(Member x : aList){
            if(member.getID().equals(x.getID())){
                return true;
            }
        }
        return false;
    }
    private Member getMember(Integer id){
        for(Member x : aList){
            if(id.equals(x.getID())) {
                return x;
            }
        }
        return null;
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
}
