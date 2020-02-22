package scene;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import events.SceneChangeEvent;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class MenuCity {

    public Scene scene;
    FadeTransition transition;
    private Image image,image1,image2;
    private ImageView imageview,imageview1,imageview2;
    private ToggleButton btn, btn1, btn2, btn3,btn4,btn5;
    private Pane root, rootCr, rootOp;
    private Label l;

    public MenuCity() {
        
        image1 = new Image("file:Resources/Sprites/MainImg1.png");
        imageview1 = new ImageView();
        imageview1.setImage(image1);
        imageview1.setFitHeight(1080);
        imageview1.setFitWidth(1920);
        imageview1.setVisible(true);
        
        image2 = new Image("file:Resources/Sprites/MainImg2.png");
        imageview2 = new ImageView();
        imageview2.setImage(image2);
        imageview2.setFitHeight(1080);
        imageview2.setFitWidth(1920);
        imageview2.setVisible(true);
        
        
        root = new Pane();
        scene = new Scene(root);
        
        rootCr = new Pane();
        rootCr.getChildren().add(imageview1);
        btn4 = new ButtonArg();
        rootCr.getChildren().add(btn4);
        btn4.setText("Back");
        
        l = new Label();
        l.setText("This game is made by Mustafa Asım Reyhan, "
                + "Ahmet Gürdal, \nHüseyin Eryılmaz and Ramis Celil Turgut who are students of \n"
                + "Department of Computer Engineering at Akdeniz University.\nHave a good time!");
        l.setFont(Font.font("Verdana",FontWeight.BOLD,25));
        l.setTextFill(Color.GOLD);
        l.setLayoutX(400);
        l.setLayoutY(400);
        rootCr.getChildren().add(l);
        
        
        
        btn4.setOnAction(e -> {
            scene.setRoot(root);
        });
        
        rootOp = new Pane();
        rootOp.getChildren().add(imageview2);
        btn5 = new ButtonArg();
        btn5.setText("Back");
        rootOp.getChildren().add(btn5);
        
        btn5.setOnAction(e -> {
            scene.setRoot(root);
        });
        
        
        
        btn = new ButtonArg();
        btn1 = new ButtonArg();
        btn2 = new ButtonArg();
        btn3 = new ButtonArg();
        
        
        ToggleGroup gp = new ToggleGroup();
        btn.setToggleGroup(gp);
        btn1.setSelected(false);
        btn2.setToggleGroup(gp);
        btn3.setToggleGroup(gp);

        btn.setText("New Game");
        btn1.setText("Options");
        btn2.setText("Credit");
        btn3.setText("Exit");
        

        btn.setOnAction(e -> {
            btn.fireEvent(new SceneChangeEvent(SceneChangeEvent.GAME_SCENE));
        });

        btn1.setOnAction(e -> {
            scene.setRoot(rootOp);
        });

        btn2.setOnAction(e -> {
            scene.setRoot(rootCr);
        });

        btn3.setOnAction(e -> {
            Platform.exit();
        });
        
        

        int duration = 2000;

        Path path = new Path();
        path.getElements().add(new MoveTo(-100, 500));
        path.getElements().add(new HLineTo(150));
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(duration));
        pathTransition.setPath(path);
        pathTransition.setNode(btn);
        pathTransition.play();

        Path path1 = new Path();
        path1.getElements().add(new MoveTo(-300, 575));
        path1.getElements().add(new HLineTo(200));
        PathTransition pathTransition1 = new PathTransition();
        pathTransition1.setDuration(Duration.millis(duration));
        pathTransition1.setPath(path1);
        pathTransition1.setNode(btn1);
        pathTransition1.play();

        Path path2 = new Path();
        path2.getElements().add(new MoveTo(-500, 650));
        path2.getElements().add(new HLineTo(250));
        PathTransition pathTransition2 = new PathTransition();
        pathTransition2.setDuration(Duration.millis(duration));
        pathTransition2.setPath(path2);
        pathTransition2.setNode(btn2);
        pathTransition2.play();

        Path path3 = new Path();
        path3.getElements().add(new MoveTo(-700, 725));
        path3.getElements().add(new HLineTo(300));
        PathTransition pathTransition3 = new PathTransition();
        pathTransition3.setDuration(Duration.millis(duration));
        pathTransition3.setPath(path3);
        pathTransition3.setNode(btn3);
        pathTransition3.play();
        
        image = new Image("file:Resources/Sprites/MainImg.png");
        imageview = new ImageView();
        imageview.setImage(image);
        imageview.setFitHeight(1080);
        imageview.setFitWidth(1920);
        imageview.setVisible(true);
        root.getChildren().add(imageview);
        root.getChildren().addAll(btn, btn1, btn2, btn3);
        scene.getStylesheets().add("file:Resources/Style/MainStyle.css");
        //scene2.getStylesheets().add("file:Resources/Style/StyleButton.css");
    }
}
