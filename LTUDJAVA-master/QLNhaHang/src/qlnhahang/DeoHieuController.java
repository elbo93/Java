/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlnhahang;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Dell
 */
 
public class DeoHieuController implements Initializable {
    @FXML
    private Button btnLapHoaDon;
    @FXML
    private TextField txttinhtien;
    @FXML
    private TextField txtTest;
      @FXML
    private Pane PaneTinhTien;
    @FXML
     
    void btnLapHoaDonAction(ActionEvent event) {
        System.out.print("Tinh Tien");
        txttinhtien.setText("DaiCa");
        txtTest.setText("Nhat");
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.print("OK");
        // txttinhtien.setText("10000");
    }    
    
}
