package scene;

import Menu.ButtonSet;
import Utils.SettingRes;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import simcity.MapCanvas;
import simcity.MenuPane;

public class GameScene extends AnimationTimer
{
    public Scene scene;
    Text tx1,tx2,tx3,tx4,tx5,tx6,tx7,tx8;
    private Pane root;
    private MapCanvas map;
    private long time;
    public static MenuPane p;
    public GameScene()
    {
        root = new Pane();
        scene = new Scene(root, SettingRes.screenWidth, SettingRes.screenHeigth);
        scene.getStylesheets().add("file:Resources/Style/StyleButton.css");
        
        p = new MenuPane(root);
        
        tx1 = new Text();
        tx2 = new Text();
        tx3 = new Text();
        tx4 = new Text();
        tx5 = new Text();
        tx6 = new Text();
        tx7 = new Text();
        tx8 = new Text();

        VBox box = new VBox(tx1,tx2,tx3,tx4,tx5,tx6,tx7,tx8);
        
        
    
 
        map = MapCanvas.getInstance(new ButtonSet(root) , root);
        
        root.getChildren().addAll(map);
        map.toBack();
        root.addEventFilter(MouseEvent.ANY, e ->{
            tx1.setText("Mouse xPos:" +Double.toString(e.getX())); 
            tx2.setText("Mouse yPos:" +Double.toString(e.getY())); 
            tx3.setText("Algorithm 1:" +Double.toString(MapCanvas.value1));
            tx4.setText("Algorithm 2:" +Double.toString(MapCanvas.value2));
          
                        tx6.setText("Shift Y:" + map.shiftY);  
                                                tx7.setText("Point Y:" + map.rangeX);        
                        tx8.setText("Point Y:" + map.rangeY);        

           


        });
      
        
        this.start();
        
    }

    @Override
    public void handle(long now)
    {
        tx5.setText(String.valueOf(Math.round(1.0 / ((now - time) / 1000000000.0))));
        time = now;
        
        
        map.update();
      
        draw();
    }
    public void draw()
    {
        map.draw();
    }
  
    
 
}
