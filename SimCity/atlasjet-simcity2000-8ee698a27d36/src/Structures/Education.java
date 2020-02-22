/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Structures;

/**
 *
 * @author anormal
 */
public class Education extends Structure{
    private static int highnum;
    private static int schnum;
    private static int uninum;

    private static int value = 1000;
    
    public static int getHighnum() {
        return highnum;
    }

    public static int getSchnum() {
        return schnum;
    }

    public static int getUninum() {
        return uninum;
    }
    
    
    
    public Education(int corx,int cory,Field[][] map ,int cho){
        super(corx, cory, map);
        super.killEco(value);
        switch(cho){
            case 0:
                schnum++;
                break;
            case 1:
                highnum++;
                break;
            case 2:
                uninum++;
                break;
        }
        //image = new Image("school-"+ cho + ".jpg");
        
        highnum++;
        schnum++;
        uninum++;
    
    }

    public static int getEdu() {
        return uninum * 3 + highnum * 2 + schnum * 1;
    }

   
    
    
    
}
