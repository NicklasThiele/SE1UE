package org.hbrs.se.ws20.uebung4.model;

public class ContainerException extends Exception{
    public Integer id;
    @Override
    public void printStackTrace() {
        System.out.println("Die Userstory mit der ID " +id+ " ist bereits vorhanden");
    }
    public void setExceptionID(Integer id){
        this.id = id;
    }
}
//testdsfghgfd