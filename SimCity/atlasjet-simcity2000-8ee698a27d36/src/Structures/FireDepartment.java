/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Structures;

import Graphics.AtlasMap;

/**
 *
 * @author anormal
 */
public class FireDepartment extends Structure{
    private static int firenum;
    private static int value = 1000;
    
    //image = new Image("fire.jpg");
    
    
    public FireDepartment(int corx,int cory,Field[][] map){
        super(corx,cory,map);
        super.killEco(value);
        image = AtlasMap.getImage("fire_station.png");
        firenum++;
    }

    public static int getFirenum() {
        return firenum;
    }
    
    
}
