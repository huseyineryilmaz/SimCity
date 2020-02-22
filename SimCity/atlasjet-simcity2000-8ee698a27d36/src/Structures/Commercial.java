package Structures;

public class Commercial extends MainBuildings
{ 
    private static int comnum;
    private int value = 1000;
    public Commercial(int x, int y, Field[][] map)
    {
        super(x, y, map);
        comnum++;
        super.killEco(value);
        
    }

    public static int getComnum() {
        return comnum;
    }

    
    
    
}
