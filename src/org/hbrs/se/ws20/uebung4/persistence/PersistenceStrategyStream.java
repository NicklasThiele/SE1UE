package org.hbrs.se.ws20.uebung4.persistence;

import java.io.*;
import java.util.List;

public class PersistenceStrategyStream<Member> implements PersistenceStrategy<Member> {
    private FileOutputStream fos = null;
    private ObjectOutputStream oos = null;
    private FileInputStream fis = null;
    private ObjectInputStream ois = null;
    private String file = "file.ser";
    //private String file = "file.txt";

    public void setFile(String Location){
        file = Location;
    }
    @Override
    public void openConnection() throws PersistenceException {
        try {
            fos = new FileOutputStream(file);
            //
            //fis = new FileInputStream(file);
          //ois = new ObjectInputStream(fis);
        } catch (FileNotFoundException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "Error is opening in the connections, file could not be found");
        }
        try {
            oos = new ObjectOutputStream(fos);
        }
        catch(IOException e){
            throw new PersistenceException( PersistenceException.ExceptionType.ConnectionNotAvailable
                    , "Error in opening the connection, problems with the stream");

        }
    }

    @Override
    public void closeConnection() throws PersistenceException {
        try {
            if(fos!=null) fos.close();
            if(oos!=null) oos.close();
            if(fis!=null) fis.close();
            if(ois!=null) ois.close();
        } catch (IOException e) {
            throw new PersistenceException(PersistenceException.ExceptionType.ClosingFailure , "error while closing connections");
        }


    }

    @Override
    /*
     * Method for saving a list of Member-objects to a disk (HDD)
     */
    public void save(List<Member> member) throws PersistenceException {
        this.openConnection();
        try{
            //FileOutputStream fos = new FileOutputStream("file.ser");
            //ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(member);
           // oos.close();
            //fos.close();
        }
        catch (IOException e){
            e.printStackTrace();
            throw new PersistenceException( PersistenceException.ExceptionType.LoadFailure , "Fehler beim Speichern der Datei!");
        }
        finally {
            //fos.close();
            //oos.close();
            this.closeConnection();
        }
    }

    @Override
    /*
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples come for free :-)
     */
    public List<Member> load() throws PersistenceException {
        // Some Coding hints ;-)
        //ObjectInputStream ois = null;
        //FileInputStream fis = null;
        //List<Member> newListe =  null;
        //

        // Initiating the Stream (can also be moved to method openConnection()... ;-)
        /*
        try {

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

         */
        //openConnection();
        List<Member> newListe = null;

        // Reading and extracting the list (try .. catch ommitted here)
        //Object obj = null;
        try {
            // Create Streams here instead using "this.openConnection();"
            fis = new FileInputStream("file.ser");
            ois = new ObjectInputStream(fis);

            // Auslesen der Liste
            Object obj = ois.readObject();

            if (obj instanceof List<?>) {
                newListe = (List) obj;
            }
            try {
                System.out.println("LOG: Es wurden " + newListe.size() + " User Stories erfolgreich reingeladen!");
            }
            catch(NullPointerException e){
                throw new NullPointerException();
            }
            return newListe;
        }
        catch (IOException e){
            e.printStackTrace();
            throw new PersistenceException( PersistenceException.ExceptionType.LoadFailure , "Fehler beim Laden der Datei!");
        }
        catch(ClassNotFoundException e){
            // Chain of Responsbility erfuellt, durch Throw der Exceotion kann UI
            // benachrichtigt werden!
            throw new PersistenceException( PersistenceException.ExceptionType.LoadFailure , "Fehler beim Laden der Datei! Class not found!");
        }
        finally {
            this.closeConnection();
        }


        // and finally close the streams (guess where this could be...?)


    }
}