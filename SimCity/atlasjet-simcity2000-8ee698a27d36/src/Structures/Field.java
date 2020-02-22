package Structures;

import Graphics.Image;


public abstract class Field {
    protected int crime;
    protected int polution;
    protected Image image;
    protected Image pipe;

    int x;
    int y;
    final Field[][] map;
 
    protected Field(int x , int y , Field[][] map)
    {
        this.x = x;
        this.y = y;
        this.map = map;
    }
    public void put(Image target , int x , int y)
    {
        image.put(target, x, y);
    }
    public void putPipe(Image target , int x , int y)
    {
        if(pipe != null)pipe.put(target, x, y);
    }
}
