/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlnhahang;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class QuanLySreenController implements Initializable {
    @FXML
    private Button btnHoaDon;
    @FXML
    private Pane menumain;
    @FXML
    private AnchorPane homePane;
    @FXML
    private Button btnQLHoaDon;
    @FXML
    private Button btnGoiMon;
    @FXML
    private Button btnBaoCao;
    @FXML
    private Button btnThongKe;
    @FXML
    private Button btnNhanVien;
    @FXML
    private Button btnMenu;
    @FXML
    private Button btnThoat;
    
     private Connection con = null;
     @FXML
    void btnHoaDonAction(ActionEvent event) throws IOException {
        this.createPage(homePane,"/qlnhahang/HoaDon.fxml");
    }
    @FXML
    void btnMenuAction(ActionEvent event) throws IOException{
        this.createPage(homePane,"/qlnhahang/Menu.fxml");
    }
   @FXML
    void btnQLHoaDonAction(ActionEvent event) throws IOException {
         this.createPage(homePane,"/qlnhahang/DeoHieu.fxml");
    }
      @FXML
    void btnGoiMonAction(ActionEvent event) throws IOException {
  this.createPage(homePane,"/qlnhahang/GoiMon.fxml");
    }
    @FXML
    void btnBaoCaoAction(ActionEvent event) throws IOException {
        // this.createPage(homePane,"/qlnhahang/BaoCao.fxml");
    }
      @FXML
    void btnThongKeAction(ActionEvent event) throws IOException {
  this.createPage(homePane,"/qlnhahang/Thong Ke.fxml");
    }
    @FXML
    void btnNhanVienAction(ActionEvent event) throws IOException {
         this.createPage(homePane,"/qlnhahang/NV.fxml");
    }
   @FXML
    void btnThoatAction(ActionEvent event) {
        Platform.exit();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            con=DBConncet.DBConnection.pmartConnection();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    public void setNode(Node node )
    {
        menumain.getChildren().clear();
        menumain.getChildren().add((Node)node);
    }
    public void createPage(AnchorPane home ,String loc) throws IOException
    {
        home=FXMLLoader.load(getClass().getResource(loc));
        setNode(home);
    }
    
    
}
