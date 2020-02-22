/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simcity;

import Economy.Economy;
import javafx.application.Platform;
import javafx.geometry.Pos;
import Utils.SettingRes;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class MenuPane {
    
    public StackPane spn;
    public HBox vo;
    public Label date, money;
    public Economy eco = new Economy(10000);
    public MenuPane(Pane root) {
        
        spn = new StackPane();
        spn.prefWidthProperty().bind(root.widthProperty());
        vo = new HBox();
        vo.setLayoutX((int)(SettingRes.screenWidth/2.2));
        vo.setLayoutY(10);
        //spn.setAlignment(vo, Pos.TOP_CENTER);
       // root.getChildren().add(spn);
        
        date = new Label("2017\t");
        money = new Label(eco.getMoney() + " $");
        date.setTextFill(Color.WHITE);
        money.setTextFill(Color.WHITE);
        vo.getChildren().addAll(date,money);
        
        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(root.widthProperty());
        root.getChildren().addAll(menuBar,vo);
        
        

        Menu fileMenu = new Menu("File");
        MenuItem newMenuItem = new MenuItem("New Game");
        MenuItem saveMenuItem = new MenuItem("Save");
        MenuItem selectMenuItem = new MenuItem("Do Something");
        MenuItem exitMenuItem = new MenuItem("Exit");
        MenuItem saveandexitMenuItem = new MenuItem("Save and Exit");

        exitMenuItem.setOnAction(actionEvent -> Platform.exit());
        saveandexitMenuItem.setOnAction(e -> Platform.exit());

        newMenuItem.setOnAction(e
                -> {
            System.out.println("Start a New Game");
        });

        fileMenu.getItems().addAll(newMenuItem, saveMenuItem, selectMenuItem, 
                new SeparatorMenuItem(), exitMenuItem, saveandexitMenuItem);

        Menu webMenu = new Menu("Speed");
        CheckMenuItem offline = new CheckMenuItem("Slow");
        offline.setSelected(false);
        CheckMenuItem online = new CheckMenuItem("Ultra");
        online.setSelected(true);
        Menu another = new Menu("Another Thing");
        MenuItem doanother = new MenuItem("Just Click");
        another.getItems().add(doanother);
        webMenu.getItems().addAll(offline, online, another);

        offline.setOnAction(e -> {
            System.out.println("Slow");
            online.setSelected(false);
        });

        online.setOnAction(e -> {
            System.out.println("Ultra");
            offline.setSelected(false);
        });

        Menu help = new Menu("Help");
        MenuItem gameInformation = new MenuItem("Information");
        MenuItem about = new MenuItem("About");

        help.getItems().addAll(gameInformation, about, new SeparatorMenuItem());

        Menu tutorialManeu = new Menu("Tutorial");
        tutorialManeu.getItems().addAll(
                new CheckMenuItem("Java"),
                new CheckMenuItem("JavaFX"),
                new CheckMenuItem("Swing"));

        help.getItems().add(tutorialManeu);

        Menu options = new Menu("Options");
        MenuItem sound = new MenuItem("Sound");
        MenuItem music = new MenuItem("Music");
        options.getItems().addAll(sound, music);

        menuBar.getMenus().addAll(fileMenu, webMenu, options, help);

    }

}
