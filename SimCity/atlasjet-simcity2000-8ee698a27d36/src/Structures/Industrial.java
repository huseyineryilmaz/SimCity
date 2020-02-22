package Structures;

public class Industrial extends Structure
{
    
    private static int indnum;
    private static int value = 1000; 
    public Industrial(int x, int y, Field[][] map)
    {
        super(x, y, map);
        indnum++;
        super.killEco(value);
    }

    public static int getIndnum() {
        return indnum;
    }
    
    
}
