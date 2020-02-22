package scene;

import Utils.SettingRes;
import events.SceneChangeEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Intro 
{
    
    public Scene scene;
    public Intro()
    {
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        scene = new Scene(box ,SettingRes.screenWidth, SettingRes.screenHeigth );
        scene.setFill(Color.CORAL);
      
        ImageView img = new ImageView(new Image("file:Resources/Sprites/akdeniz.png"));
        
        FadeTransition ft = new FadeTransition(Duration.millis(2000), img);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
        ImageView img1 = new ImageView(new Image("file:Resources/Sprites/cse.png"));
        img1.setOpacity(0);
        ft.setOnFinished((event) ->
        {
           
            FadeTransition ft1 = new FadeTransition(Duration.millis(2000), img1);
            ft1.setFromValue(0);
            ft1.setToValue(1);
            ft1.play();
            ft1.setOnFinished((e) ->
            {
                try
                {
                    Thread.sleep(1000);
                } catch (InterruptedException ex)
                {
                    Logger.getLogger(Intro.class.getName()).log(Level.SEVERE, null, ex);
                }
                box.fireEvent(new SceneChangeEvent(SceneChangeEvent.MAIN_MENU));
            });
        
        });
        
        box.getChildren().addAll(img , img1);
     
        
    }
}

