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
public class PoliceDepartment extends Structure{
    private static int polnum;
    //image = new Image("fire.jpg");
    
    
    public PoliceDepartment(int corx,int cory,Field[][] map){
        super(corx,cory,map);
        image = AtlasMap.getImage("Police.png");
        polnum++;
    
    }

    public static int getPolnum() {
        return polnum;
    }
    
    
}
