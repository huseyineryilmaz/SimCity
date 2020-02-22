package Structures;

import Graphics.AtlasMap;

public class Residental extends MainBuildings
{
    private static int resnum;
    private static int value = 800;
    public Residental(int x, int y, Field[][] map)
    {
        super(x, y, map);
        image = AtlasMap.getImage("bd1.png");
        resnum++;
        super.killEco(value);
    }

    public static int getResnum() {
        return resnum;
    }
    
    
}
