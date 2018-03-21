/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlnhahang;

import Model.HoaDon;
import Model.NhanVien;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class HoaDonController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
     private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private PreparedStatement pstid = null;
    private ObservableList<HoaDon> data;
    private int Postion;
    
    @FXML
    private TableView<HoaDon> TblDanhSachHoaDon;

    @FXML
    private Button BtnXoaHoaDon;

    @FXML
    private TableColumn<?, ?> ColTongTien;

    @FXML
    private Button BtnInHoaDon;

    @FXML
    private TextField TxtTimHD;

    @FXML
    private TableColumn<?, ?> ColTGLap;

    @FXML
    private TableColumn<?, ?> ColNguoiLap;

    @FXML
    private TableColumn<?, ?> ColSoHoaDon;

    @FXML
    private TableColumn<?, ?> ColMSBan;

    @FXML
    private TableColumn<?, ?> ColSoKhach;

    @FXML
    private TextArea TxtThongTinHoaDon;

    @FXML
    private TableColumn<?, ?> ColNguoiThuTien;

    @FXML
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //System.out.print("Vai");
         try {
            con = DBConncet.DBConnection.pmartConnection();
            data = FXCollections.observableArrayList();
            setCellTable();
            LoadData();
            
        } catch (SQLException ex) {
            Logger.getLogger(NVController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
           private void setCellTable() {
        ColSoHoaDon.setCellValueFactory(new PropertyValueFactory<>("SoHD"));
        ColTGLap.setCellValueFactory(new PropertyValueFactory<>("ThoiGianLap"));
        ColMSBan.setCellValueFactory(new PropertyValueFactory<>("MaSoBan"));
        ColSoKhach.setCellValueFactory(new PropertyValueFactory<>("SoKhach"));
        ColNguoiLap.setCellValueFactory(new PropertyValueFactory<>("MaNVlap"));
        ColTongTien.setCellValueFactory(new PropertyValueFactory<>("TongTien"));

    }
      
      private void LoadData() throws SQLException {
        data.clear();
        pst = con.prepareStatement("select * from HoaDon");
        rs = pst.executeQuery();

        while (rs.next()) {
            data.add(new HoaDon(rs.getInt(1), rs.getDate(2), rs.getInt(3), rs.getInt(4), rs.getInt(5),rs.getFloat(6)));
        }
        TblDanhSachHoaDon.setItems(data);
    }

    
}
