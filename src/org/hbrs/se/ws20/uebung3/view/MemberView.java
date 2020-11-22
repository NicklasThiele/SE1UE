package org.hbrs.se.ws20.uebung3.view;

import org.hbrs.se.ws20.uebung3.control.Container;
import org.hbrs.se.ws20.uebung3.control.Member;

import java.util.List;

public class MemberView {
    public static void dump(List<Member> liste){
        for(Member x : liste){
            System.out.print(x.toString());
        }
    }
}
