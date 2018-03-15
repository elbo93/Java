/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DiaLog;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Dell
 */
public class AlertDialog {
    public static void display(String tiltle,String Message)
    {
        Stage window =new Stage();
        window.setTitle(tiltle);
        window.setWidth(250);
        window.setHeight(100);
        
        Label label=new Label();
        label.setText(Message);
        Button buttonOK=new Button("OK");
        buttonOK.setOnAction(e->window.close());
        
        VBox layout =new VBox(5);
        layout.getChildren().addAll(label,buttonOK);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene=new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
         
        
    }
            
}
