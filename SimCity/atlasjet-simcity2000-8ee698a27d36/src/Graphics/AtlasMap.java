package Graphics;

import Utils.AtlasReader;
import java.util.HashMap;

public class AtlasMap
{
    private static HashMap<String ,Image> map;
    public static void initialize()
    {
        map = AtlasReader.getImages(); 
    }
    public static void scaleAll(int width)
    {
          map.values().forEach((a) -> a.scale(width) );
    }
    public static Image getImage(String name)
    {
        return map.get(name);
    }
}
