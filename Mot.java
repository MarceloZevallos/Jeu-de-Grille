/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gg.sim.mzevallos.projet2;

import java.awt.Point;
import java.util.Locale;

/**
 *
 * @author X_Pet
 */
public class Mot {
    private String strMot;
    private EnumDirections direction;
    private Point position = new Point(0,0); // default
    
    Mot(String strMot){
        this.strMot = strMot;
    }
    
    public String toString(){
        String message = String.format(Locale.US, "%-15S (%2s,%2s)      %-20s", strMot, position.x,
                    position.y, direction.getDescription());
        return message;
    }

    public String getStrMot() {
        return strMot;
    }

    public EnumDirections getDirection() {
        return direction;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(int x, int y) {
        position = new Point(x,y);
    }

    public void setDirection(EnumDirections direction) {
        this.direction = direction;
    }
    
    
    
}// Mot
