package org.hbrs.se.ws20.uebung4.view;



import org.hbrs.se.ws20.uebung4.client.EingabeDialog;
import org.hbrs.se.ws20.uebung4.control.Container;
import org.hbrs.se.ws20.uebung4.control.UserStory;
import org.hbrs.se.ws20.uebung4.persistence.PersistenceStrategy;
import org.hbrs.se.ws20.uebung4.persistence.PersistenceStrategyStream;

public class Main {
    public static void main (String[] args) {
        Container container = Container.getInstance();
        PersistenceStrategy<UserStory> usPss = new PersistenceStrategyStream<UserStory>();
        container.setPss(usPss);
        EingabeDialog ed = new EingabeDialog();
        ed.beginnEingabe();
    }
}