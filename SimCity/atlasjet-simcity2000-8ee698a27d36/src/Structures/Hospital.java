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
public class Hospital extends Structure {
    private static int hosnum;
    private static int value = 1000;
    //image = new Image("fire.jpg");
    
    
    public Hospital(int corx,int cory,Field[][] map){
        
        super(corx,cory,map);
        image = AtlasMap.getImage("Hospital.png");
        hosnum++;
        super.killEco(value);
    
    }

    public static int getHosnum() {
        return hosnum;
    }
    
}
