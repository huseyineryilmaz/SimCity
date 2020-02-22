package simcity;

import scene.GameScene;
import scene.MenuCity;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Utils.SettingRes;
import com.sun.javafx.scene.SceneEventDispatcher;
import events.SceneChangeEvent;
import javafx.scene.input.KeyCombination;
import scene.Intro;

public class Main extends Application
{
    private GameScene gameScene; 
    private MenuCity menuScene;
    private Stage stage;
    @Override
    public void start(Stage primaryStage)
    { 
        stage = primaryStage;
        
        Intro intro = new Intro();
        //menuScene = new MenuCity();
        Pane root = new Pane();
        Scene scene = new Scene(root,SettingRes.screenWidth,SettingRes.screenHeigth);
        primaryStage.setScene(intro.scene);
        primaryStage.setFullScreen(SettingRes.getInt("Video_Settings", "FullScreen") == 1);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.setTitle("Simcity DEV");
        
        primaryStage.show();
        primaryStage.addEventHandler(SceneChangeEvent.MAIN_MENU, (SceneChangeEvent event) ->
        {
            menuScene = new MenuCity();
            stage.setScene(menuScene.scene);
            stage.setFullScreen(true);
        });
        primaryStage.addEventHandler(SceneChangeEvent.GAME_SCENE, (SceneChangeEvent event) ->
        {
            gameScene = new GameScene();
            stage.setScene(gameScene.scene);
            stage.setFullScreen(true);
        });
    }
    public static void main(String[] args)
    {
        SettingRes.initSettings();
        launch(args);
    }
    
}
