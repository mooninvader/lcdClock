package customcontrol;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author hakim
 */

public class CustomControl extends Application {

    @Override
    public void start(Stage primaryStage) {

        LcdClock lcd=new LcdClock();        
        //lcd.setStyle("-fx-lcd-bk : yellow;");
  
        lcd.setOnMouseClicked((e)->{
            System.out.println("hello");
        });
        
        lcd.setOnColor(Color.AQUA);
        StackPane root = new StackPane();
        root.getChildren().addAll(lcd);        
        Scene scene = new Scene(root, 300, 200); 
        primaryStage.setTitle("Custom control");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }

}

