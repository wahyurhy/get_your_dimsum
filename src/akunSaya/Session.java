/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package akunSaya;

import java.util.ArrayList;

/**
 *
 * @author hp
 */
public class Session {
    private static ArrayList<SESSIONCLASS> SESSION = new ArrayList<SESSIONCLASS>();

    public static void setSession(String name, Object value) {
        boolean found = false;
        for (int i = 0; i < SESSION.size(); i++) {
            if (name.toLowerCase().equals(SESSION.get(i).name.toLowerCase())) {
                SESSIONCLASS s = SESSION.get(i);
                s.value = value;
                found = true;
                SESSION.set(i, s);
            }
        }
        if (!found) {
            SESSIONCLASS session = new SESSIONCLASS();
            session.name = name;
            session.value = value;
            SESSION.add(session);
        }
    }

    public static Object getSession(String name) {
        boolean found = false;
        for (int i = 0; i < SESSION.size(); i++) {
            if (name.toLowerCase().equals(SESSION.get(i).name.toLowerCase())) {
                return SESSION.get(i).value;
            }
        }
        return null;
    }

    public static void clearSession(String name) {
        boolean found = false;
        for (int i = 0; i < SESSION.size(); i++) {
            if (name.toLowerCase().equals(SESSION.get(i).name.toLowerCase())) {
                SESSION.remove(i);
            }
        }
    }
    
    public static boolean isSetSession(String name) {
        if(getSession(name) == null) return false;
        return true;
    }
}

class SESSIONCLASS {

    public String name;
    public Object value;
}
