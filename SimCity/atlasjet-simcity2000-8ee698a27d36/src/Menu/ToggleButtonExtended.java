package Menu;

import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class ToggleButtonExtended extends StackPane {
    public static Type type = Type.NULL;
    private static final ArrayList<ToggleButtonExtended> list = new ArrayList<>();
    private static final double DEFAULT_SIZE = 80; 
    private ImageView img;
    private Pane table;
    private Label label;
    private ToggleButton button;
    private ToggleGroup group;
    public enum Type
    {
        ROAD,HOUSE , FACTORY , HOSPITAL , POLICE , FIRE , SCHOOL , ELECTRICITY , NULL
    }private final Type thisType;
    public ToggleButtonExtended(String imagePath , String labelName , Pane tb , Type type)
    {
        thisType = type;
        list.add(this);
        this.table = tb;
        this.button = new ToggleButton();
        this.img = new ImageView("file:" + imagePath);
        img.setFitWidth(40);
        img.setFitHeight(40);
        button.setGraphic(img);
        this.label = new Label(labelName);
        label.setVisible(false);
        label.setTextFill(Color.WHITE);
        StackPane.setAlignment(this.label, Pos.TOP_CENTER);
        this.group = new ToggleGroup();
        
        super.getChildren().addAll(button , this.label);
        this.setOnMouseEntered((event) -> {
           label.setVisible(true);
        });
        this.setOnMouseExited((event) -> {
            label.setVisible(false);
        });
        button.setOnMouseClicked((event) -> {
            list.forEach((t) -> {
                if(t.table != null)t.table.setVisible(false);
            });
            if(this.table != null)this.table.setVisible(true);
        });
        
        button.setOnMouseClicked((MouseEvent event) ->
        {
            if(button.isSelected())
            {
                ToggleButtonExtended.type = thisType;
            }
            else ToggleButtonExtended.type = ToggleButtonExtended.Type.NULL;
        });
        
        Utils.SettingRes.bValue.addListener((observable, oldValue, newValue) -> {
            button.setPrefSize(DEFAULT_SIZE * (Double)newValue, DEFAULT_SIZE * (Double)newValue);
            img.setFitHeight(DEFAULT_SIZE * (Double)newValue);
            img.setFitWidth(DEFAULT_SIZE * (Double)newValue);
        });
    }

    void setToggleGroup(ToggleGroup group) {
        
    }

    void setSelected(boolean b) {
        b = false;
    }
}
