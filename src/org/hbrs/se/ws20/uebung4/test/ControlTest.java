package org.hbrs.se.ws20.uebung4.test;

import org.hbrs.se.ws20.uebung4.control.Container;
import org.hbrs.se.ws20.uebung4.control.ContainerException;
import org.hbrs.se.ws20.uebung4.control.UserStory;

import org.hbrs.se.ws20.uebung4.persistence.PersistenceException;
import org.hbrs.se.ws20.uebung4.persistence.PersistenceStrategyStream;
import org.hbrs.se.ws20.uebung4.view.MemberView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;


public class ControlTest {
    private UserStory userStory1 = null;
    private UserStory userStory2 = null;
    private UserStory userStory3 = null;
    private Container container = null;
    private Container container2 = null;
    private ByteArrayOutputStream os = new ByteArrayOutputStream();
    private PrintStream capture = System.out;

    @BeforeEach
    void setup(){
        userStory1 = new UserStory( 1,  "test1", 1,  1,  2, 2 );
        userStory2 = new UserStory( 2,  "test1", 1,  1,  2, 2 );
        userStory3 = new UserStory( 3,  "test1", 1,  1,  2, 2 );
        container = Container.getInstance();
        container2 = Container.getInstance();
        //container2 = Container.getInstance();
        System.setOut(new PrintStream(os));

    }
    @AfterEach
    void teardown(){
        userStory1 = null;
        userStory2 = null;
        userStory3 = null;
        //container = null; //brauchen wir eigentlich nicht mehr oder?
        Container.reSetInstance(); //Cointainer löschen
        System.setOut(capture);
    }
    @Test
    void testAddMember() throws ContainerException {
        assertEquals(0, container.size(), " size (0) funktioniert nicht korrekt");

        try {
            container.addUserstory(userStory1);
        } catch (ContainerException e) {
            e.printStackTrace();
        }
        assertEquals(1, container.size(), "addMember Liste hinzufügen oder size funktioniert nicht korrekt");
        container.addUserstory(userStory2);
        container.addUserstory(userStory3);
        assertEquals(3, container.size(), "addMember Liste hinzufügen oder size funktioniert nicht korrekt");
        assertThrows(ContainerException.class, () -> container.addUserstory(userStory1), "Exception in AddMember funktioniert nicht");
        assertEquals(3, container.size(), " size (3) nach Doppel hin zu fügen funktioniert nicht korrekt");
    }
    @Test
    void testDeleteMember() throws ContainerException {
        assertEquals(0, container.size(), "size = 0 Fehler");
        container.addUserstory(userStory2);
        container.addUserstory(userStory3);
        assertEquals(2, container.size(), "size = 2 Fehler");
        container.deleteUserStory(2);
        assertEquals("Member ist nicht vorhanden", container.deleteUserStory(1), "delete nicht vorhandenen Member funktioniert nicht korrekt"); //Eigentlicher Test

        assertEquals(1, container.size(), "size = 2 Fehler");
    }
    @Test
    void testDump() throws ContainerException {
        //PrintStream capture = new PrintStream(os);

        container.addUserstory(userStory2);
        container.addUserstory(userStory3);
        MemberView.dump(container.getCurrentList());

        //System.out.print(capture.toString());
        assertEquals("Member (ID = 2)\nMember (ID = 3)\n", os.toString(), "Dump funktioniert nicht korrekt");
        //assertEquals(capture, container.dump(), "deleteMember funktioniert nicht korrekt");
    }
    //test
    @Test
    void testSingleton(){
        assertSame( container, container2);
    }
//    @Test
//    void testStore() throws PersistenceException, EOFException {
//        try {
//            container.setPss(new PersistenceStrategyStream<Member>());
//            container.addMember(member1);
//            container.addMember(member2);
//            container.addMember(member3);
//
//            container.store();
//            //store ausgeben
//            Container.reSetInstance();
//            container = null;
//            Container container2 = Container.getInstance();
//            container2.load();
//            //assertEquals(2,container.size(),"provozierter Fehler");
//            assertEquals(3, container2.size(), "Save and load funktioniert");
//
//        } catch (IOException | ContainerException e) {
//            e.printStackTrace();
//        }
//    }
    @Test
    void testStoreAndLoad() {
        try {
            container.setPss(new PersistenceStrategyStream<UserStory>());
            container.addUserstory(userStory1);
            container.addUserstory(userStory2);
            container.addUserstory(userStory3);
            assertEquals(3, container.size(), "Size() sollte hier 3 sein!");
            container.store();

            container.deleteUserStory(1);
            assertEquals(2 , container.size() );

            container.load(false);
            assertEquals( 3 , container.size() );

        } catch (ContainerException | PersistenceException | IOException e) {
            System.out.println("Message: " + e.getMessage());
        }
    }
}
