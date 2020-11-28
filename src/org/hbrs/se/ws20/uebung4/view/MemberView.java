package org.hbrs.se.ws20.uebung4.view;

import org.hbrs.se.ws20.uebung4.control.UserStory;

import java.util.List;

public class MemberView {
    public static void dump(List<UserStory> liste){
        for(UserStory x : liste){
            System.out.print(x.toString());
        }
    }
}
