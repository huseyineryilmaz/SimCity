package Structures;

import Graphics.AtlasMap;
import Graphics.Image;

    public class Wire extends Structure{
    
    public enum Type{
        SOUTH_NORTH, EAST_WEST
    }
    
    public Road road;
    
    public Wire(Type type,int x,int y , Field[][] map)
    {
        super(x, y, map);
        if(road != null)
        {
            this.x = x;
            this.y = y;
            switch(type)
            {
                case SOUTH_NORTH:
                {
                    image = AtlasMap.getImage("");
                    break;
                }
                case EAST_WEST:
                {
                    image = AtlasMap.getImage("tile.png");
                    break;
                }
            }
            if (!powered)
            {
                electric = AtlasMap.getImage("");
            }
            else electric = AtlasMap.getImage("null");
        }
        image = AtlasMap.getImage("Electricity.png");
    }
    
    @Override
    public void put(Image target,int x , int y){
        if(road != null)road.put(target, x, y);
        image.put(target, x, y);
        electric.put(target, x, y);
    }
    
}
