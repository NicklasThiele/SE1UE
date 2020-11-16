package org.hbrs.se.ws20.uebung1.view;

public class ClientTest {
    private static Client client;
    //private Client Client = null;
    public ClientTest() {
        client = new Client();
    }
    public static String test(){
        //positivTest
        client.display(1);
        //NegativTest
        client.display(-1);
        client.display(11);
        client.display(0);
        return "Test abgeschlossen";
    }
    public static void main(String[] args) {
        ClientTest cs = new ClientTest();
        ClientTest.test();

    }
}
