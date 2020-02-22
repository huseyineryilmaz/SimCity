package Structures;

import Graphics.AtlasMap;

public class Soil extends Field
{
    
    public Soil(int x, int y , Field[][] map)
    {
        super(x, y , map);
        image = AtlasMap.getImage("tile2.png");
    }
    
}
