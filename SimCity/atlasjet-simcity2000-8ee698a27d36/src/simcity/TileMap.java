package simcity;

import Graphics.AtlasMap;
import Graphics.Image;
import Menu.ButtonSet;
import Menu.ToggleButtonExtended;
import Structures.*;
import Utils.SettingRes;

public class TileMap
{
    
    private final ButtonSet set;
    public int length;
    public Image data;
    private final Field[][] map;
   
    private static TileMap tilemapIns = null;
    
    public static TileMap getInstance(ButtonSet set )
    {
        if(tilemapIns == null)tilemapIns = new TileMap(set);
        return tilemapIns;
    }
        private enum ButtonType
    {
        SOIL , ROAD
    }ButtonType buttonType;
    private TileMap(ButtonSet set)
    {        
        this.set = set;
        AtlasMap.initialize();
        map = new Field[512][512];
        length = map.length;
        data = new Image((int)Math.ceil(SettingRes.screenWidth * 5/ 3), (int)Math.ceil(SettingRes.screenHeigth * 5 / 3));
        for (int i = 0; i < map.length; i++)
        {
            for (int j = 0; j < map.length; j++)
            {
                map[i][j] = new Soil(i, j,map);
            }
        }
    }
    public void initialize()
    {
        
    }
    public void updateScreen(byte zoom , int locX , int locY )
    {
        int x = SettingRes.screenWidth / zoom;
        int y = SettingRes.screenHeigth / zoom;
        AtlasMap.scaleAll(x);
        int limit = 2;
        int i = 0;
        int j = 0;
        
       for (;i < (zoom * 2 + 4) /2 ;i++)
        {
            for (; j < limit ; j++)
            {
                map[locX + i][locY + j - i].put(data, (x / 2 + x * i) - (x / 2 *j) , j * y / 2 + y/2);
            }
            j = 0;
            limit += 2;
        }
        limit -= 2;
        for (int k = 0; k < (zoom * 2 + 4) /2 ; k++)
        {
            for (int l = 0; l < limit; l++)
            {
                map[locX + i][locY  - (zoom * 2 + 4) /2 + j + k  +1].put(data ,((zoom * 2 + 4) /2 ) * x - (l   * x / 2), (k +  1)* y + (l * y / 2));
                j++;
            }
            j-=limit;
            i++;
            
            limit -= 2;
        }
    }
    public void set(int x, int y , int rangeX , int rangeY)
    {     
         switch(set.getSelected())
         {
            case HOUSE:
            {
                map[x][y] = new Residental(x, y, map);
                
                break;
            }
            case ELECTRICITY:
            {
                break;
            }
            case POLICE:
            {
                map[x][y] = new PoliceDepartment(x, y, map);
                break;
            }
            case FIRE:
            {
                map[x][y] = new FireDepartment(x, y, map);
                break;
            }
            case HOSPITAL:
            {
                map[x][y] = new Hospital(x, y, map);
            }
        }
         
    }
}
