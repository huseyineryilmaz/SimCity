package Menu;


public class ButtonSize extends javafx.scene.control.Button{
    
    public ButtonSize(){
    
 
        Utils.SettingRes.bValue.addListener((observable, oldValue, newValue) -> {
            this.setPrefSize(90 * (Double)newValue, 20 * (Double)newValue);
        });
        
  
        
    }
}
