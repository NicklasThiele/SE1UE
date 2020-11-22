package org.hbrs.se.ws20.uebung3.persistence;

import java.io.*;
import java.util.List;

public class PersistenceStrategyStream<Member> implements PersistenceStrategy<Member> {
    FileOutputStream fos = null;
    ObjectOutputStream oos = null;
    FileInputStream fis = null;
    ObjectInputStream ois = null;
    String file = "file.txt";
    @Override
    public void openConnection() throws PersistenceException {
        try {
            fos = new FileOutputStream("file.txt");
            oos = new ObjectOutputStream(fos);
            fis = new FileInputStream("file.txt");
            ois = new ObjectInputStream(fis);
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    @Override
    public void closeConnection() throws PersistenceException {
        try {
            fos.close();
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     */
    public void save(List<Member> member) throws PersistenceException  {
        openConnection();
        try{
            oos.writeObject(member);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            closeConnection();
        }
    }

    @Override
    /**
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples come for free :-)
     */
    public List<Member> load() throws PersistenceException  {
        // Some Coding hints ;-)
        //ObjectInputStream ois = null;
        //FileInputStream fis = null;
        //List<Member> newListe =  null;
        //
        openConnection();
        // Initiating the Stream (can also be moved to method openConnection()... ;-)
        try {
            fis = new FileInputStream( " a location to a file" );
            ois = new ObjectInputStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Member> newListe = null;

        // Reading and extracting the list (try .. catch ommitted here)
        Object obj = null;
        try {
            obj = ois.readObject();

            if (obj instanceof List<?>) {
                newListe = (List) obj;
            }
            return newListe;
        }
        catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        finally {
            closeConnection();
        }
        return null;

        // and finally close the streams (guess where this could be...?)


    }
}