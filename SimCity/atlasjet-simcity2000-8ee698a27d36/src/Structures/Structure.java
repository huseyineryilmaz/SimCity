package Structures;
import scene.GameScene.*;
import Graphics.AtlasMap;
import Graphics.Image;
import scene.GameScene;

public abstract class Structure extends Field{  
    public boolean powered;
    public boolean watered;
    public Image electric;
    protected int value;    
    
    protected Structure(int x , int y , Field[][] map)
    {
        super(x, y,map);
        checkElectrified();
        checkWatered();
    }
    
    public void killEco(int v){
        GameScene.p.eco.outEco(v);
        GameScene.p.money.setText(GameScene.p.eco.getMoney()+ "$");
    }
    
    public final boolean checkElectrified()
    {
        
        boolean iselect = false;
        
        if(!this.powered){
            
            if (x != 0 && map[x - 1][y] instanceof Structure)
                iselect = iselect || ((Structure)map[x - 1][y]).checkElectrified();
            if (y != 0 && map[x][y - 1] instanceof Structure)
                iselect = iselect || ((Structure)map[x][y - 1]).checkElectrified();
            if(x < map.length -1 && map[x +1][y] instanceof Structure)
                iselect = iselect || ((Structure)map[x +1][y]).checkElectrified();
            if(y < map.length -1 && map[x][y +1] instanceof Structure)
                iselect = iselect || ((Structure)map[x][y + 1]).checkElectrified();
        
        }
        
        else{
            return powered;
        }
        powered = iselect;
        //if(iselect) electrify(x,y,map);
        return iselect;
    }
    
    /*void electrify(int x, int y, Field[][] map) {
        ((Structure)map[x][y]).powered = true;
        
    }*/
    
    public final boolean checkWatered()
    {
        boolean iselect = false;
        if (x != 0 && map[x - 1][y] instanceof Structure)
            iselect = iselect || ((Structure)map[x - 1][y]).watered;
        if (y != 0 && map[x][y - 1] instanceof Structure)
            iselect = iselect || ((Structure)map[x][y - 1]).watered;
        if(x != map.length && map[x +1][y] instanceof Structure)
            iselect = iselect || ((Structure)map[x +1][y]).watered;
        if(y != map.length && map[x][y +1] instanceof Structure)
            iselect = iselect || ((Structure)map[x][y + 1]).watered;
        watered = iselect;
        return watered;
    }
    @Override
    public void putPipe(Image target , int x , int y)
    {
        if (pipe != null)
        {
            pipe.put(target, x, y);
        }
    }
    private void setPipe()
    {
        if(watered)
            pipe = AtlasMap.getImage("");
        
        else 
            pipe = AtlasMap.getImage("");
    }
    @Override
    public void put(Image target , int x , int y)
    {
        image.put(target, x, y);
        //if(powered == false)electric.put(target, x, y);    
    }
}
