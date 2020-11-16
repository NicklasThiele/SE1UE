package org.hbrs.se.ws20.uebung2.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.hbrs.se.ws20.uebung2.control.Container;
import org.hbrs.se.ws20.uebung2.control.ContainerException;
import org.hbrs.se.ws20.uebung2.control.MemberDef;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ControlTest<Member> {
    private MemberDef member1 = null;
    private MemberDef member2 = null;
    private MemberDef member3 = null;
    private Container container = null;
    private ByteArrayOutputStream os = new ByteArrayOutputStream();
    private PrintStream capture = System.out;

    @BeforeEach
    void setup(){
        member1 = new MemberDef(1);
        member2 = new MemberDef(2);
        member3 = new MemberDef(3);
        container = new Container();
        System.setOut(new PrintStream(os));

    }
    @AfterEach
    void teardown(){
        member1 = null;
        member2 = null;
        member3 = null;
        container = null;
        System.setOut(capture);
    }
    @Test
    void testAddMember() throws ContainerException {
        assertEquals(0, container.size(), " size (0) funktioniert nicht korrekt");
        container.addMember(member1);
        assertEquals(1, container.size(), "addMember Liste hinzufügen oder size funktioniert nicht korrekt");
        container.addMember(member2);
        container.addMember(member3);
        assertEquals(3, container.size(), "addMember Liste hinzufügen oder size funktioniert nicht korrekt");
        assertThrows(ContainerException.class, () -> container.addMember(member1), "Exception in AddMember funktioniert nicht");

    }
    @Test
    void testDeleteMember() throws ContainerException{
        assertEquals(0, container.size(), "size = 0 Fehler");
        container.addMember(member2);
        container.addMember(member3);
        assertEquals(2, container.size(), "size = 2 Fehler");
        container.deleteMember(2);
        assertEquals("Member ist nicht vorhanden", container.deleteMember(1), "delete nicht vorhandenen Member funktioniert nicht korrekt"); //Eigentlicher Test
    }
    @Test
    void testDump() throws ContainerException{
        //PrintStream capture = new PrintStream(os);

        container.addMember(member2);
        container.addMember(member3);
        container.dump();

        //System.out.print(capture.toString());
        assertEquals("Member (ID = 2)\nMember (ID = 3)\n", os.toString(), "testDump funktioniert nicht korrekt");
        //assertEquals(capture, container.dump(), "deleteMember funktioniert nicht korrekt");
    }

}
