package simcity;

import Menu.ButtonSet;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;
import Utils.SettingRes;
import javafx.scene.layout.Pane;
public class MapCanvas extends Canvas
{
    private static MapCanvas mapcanvasIns;
    private TileMap map;
    private Pane root;
    private double polyX[] = new double[4];
    private double polyY[] = new double[4];
    private boolean drawing;
    private double scrollSpeedX;
    private double scrollSpeedY;
    private boolean shifting;
    private short firstPosX;
    private short firstPosY;
    private int fark1;
    private int fark2;
    public int rangeX;
    public int rangeY;
    public static double value1;
    public static double value2;
    public static byte zoom;
    private double mouseX;
    private double mouseY;
    private double partitionX; 
    private double partitionY;
    private double partitionHalfX;
    private double partitionHalfY;
    public double offsetX1 = 0;
    public double offsetX2 = 0;
    public double offsetY1 = 0;
    public double offsetY2 = 0;
    public double shiftX;
    public double shiftY;
    
    private final GraphicsContext gc;
    
    private enum Directions
    {
        NORTH , NORTHEAST , EAST , SOUTHEAST , SOUTH , SOUTHWEST , WEST , NORTHWEST
    }
    Directions direction;
    
    private enum Notification
    {
        ZOOM , ZOOM_SHIFT , SHIFT , NONE , ERASE , DRAW
    }
    Notification notified;
    private enum Type
    {
        ROAD , STRUCTURE
    }
    Type type;

    
    public static MapCanvas getInstance(ButtonSet set , Pane root)
    {
        if(mapcanvasIns == null)
        {
            mapcanvasIns = new MapCanvas(set , root);
        }
        return mapcanvasIns;
    }
    private MapCanvas(ButtonSet set , Pane root)
    {
        //Sets Canvas Resolution
        super(SettingRes.screenWidth, SettingRes.screenHeigth);
        this.root = root;
        
        
        //Scales canvas to game's window resolution
        canvasScale(Screen.getPrimary().getBounds().getWidth() / SettingRes.screenWidth, Screen.getPrimary().getBounds().getHeight() / SettingRes.screenHeigth);
        
        zoom = 3;
        partitionX = super.getWidth() / zoom;
        partitionY = super.getHeight() / zoom;
        partitionHalfX = partitionX / 2;
        partitionHalfY = partitionY / 2;
        
        //Gets scroll speed from settings
        scrollSpeedX = scrollSpeedY = SettingRes.getInt("Video_Settings", "SCROLL_SPEED");
        scrollSpeedX *= 1.6;
        scrollSpeedY *= 0.9;
        
        //Creating map and sets cordinates to its half
        map = TileMap.getInstance(set);
        rangeX = (short)(map.length / 2 - zoom);
        rangeY = (short)(map.length / 2 - zoom);
        
        //Initialize map data
        map.updateScreen(zoom, rangeX, rangeY);

        //Gets GraphicsContext in order to write to canvas
        gc = this.getGraphicsContext2D();
        
        //Adds EventsHandlers
        this.addEventHandler(MouseEvent.MOUSE_PRESSED, onMousePressed);
        this.addEventHandler(MouseEvent.MOUSE_RELEASED, onMouseReleased);
        root.addEventFilter(MouseEvent.MOUSE_MOVED, onMouseMoved);
        this.addEventHandler(MouseEvent.MOUSE_DRAGGED , onMouseDragged);
        this.addEventHandler(ScrollEvent.SCROLL, onScroll);
    }
    /*
     *  Game Logic Loop
    */
    public void update()
    {
        
        if(drawing)
        {
            getPosition();
            if(value1 != firstPosX || value2 != firstPosY)
            {
                fark1 = (int)(value1 - firstPosX);
                fark2 = (int)(value2 - firstPosY);
                if(fark2 >= 0 )
                {
                    if(fark1 >= 0)
                    {
                        value1 = firstPosX;
                        value2 = firstPosY + fark2;
                    }
                    else
                    {
                        value1 = firstPosX + fark1;
                        value2 = firstPosY + fark2;
                    }
                }
                else if(fark1 < 0)
                {
                    value2 = firstPosY;
                    value1 = firstPosX + fark1;
                }
                else
                {
                    value1 = firstPosX;
                    value2 = firstPosY;
                }
                fark1 = Math.abs(fark1);
                fark2 = Math.abs(fark2);
            }
            arrangePoly();
        }
        
        if(shifting)
        {
            switch(direction)
            {
                case NORTH:
                {
                    offsetX1 -= scrollSpeedX ; offsetY1 -= scrollSpeedY;
                    offsetX2 += scrollSpeedX ; offsetY2 += scrollSpeedY;
                    shiftY += scrollSpeedY;
                    break;
                }
                case SOUTH:
                {
                    offsetX1 += scrollSpeedX ; offsetY1 += scrollSpeedY;
                    offsetX2 -= scrollSpeedX ; offsetY2 -= scrollSpeedY;
                    shiftY -= scrollSpeedY;
                    break;
                }
                case WEST:
                {
                    offsetX1 += scrollSpeedX ; offsetY1 += scrollSpeedY;
                    offsetX2 += scrollSpeedX ; offsetY2 += scrollSpeedY;
                    shiftX += scrollSpeedX;
                    break;
                }
                case EAST:
                {
                    offsetX1 -= scrollSpeedX ; offsetY1 -= scrollSpeedY;
                    offsetX2 -= scrollSpeedX ; offsetY2 -= scrollSpeedY;
                    shiftX -= scrollSpeedX;
                    break;
                }
                case NORTHWEST:
                {
                    offsetX1 += scrollSpeedX ; offsetY1 += scrollSpeedY;
                    shiftX += scrollSpeedX ; shiftY += scrollSpeedY; 
                    break;
                }
                case SOUTHEAST:
                {
                    offsetX2 -= scrollSpeedX ; offsetY2 -= scrollSpeedY;
                    shiftX -= scrollSpeedX ;  shiftY -= scrollSpeedY;
                    break;
                }
                case NORTHEAST:
                {
                    offsetX1 -= scrollSpeedX ; offsetY1 -= scrollSpeedY;
                    shiftX -= scrollSpeedX ; shiftY += scrollSpeedY;
                    break;
                }
                case SOUTHWEST:
                {
                    offsetX1 += scrollSpeedX ; offsetY1 += scrollSpeedY;
                    shiftX += scrollSpeedX; shiftY -= scrollSpeedY;
                }                        
            }
            if(offsetX1 > partitionX || offsetX1 < -1 * partitionX  ){offsetX1 = 0; offsetY1 = 0;}
            if(offsetX2 > partitionX || offsetX2 < -1 * partitionX  ){offsetX2 = 0; offsetY2 = 0;}
            if(shiftY > partitionY) { shiftY = 0; firstPosX++; firstPosY++; rangeX--; rangeY--; map.updateScreen(zoom, rangeX, rangeY);}
            else if(shiftY < -partitionY){ shiftY = 0; firstPosX--; firstPosY--;rangeX++; rangeY++; map.updateScreen(zoom, rangeX, rangeY); }
            if(shiftX > partitionX) { shiftX = 0;firstPosX++; firstPosY--; rangeX--; rangeY++; map.updateScreen(zoom, rangeX, rangeY); }
            else if(shiftX < -partitionX) { shiftX = 0; firstPosX--; firstPosY++; rangeX++; rangeY--; map.updateScreen(zoom, rangeX, rangeY); }

        }     
    }
    public void draw()
    {
        //Clears Screen to write new data
        gc.clearRect(0, 0, getWidth(), getHeight());
        
        //Writes new data to canvas
        gc.getPixelWriter().setPixels((int)(shiftX - partitionX) ,(int)(shiftY - partitionY) , 
              map.data.sizedWidth , map.data.sizedHeigth  , Graphics.Image.format, map.data.sizedBuff.array(), 0 , map.data.sizedWidth * 4 );
             //gc.getPixelWriter().setPixels(-1280,-720,3200,1080*5/3,map.dada1,map.imgda.sizedBuff.array() , 0 ,(int)(getWidth() * 20  / 3 )); 
             
        //Isometric lines algorithm
        /*for(int i = 0 ; i <= zoom   ; i++)
        {
            gc.strokeLine(partitionX * i + offsetX2, 0, 0, partitionY * i + offsetY2);
            gc.strokeLine(partitionX * (zoom -  i) + offsetX1, 0, getWidth(), partitionY * i - offsetY1);
            if(i != 0)gc.strokeLine(partitionX * i + offsetX2, getHeight(), getWidth(), partitionY * i + offsetY2);  
            if(i != zoom)gc.strokeLine(partitionX * i + offsetX1, getHeight(), 0, partitionY  * (zoom -  i) - offsetY1);         
        }*/
        
        //Draws rectangle if multiple tiles are selected
        gc.setGlobalAlpha(0.5);
        if(drawing)gc.fillPolygon(polyX , polyY , 4);
        gc.setGlobalAlpha(1);
        
        if(notified == Notification.ZOOM_SHIFT)notified = Notification.SHIFT;
        if(notified != Notification.SHIFT) notified = Notification.NONE; 
    }
    private void edgeDetect()
    {
        shifting = true;
        if(mouseX == 0 )
        {
           direction = Directions.WEST;
           if(mouseY == 0)direction = Directions.NORTHWEST;
           if(mouseY >= Screen.getPrimary().getBounds().getHeight() -3)direction = Directions.SOUTHWEST;
        }
        else if(mouseX >= Screen.getPrimary().getBounds().getWidth() -1)
        {
            direction = Directions.EAST;
            if(mouseY == 0)direction = Directions.NORTHEAST;
            if(mouseY >= Screen.getPrimary().getBounds().getHeight()  -3)direction = Directions.SOUTHEAST;
        }
        else if(mouseY == 0) direction = Directions.NORTH;
        else if (mouseY >=  Screen.getPrimary().getBounds().getHeight()  -3) direction = Directions.SOUTH;
        else shifting = false;
    }
     /*
     * getPosition uses mouse cordinates(mouseX , mouseY), screen shift values(shiftX , shiftY) to calculate  screen positions
     * value1 , value2 keeps screen positions
     * value1's top left points value is 0 so value2's is
    */
    private void getPosition()
    {         
        //pointX and pointY keeps in which squre mouse at
        int pointX = 0;
        int pointY = 0;
        
        //if screen is shifted
        if(shiftY > 0 && mouseY <= shiftY) value2 =  (- partitionY + shiftY - mouseY) / partitionY; 
        else value2 = ((mouseY - shiftY) / partitionY) ;
        if(shiftX > 0 && mouseX <= shiftX) value1 = (- partitionX + shiftX - mouseX) / partitionX; 
      
        else value1 = ((mouseX - shiftX)/ partitionX) ;
        pointX += Math.floor(value1);
        pointY += Math.floor(value2);
        value1 = Math.abs(value1 % 1);
        value2 = Math.abs(value2 % 1);
        if(value1 + value2 < 1)
        {
            if(value1 > value2)
            {
                value1 = pointX + pointY;
                value2 = pointY - pointX;
            }
            else
            {
                value1 = pointX + pointY;
                value2 = pointY - pointX + 1;
            }
        }
        else
        {   
            if(value1 > value2)
            {
                value1 = pointX + pointY + 1;
                value2 = pointY - pointX;
            }
            else
            {
                value1 = pointX + pointY + 1;
                value2 = pointY - pointX + 1;
            }
        }
    }
    private void arrangePoly()
    {
        polyX[0] = partitionHalfX * value1 - partitionHalfX * value2 + shiftX;
        polyX[1] = polyX[0] + partitionHalfX * (fark1 + 1);
        polyX[2] = polyX[0] + partitionHalfX * (fark1 + fark2 + 2);
        polyX[3] = polyX[0] + partitionHalfX * (fark2 + 1);
        
        polyY[0] = partitionHalfY * value1 + partitionHalfY * value2 + shiftY;
        polyY[1] = polyY[0] + partitionHalfY * (fark1 + 1);
        polyY[2] = polyY[0] + partitionHalfY * (fark1 - fark2);
        polyY[3] = polyY[0] - partitionHalfY * (fark2 + 1); 
    }
    
    public void canvasScale(double ratioX , double ratioY)
    { 
        Scale scale = new Scale(ratioX, ratioY, 0, 0);
        super.getTransforms().add(scale);
    }
    
    EventHandler onMousePressed = new EventHandler<MouseEvent>()
    {
        @Override
        public void handle(MouseEvent e)
        {
            //Keeps mouse locations
            mouseX = e.getX();
            mouseY = e.getY();
            
            //Sets map cordinates by reading mouse location
            getPosition();
            
            //Keeps first clicked cordinates according to screen
            firstPosX = (short)value1;
            firstPosY = (short)value2;
            
            //Notifies game that something needs to be drawed
            drawing = true;
            
            //Sets ranges to appropriate map cordinates
            rangeX += (short)(firstPosX);
            rangeY += (short)(firstPosY);
        }
    };
    
    EventHandler onMouseReleased = new EventHandler<MouseEvent>()
    {
        @Override
        public void handle(MouseEvent e)
        {            
            if(drawing)
            {
                //Sends build information to map
               
                map.set(rangeX +2 , rangeY  , (short)fark1 , (short)fark2);
                
                rangeX -= (short)(firstPosX);
                rangeY -= (short)(firstPosY);
                // SIKINTI
                map.updateScreen(zoom, rangeX, rangeY);
                fark1 = fark2 = 0;
            }
            drawing = false;
        }
    };
    
    EventHandler onMouseMoved = new EventHandler<MouseEvent>()
    {
        @Override
        public void handle(MouseEvent e)
        {
            mouseX = e.getX();
            mouseY = e.getY();
            edgeDetect();     
        }
    };
    
    EventHandler onMouseDragged = new EventHandler<MouseEvent>()
    {
        @Override
        public void handle(MouseEvent e)
        {

            mouseX = e.getX();
            mouseY = e.getY();
           // System.out.println("First PosX: " + rangeX +2 + "   FirstPosY: " + rangeY + "    LastPosX: " + value1 + "  LastPosY:"  + value2);
            edgeDetect();  
        }
    };
    
    EventHandler onScroll = new EventHandler<ScrollEvent>()
    {
        @Override
        public void handle(ScrollEvent e)
        {
            if(e.getDeltaY() <0)
            {
                 zoom++;
            }
            else
            {
                zoom--;
            }
            map.updateScreen(zoom , rangeX , rangeY );
            partitionX = getWidth() / zoom;
            partitionY = getHeight() /zoom;
            partitionHalfX = partitionX / 2;
            partitionHalfY = partitionY / 2;
        } 
    };
}
    
    
   
    
    
    
   
    
