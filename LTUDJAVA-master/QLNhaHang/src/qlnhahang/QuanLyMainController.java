/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlnhahang;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class QuanLyMainController implements Initializable {

     @FXML
    private BorderPane homePane;
    @FXML
    private AnchorPane MenuPane;
     @FXML
    private Button btnMenu;
    /**
     * Initializes the controller class.
     */
    @FXML
    void MenuAction(ActionEvent event) throws IOException {
         this.createPage(homePane,"/qlnhahang/Test.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setNode(Node node )
    {
        MenuPane.getChildren().clear();
        MenuPane.getChildren().add((Node)node);
    }
    public void createPage(BorderPane home ,String loc) throws IOException
    {
        home=FXMLLoader.load(getClass().getResource(loc));
        setNode(home);
    }
}
