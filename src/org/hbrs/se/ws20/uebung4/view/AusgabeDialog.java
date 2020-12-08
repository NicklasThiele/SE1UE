package org.hbrs.se.ws20.uebung4.view;

import org.hbrs.se.ws20.uebung4.model.Container;

public class AusgabeDialog {
    Container container = Container.getInstance();
    public void dump(){
        container.getCurrentList().stream()
                .forEach(userStory -> System.out.println(userStory.toString()) );
    }
    public void dumpParameter(Container container, int dAufwand){
        container.getCurrentList().stream()
                .filter(userStory -> userStory.getAufwand() > dAufwand)
                .forEach(userStory -> System.out.println(userStory.toString()));
    }
}

