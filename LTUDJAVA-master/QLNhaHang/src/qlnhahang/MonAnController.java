/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlnhahang;

import DiaLog.AlertDialog;
import Model.MonAn;
import Model.NhanVien;
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
public class MonAnController implements Initializable {
     @FXML
    private TextField txtTenLoai;

    @FXML
    private Button btnThem;

    @FXML
    private TableColumn<?, ?> ColMaLoai;

    @FXML
    private Button btnSua;

    @FXML
    private TextField txtNhom;

    @FXML
    private TableColumn<?, ?> ColNhom;

    @FXML
    private TableColumn<?, ?> ColTenNhom;

    @FXML
    private Button btnXoa;
     @FXML
    private TableView<MonAn> tblMonAn;

    /**
     * Initializes the controller class.
     */
     private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
   private PreparedStatement pstid = null;
    private ObservableList<MonAn> data;
    private int Postion;
    private int id;
  //--------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------
      @FXML
    void btnThemAction(ActionEvent event) {
        String sql = "Insert into LoaiThucDon(MaLoai,Nhom,TenLoai)Values(?,?,?)";
        String Nhom = txtNhom.getText();
        String TenLoai=txtTenLoai.getText();
       
   //     System.out.print(date);
        try {
            pstid = con.prepareStatement("select top 1 * from LoaiThucDon order by MaLoai desc;");
            pst = con.prepareStatement(sql);
            rs = pstid.executeQuery();
            int j = 0;
            while (rs.next()) {
                id = rs.getInt("MaLoai") + 1;

            }
            pst.setInt(1, id);
            pst.setString(2, Nhom);
            pst.setString(3,TenLoai);
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
    void btnSuaAction(ActionEvent event) {
         try {
                    setCellValueTable();
                    
              String sql = "update LoaiThucDon set Nhom=?,TenLoai =? where MaLoai=?";
            String Nhom = txtNhom.getText();
            String TenLoai=txtTenLoai.getText();
              pst = con.prepareStatement(sql);
              pst.setString(1, Nhom);
              pst.setString(2, TenLoai);
               pst.setInt(3,Postion);
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
    void btnXoaAction(ActionEvent event) {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          alert.setTitle("Thong Bao !!!");
          alert.setHeaderText("Look, a Confirmation Dialog");
          alert.setContentText("Ban Co Muon Xoa Khong?");

          Optional<ButtonType> result = alert.showAndWait();
          if (result.get() == ButtonType.OK) {
              String sql = "delete from LoaiThucDon where MaLoai=?";
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
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
        
            con = DBConncet.DBConnection.pmartConnection();
            data = FXCollections.observableArrayList(); 
           setCellTable();
            LoadData();
            setCellValueTable();
        } catch (SQLException ex) {
            Logger.getLogger(NVController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }    
     private void setCellTable() {
        ColMaLoai.setCellValueFactory(new PropertyValueFactory<>("MaLoai"));
        ColNhom.setCellValueFactory(new PropertyValueFactory<>("Nhom"));
        ColTenNhom.setCellValueFactory(new PropertyValueFactory<>("TenLoai"));
    }
     private void LoadData() throws SQLException {
       data.clear();
        pst = con.prepareStatement("select * from LoaiThucDon");
        rs = pst.executeQuery();

        while (rs.next()) {
            data.add(new MonAn(rs.getInt(1), rs.getString(2), rs.getString(3)));
        }
        tblMonAn.setItems(data);
    }
     private void setCellValueTable()
    {
        tblMonAn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
               MonAn ma= tblMonAn.getItems().get(tblMonAn.getSelectionModel().getSelectedIndex());
                 Postion=ma.getMaLoai();
                 txtNhom.setText(ma.getNhom());
                 txtTenLoai.setText(ma.getTenLoai());
                                   
            }
        });
       
    }
}
