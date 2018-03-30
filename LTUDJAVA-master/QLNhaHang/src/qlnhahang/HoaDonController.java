/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlnhahang;

import DiaLog.AlertDialog;
import Model.CTHoaDon;
import Model.HoaDon;
import Model.NhanVien;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
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
    //private PreparedStatement pstid = null;
    private ObservableList<HoaDon> data;
    private ObservableList<CTHoaDon> dataHD;
    private int Postion;
    
    @FXML
    private TableView<HoaDon> TblDanhSachHoaDon;
     @FXML
    private TableColumn<?, ?> colSoHD;
    @FXML
    private Button BtnXoaHoaDon;
    
      @FXML
    private TableColumn<?, ?> ColSoLuong;
   @FXML
    private TableView<CTHoaDon> tblCTHoaDon;
    @FXML
    private TableColumn<?, ?> ColTongTien;
    
     @FXML
    private TableColumn<?, ?> ColTenThucDon;
      
         @FXML
    private TableColumn<?, ?> ColDonGia;
    
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
    private TextField TxtTraCuuHoaDon;

    @FXML
    private TableColumn<?, ?> ColNguoiThuTien;

    @FXML
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //System.out.print("Vai");
         try {
            con = DBConncet.DBConnection.pmartConnection();
            data = FXCollections.observableArrayList();
             dataHD=FXCollections.observableArrayList();
           LoadData();
            setCellTable();
           setCellValueTable();
          SetCellCTHoaDon();
            SreachHD();
            
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
      private void SetCellCTHoaDon()
      {
          ColTenThucDon.setCellValueFactory(new PropertyValueFactory<>("TenThucDon"));
       
          ColDonGia.setCellValueFactory(new PropertyValueFactory<>("DonGia"));
          ColSoLuong.setCellValueFactory(new PropertyValueFactory<>("SoLuong"));
      }
       private void LoadCTHoaDon(int pos) throws SQLException {
        dataHD.clear();
        pst = con.prepareStatement("select * from ChiTietHD where SoHD=?");
        pst.setInt(1, pos);
        rs = pst.executeQuery();
       
        while (rs.next()) {
            
            dataHD.add(new CTHoaDon(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getFloat(4),rs.getString(5)));

        }
      tblCTHoaDon.setItems(dataHD);
    }
        @FXML
      void btnXoaAction(ActionEvent event) {

        String sql="delete from HoaDon where SoHD=?";
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
                  Logger.getLogger(HoaDonController.class.getName()).log(Level.SEVERE, null, ex);
              }
    }
      private void setCellValueTable()
    {
        TblDanhSachHoaDon.setOnMouseClicked((MouseEvent event) -> {
         //  NhanVien nv=tblNhanVien.getItems().get(0);
              HoaDon hd= TblDanhSachHoaDon.getItems().get(TblDanhSachHoaDon.getSelectionModel().getSelectedIndex());
            Postion=hd.getSoHD();
            System.out.print(Postion);
            try {
                LoadCTHoaDon(Postion);
            } catch (SQLException ex) {
                Logger.getLogger(HoaDonController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
      private void SreachHD()
   {
       //data.clear();
       FilteredList<HoaDon> filtereddata=new FilteredList<>(data,e->true);
      TxtTraCuuHoaDon.setOnKeyReleased(e->{
       TxtTraCuuHoaDon.textProperty().addListener((ObservableValue, oldValue,newValue)->{
           filtereddata.setPredicate((Predicate<?super HoaDon>) nv->{
           if(newValue==null||newValue.isEmpty())
           {
               return true;
           }
             String   lowerCaseFilter=newValue.toLowerCase();
//             if(nv.getSoHD().contains(newValue))
//             {
//                 return true;
//             }
             int a=nv.getSoHD();
            
            if( Integer.toString(a).contains(newValue))
              
             {
                 return true;
             }
//             if( nv.getTenDN().contains(newValue))   
//             {
//                 return true;
//             }        
               return false;
           });
           });
           
       });
       SortedList<HoaDon> sorted=new SortedList<>(filtereddata);
       sorted.comparatorProperty().bind(TblDanhSachHoaDon.comparatorProperty());
       TblDanhSachHoaDon.setItems(sorted);
   }
}
