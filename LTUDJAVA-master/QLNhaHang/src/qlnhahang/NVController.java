/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlnhahang;

import Model.NhanVien;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class NVController implements Initializable {

    @FXML
    private TableColumn<?, ?> ColNgaySinh;
    
    @FXML
    private ComboBox<String> cboQuyen;
    
    @FXML
    private TableColumn<?, ?> ColQuyen;

    @FXML
    private TableColumn<?, ?> ColTenDN;

    @FXML
    private TableView<NhanVien> tblNhanVien;

    @FXML
    private TableColumn<?, ?> ColMaNV;
    @FXML
    private TextField txtDN;

    @FXML
    private Button btnthem;
    @FXML
    private DatePicker dpkNgaySinh;
    @FXML
    private TextField txtPreMK;

    @FXML
    private TextField txtMK;

    @FXML
    private TextField txtHoTen;
    @FXML
    private TextField txtQuyen;
    @FXML
    private Pane pnThongTin;
    int id;
    @FXML
    private Button btnXoa;
      @FXML
    private Button btnCapNhat;
    @FXML
    private TableColumn<?, ?> ColHoTen;
    private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private PreparedStatement pstid = null;
    private ObservableList<NhanVien> data;
    private int Postion;
    ObservableList<String> list =FXCollections.observableArrayList("Giám Đốc","Quản Lý","Nhân Viên");
   
    /**
     * Initializes the controller class.
     */
    @FXML
    void btnthemAction(ActionEvent event) {
        String sql = "Insert into NhanVien(MaNV,HoTen,NgaySinh,TenDN,MatKhau,Quyen)Values(?,?,?,?,?,?)";
        String HoTen = txtHoTen.getText();
         String date = dpkNgaySinh.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String TenDN = txtDN.getText();
        String MatKhau = txtMK.getText();
        String Quyen = cboQuyen.getValue();
        System.out.print(date);
        try {
            pstid = con.prepareStatement("select top 1 * from NhanVien order by MaNV desc;");
            pst = con.prepareStatement(sql);
            rs = pstid.executeQuery();
            int j = 0;
            while (rs.next()) {
                id = rs.getInt("MaNV") + 1;

            }
            pst.setInt(1, id);
            pst.setString(2, HoTen);
            pst.setString(3,date);
            pst.setString(4, TenDN);
            pst.setString(5, MatKhau);
            pst.setString(6, Quyen);
            int i = pst.executeUpdate();
            if (i == 1) {
                System.out.print("Them Thanh Cong");
           
                
            }
            else 
            {
                System.out.print("Them That Bai");
            }
                 setCellTable();
                 LoadData();
        } catch (SQLException ex) {
            Logger.getLogger(NVController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
      @FXML
    void btnXoaAction(ActionEvent event) {
        String sql="delete from NhanVien where MaNV=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, Postion);
            int i = pst.executeUpdate();
            if (i == 1) {
                System.out.print("Delete Thanh Cong");

            } else {
                System.out.print("Delete That Bai");
            }
            setCellTable();
            LoadData();
        } catch (SQLException ex) {
            Logger.getLogger(NVController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     @FXML
    void btnCapNhatAction(ActionEvent event) {
                try {
                    setCellValueTable();
                    
              String sql = "update NhanVien set HoTen=?, NgaySinh=?,TenDN=?,Quyen=? where MaNV=?";
              String HoTen = txtHoTen.getText();
              String date = dpkNgaySinh.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
              String TenDN = txtDN.getText();
              
              String Quyen = cboQuyen.getValue();
              pst = con.prepareStatement(sql);
              pst.setString(1, HoTen);
              pst.setString(2, date);
              pst.setString(3, TenDN);
              //    pst.setString(5, MatKhau);
              pst.setString(4, Quyen);
               pst.setInt(5,Postion);
               int i = pst.executeUpdate();
            if (i == 1) {
                System.out.print("Cap nhat Thanh Cong");
           
                
            }
            else 
            {
                System.out.print("Cap Nhat That Bai");
            }
             setCellTable();
                 LoadData();
          } catch (SQLException ex) {
              Logger.getLogger(NVController.class.getName()).log(Level.SEVERE, null, ex);
          }
        
    }
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
         cboQuyen.setItems(list);
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
        ColMaNV.setCellValueFactory(new PropertyValueFactory<>("MaNV"));
        ColHoTen.setCellValueFactory(new PropertyValueFactory<>("HoTen"));
        ColNgaySinh.setCellValueFactory(new PropertyValueFactory<>("NgaySinh"));
        ColTenDN.setCellValueFactory(new PropertyValueFactory<>("TenDN"));
        ColQuyen.setCellValueFactory(new PropertyValueFactory<>("Quyen"));

    }

    private void LoadData() throws SQLException {
        data.clear();
        pst = con.prepareStatement("select * from NhanVien");
        rs = pst.executeQuery();

        while (rs.next()) {
            data.add(new NhanVien(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
        }
        tblNhanVien.setItems(data);
    }
    private void setCellValueTable()
    {
        tblNhanVien.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
               NhanVien nv= tblNhanVien.getItems().get(tblNhanVien.getSelectionModel().getSelectedIndex());
                 Postion=nv.getMaNV();
                 txtHoTen.setText(nv.getHoTen());
                dpkNgaySinh.setValue(LocalDate.parse(nv.getNgaySinh()));
               txtDN.setText(nv.getTenDN());
                cboQuyen.setValue(nv.getQuyen());                      
            }
        });
       
    }

}
