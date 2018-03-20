/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlnhahang;

import Model.Menu;
import Model.NhanVien;
import DiaLog.AlertDialog;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class MenuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    //---------------------------------------Khai Bao Bien-------------------------------------------------------- 
     //---------------------------------------Khai Bao Bien-------------------------------------------------------- 
     //---------------------------------------Khai Bao Bien-------------------------------------------------------- 
    
    
    
    @FXML
    private TableColumn<?, ?> ColDonViTinh;

    @FXML
    private TableColumn<?, ?> colLoaiThucDon;

    @FXML
    private TableColumn<?, ?> ColGiaHienHanh;

    @FXML
    private TableColumn<?, ?> ColTenMonAn;

    @FXML
    private TableColumn<?, ?> ColMTD;
      @FXML
    private TableView<Menu> tblMenu;

    private ObservableList<Menu> data;
     private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private PreparedStatement pstid = null;
    private int Postion;
    private int id;
     @FXML
    private TextField txtDVT;

    @FXML
    private TextField txtTenMonAn;

    @FXML
    private TextField txtLoaiThucDon;
      @FXML
    private TextField txtGiaHienHanh;
      @FXML
    private Button btnXoa;
      @FXML
    private Button btnCapNhat;
      @FXML
    private Button btnNew;

      
///-----------------------------------Button Action-------------------------------------------------
///-----------------------------------Button Action-------------------------------------------------
///-----------------------------------Button Action-------------------------------------------------
       @FXML
    void btnThemAction(ActionEvent event) {
        
         String sql = "Insert into ThucDon(MaThucDon,MaLoai,TenThucDon,DonViTinh,GiaHienHanh)Values(?,?,?,?,?)";
      
            String MaLoai=txtLoaiThucDon.getText();
            String TenThucDon=txtTenMonAn.getText();
            String DonViTinh=txtDVT.getText();
            String GiaHienHanh=txtGiaHienHanh.getText();
        try {
            pstid = con.prepareStatement("select top 1 * from ThucDon order by  MaThucDon desc;");
            pst = con.prepareStatement(sql);
            rs = pstid.executeQuery();
            int j = 0;
            while (rs.next()) {
                id = rs.getInt("MaThucDon") + 1;

            }
            pst.setInt(1, id);
            pst.setString(2, MaLoai);
            pst.setString(3,TenThucDon);
            pst.setString(4, DonViTinh);
            pst.setString(5, GiaHienHanh);
            int i = pst.executeUpdate();
            if (i == 1) {
                AlertDialog.display("Infor","Them Thanh Cong");
           
                
            }
            else 
            {
               AlertDialog.display("Infor","Them That Bai");
            }
                 setCellTable();
                 LoadData();
        } catch (SQLException ex) {
            Logger.getLogger(NVController.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
      @FXML
    void btnXoaAction(ActionEvent event) {
          Alert alert = new Alert(AlertType.CONFIRMATION);
          alert.setTitle("Thong Bao !!!");
          alert.setHeaderText("Look, a Confirmation Dialog");
          alert.setContentText("Ban Co Muon Xoa Khong?");

          Optional<ButtonType> result = alert.showAndWait();
          if (result.get() == ButtonType.OK) {
              String sql = "delete from ThucDon where MaThucDon=?";
              try {
                  pst = con.prepareStatement(sql);
                  pst.setInt(1, Postion);
                  int i = pst.executeUpdate();
                  if (i == 1) {
                      AlertDialog.display("Infor", "Xoa Thanh Cong");

                  } else {
                      AlertDialog.display("Infor", "Xoa That Bai");
                  }
                  setCellTable();
                  LoadData();
              } catch (SQLException ex) {
                  Logger.getLogger(NVController.class.getName()).log(Level.SEVERE, null, ex);
              }
          } else {
              alert.close();
          }
//         String sql="delete from ThucDon where MaThucDon=?";
//        try {
//            pst = con.prepareStatement(sql);
//            pst.setInt(1, Postion);
//            int i = pst.executeUpdate();
//            if (i == 1) {
//                AlertDialog.display("Infor","Xoa Thanh Cong");
//
//            } else {
//               AlertDialog.display("Infor","Xoa That Bai");
//            }
//            setCellTable();
//            LoadData();
//        } catch (SQLException ex) {
//            Logger.getLogger(NVController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
      @FXML
    void btnCapNhatAction(ActionEvent event) {
         try {
                    setCellValueTable();
                    
              String sql = "update ThucDon set MaLoai=?,TenThucDon =?,DonViTinh=?,GiaHienHanh=? where MaThucDon=?";
            String MaLoai=txtLoaiThucDon.getText();
            String TenThucDon=txtTenMonAn.getText();
            String DonViTinh=txtDVT.getText();
            String GiaHienHanh=txtGiaHienHanh.getText();
              pst = con.prepareStatement(sql);
              pst.setString(1, MaLoai);
              pst.setString(2, TenThucDon);
              pst.setString(3, DonViTinh);
              //    pst.setString(5, MatKhau);
              pst.setString(4, GiaHienHanh);
               pst.setInt(5,Postion);
               int i = pst.executeUpdate();
            if (i == 1) {
               // System.out.print("Cap nhat Thanh Cong");
                AlertDialog.display("Infor","Cap Nhat Thanh Cong");
                
            }
            else 
            {
               AlertDialog.display("Infor","Cap Nhat That Bai");
            }
             setCellTable();
                 LoadData();
          } catch (SQLException ex) {
              Logger.getLogger(NVController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
     @FXML
    void btnNewAction(ActionEvent event) {
        txtDVT.clear();
        txtGiaHienHanh.clear();
        txtLoaiThucDon.clear();
        txtTenMonAn.clear();
    }
    
    
    //------------------------------------------------Function--------------------------------
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            con = DBConncet.DBConnection.pmartConnection();
             data = FXCollections.observableArrayList();
            setCellTable();
            LoadData();
            setCellValueTable();
        } catch (SQLException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }
    private void setCellTable()
    {
         ColMTD.setCellValueFactory(new PropertyValueFactory<>("MaThucDon"));
         colLoaiThucDon.setCellValueFactory(new PropertyValueFactory<>("MaLoai"));
         ColTenMonAn.setCellValueFactory(new PropertyValueFactory<>("TenThucDon"));
         ColDonViTinh.setCellValueFactory(new PropertyValueFactory<>("DonViTinh"));
         ColGiaHienHanh.setCellValueFactory(new PropertyValueFactory<>("GiaHienHanh"));     
    }
    private void LoadData() throws SQLException {
        data.clear();
        pst = con.prepareStatement("select * from ThucDon");
        rs = pst.executeQuery();

        while (rs.next()) {
            data.add(new Menu(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getFloat(5)));
        }
        tblMenu.setItems(data);
    }
        private void setCellValueTable()
    {
        tblMenu.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
               Menu menu= tblMenu.getItems().get(tblMenu.getSelectionModel().getSelectedIndex());
                 Postion=menu.getMaThucDon();
               txtLoaiThucDon.setText(String.valueOf(menu.getMaLoai()));
                txtGiaHienHanh.setText(String.valueOf(menu.getGiaHienHanh()));
                txtTenMonAn.setText(menu.getTenThucDon());
                txtDVT.setText(menu.getDonViTinh());
             //txtGiaHienHanh.setText(menu.getGiaHienHanh());
            }
        });
       
    }
}
