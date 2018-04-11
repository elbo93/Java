/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlnhahang;

import DiaLog.AlertDialog;
import Model.GoiMon;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static java.awt.SystemColor.window;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableColumn<?, ?> ColDonGia;
     @FXML
    private ComboBox<String> CboBan;
     @FXML
    private Button btnTinhTien;
     @FXML
    private Button btnTest;
    @FXML
      private Connection con = null;
    private ResultSet rs = null;
    @FXML
    private TableView<GoiMon> tblCTHoaDon;
    
      @FXML
    private TableColumn<?, ?> ColSoLuong;
        @FXML
    private DatePicker dpkngaylaphd;
      @FXML
    private ComboBox<String> CboNhanVien;

    @FXML
    private TableColumn<?, ?> ColTenThucDon;
     private PreparedStatement pst = null;
     private PreparedStatement psthd = null;
      private PreparedStatement pstid = null;
      private int id;
      private int idhd;
     private ObservableList<GoiMon> data = FXCollections.observableArrayList();
     ObservableList<String> listbanan = FXCollections.observableArrayList();
      ObservableList<String> listnhanvien = FXCollections.observableArrayList();
    @FXML
    private TextField txtTest;
      @FXML
    private Pane PaneTinhTien;
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            con = DBConncet.DBConnection.pmartConnection();
//            setCellTable();
//            LoadData();
            LoadComboboxBan();
            LoadNhanVien();
        } catch (SQLException ex) {
            Logger.getLogger(DeoHieuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    private void setCellTable() {
       
        ColTenThucDon.setCellValueFactory(new PropertyValueFactory<>("ThucDon"));
      ColDonGia.setCellValueFactory(new PropertyValueFactory<>("DonGia"));
      ColSoLuong.setCellValueFactory(new PropertyValueFactory<>("SoLuong"));

    }
    private void LoadData() throws SQLException {
        String msb = CboBan.getValue();
        data.clear();
        pst = con.prepareStatement("select * from GoiMon where MaSoBan=" + msb + "");
        rs = pst.executeQuery();

        while (rs.next()) {
            data.add(new GoiMon(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5)));
            tblCTHoaDon.setItems(data);
        }
    }
     private void LoadComboboxBan() throws SQLException {

        pst = con.prepareStatement("select DISTINCT  MaSoBan  from GoiMon");
        rs = pst.executeQuery();

        while (rs.next()) {
            listbanan.add(rs.getString("MaSoBan"));
        }
        CboBan.setItems(listbanan);

    }
     private void LoadNhanVien() throws SQLException
     {
           pst=con.prepareStatement("select  HoTen from NhanVien where Quyen= N'Nhân Viên'");
         rs = pst.executeQuery();
          while (rs.next()) {
            listnhanvien.add(rs.getString("HoTen"));
        }
        CboNhanVien.setItems(listnhanvien);
     }
       @FXML
    void CboBanActive(ActionEvent event) throws SQLException {
        setCellTable();
        LoadData();
          
    }
    @FXML
    void CboNhanVienAction(ActionEvent event) throws SQLException {
      
    } 
     @FXML
    void btnTinhTienAction(ActionEvent event) throws SQLException {
          String msb = CboBan.getValue();
      pst=con.prepareStatement("select Sum( [SoLuong] * [Dongia]) from GoiMon where MaSoBan=" + msb + "");
      rs=pst.executeQuery();
    while (rs.next()){
       txttinhtien.setText(String.valueOf(rs.getInt(1)));
    }
    
    }
      @FXML
     
    void btnLapHoaDonAction(ActionEvent event) throws SQLException {
        String sql = "Insert into HoaDon(SoHD,ThoiGianLap,MaSoBan,TongTien,HoTen)Values(?,?,?,?,?)";
            int MaSoBan=Integer.parseInt(CboBan.getValue());
            String HoTen=CboNhanVien.getSelectionModel().getSelectedItem();
            String TongTien=txttinhtien.getText();
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            try {
               
                pstid = con.prepareStatement("select top 1 * from HoaDon order by SoHD desc;");
                pst = con.prepareStatement(sql);
                rs = pstid.executeQuery();
             
                while (rs.next()) {
                    id = rs.getInt("SoHd") + 1;
                }
                pst.setInt(1,id);
               pst.setDate(2,date);
               pst.setInt(3,MaSoBan);
                pst.setString(4, TongTien);
                pst.setString(5, HoTen);
              
                int i = pst.executeUpdate();
                if (i == 1) {
                    AlertDialog.display("Infor","Thêm  Thành Công");
                } 
                else {
                     AlertDialog.display("Infor","Thêm thất bại");
                }
                setCellTable();
                LoadData();
            } catch (SQLException ex) {
                Logger.getLogger(NVController.class.getName()).log(Level.SEVERE, null, ex);
            }

        pstid = con.prepareStatement("select top 1 * from CTHD order by ID desc;");
               // pst = con.prepareStatement(sql);
                rs = pstid.executeQuery();
             
                while (rs.next()) {
                    id = rs.getInt("ID")+1;
//                    System.out.println("Id:");
                    System.out.println(id);
                }
                 pstid = con.prepareStatement("select top 1 * from HoaDon order by SoHD desc;");
                rs = pstid.executeQuery();
             
                while (rs.next()) {
                    idhd = rs.getInt("SoHd");
                    System.out.println("SoHD:");
                    System.out.println(idhd);
                }
        String msb = CboBan.getValue();
        data.clear();
        
        pst = con.prepareStatement("select * from GoiMon where MaSoBan=" + msb + "");
        rs = pst.executeQuery();
        int j=0;
        while (rs.next()) {
          
          
             String sql1 = "Insert into CTHD(ID,SOHD,SoLuong,DonGia,TenThucDon)Values(?,?,?,?,?)";
             psthd = con.prepareStatement(sql1);
              psthd.setInt(1, id+j);
               psthd.setInt(2,idhd);
               psthd.setInt(3,rs.getInt(5));
                psthd.setInt(4,rs.getInt(4));
                psthd.setString(5,rs.getString(3));
                int i = psthd.executeUpdate();
                if (i == 1) {
                  //  AlertDialog.display("Infor","Thêm  Thành Công");
                } 
                else {
                   //  AlertDialog.display("Infor","Thêm thất bại");
                }
                j++;
        }
          String sql2="delete from GoiMon where MaSoBan=" + msb + "";
        try {
            pst = con.prepareStatement(sql2);
            int i = pst.executeUpdate();
          
               // //AlertDialog.display("Infor","Delete  Thành Công");

            
        } catch (SQLException ex) {
            Logger.getLogger(NVController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            @FXML
    void btnTestAction(ActionEvent event) {
   
    }  



}
