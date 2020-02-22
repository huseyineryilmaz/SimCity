package Structures;


import Graphics.AtlasMap;

public class Road extends Field{

    
    
    public enum Directions
    {
        NONE,SOUTH_NORTH,WEST_EAST,NORTH_EAST,SOUTH_EAST, NORTH_WEST,SOUTH_WEST, CONN3
    }Directions directions;
   
    public Road(int x ,int y,Field[][] map)
    {
        super(x, y , map);
        this.x = y;
        this.y = y;
        directions = Directions.CONN3;
        switch(directions){
        
            case SOUTH_NORTH:
                image = AtlasMap.getImage("");
                break;
                
            case WEST_EAST:
                image = AtlasMap.getImage("");
                break;
                
            case NORTH_EAST:
                image = AtlasMap.getImage("");
                break;
                
            case SOUTH_EAST:
                image = AtlasMap.getImage("");
                break;
                
            case NORTH_WEST:
                image = AtlasMap.getImage("");
                break;
            
            case SOUTH_WEST:
                image = AtlasMap.getImage("");
                break;
            
            case CONN3:
                image = AtlasMap.getImage("tile.png");
                break;
            
        }
        
    }
}
