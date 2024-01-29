/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gg.sim.mzevallos.projet2;

/**
 *
 * @author X_Pet
 */
public enum EnumDirections {
    NORD("Nord"),
    NORDEST("Nord Est"),
    NORDOUEST("Nord Ouest"),
    SUD("Sud"),
    SUDEST("Sud Est"),
    SUDOUEST("Sud Ouest"),
    EST("Est"),
    OUEST("Ouest");
    
    private String description = null;
	
    EnumDirections(String desc) {
	this.description = desc;
    }
	
    public String getDescription() {
        return this.description;
    }
    
    public static EnumDirections getEnum(String s) {
//    	System.out.println("input: " + s);
    	for (EnumDirections e : EnumDirections.values()) {
//    	   	System.out.println("e: " + e + "des: " + e.description);
    		if (e.description.equals(s)) {
    			return e;
    		}
    	}
    	return null;
    }
}
