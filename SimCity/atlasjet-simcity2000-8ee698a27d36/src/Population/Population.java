/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Population;
import Structures.*;
/**
 *
 * @author anormal
 */
public class Population {
    private static int pop;

    public static int getPop() {
        return pop;
    }
    
    
    
    static int[] building = new int[]{
        Residental.getResnum(),
        Commercial.getComnum(),
        Industrial.getIndnum(),
        Hospital.getHosnum(),
        PoliceDepartment.getPolnum(),
        Education.getEdu()/2
    };
 
    
    public static double calcPoprate(int p){
        building = new int[] {5,3,2,0,0,0};
        pop = p;
        for(int i:building)
            System.out.println(i);
        double poprate;
        double buildrate = Math.min(Math.min(building[0]/5, building[1]/3),Math.min(building[0]/5,building[2]/2))*3;
        System.out.println(buildrate);
        poprate = buildrate * 5  - (calcAll(building[3] + building[5],50000) * 2) - (calcAll(building[4]+ building[5],5000) * 3);
        
        return Math.round(poprate*100);
    }
    
    private static int calcAll(int p,int rate){
        int pol = pop/rate - p;
        if (pol < 0)
            pol = 0;
        else if(pol > 100)
            pol = 100;
        return pol;
    }
    
   
    
    
}
