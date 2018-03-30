/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlnhahang;

import DiaLog.AlertDialog;
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
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javax.xml.validation.Validator;
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
    private TextField txtSreach;
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
        
        String text = btnthem.getText();
        String t = "Thêm";
        String t2 = "Lưu";
      

        if (text.equals(t)) {
              btnCapNhat.setDisable(true);
            btnXoa.setDisable(true);
            txtDN.clear();
            txtHoTen.clear();
            txtDN.setDisable(false);
            txtHoTen.setDisable(false);
            txtMK.setDisable(false);
            txtPreMK.setDisable(false);
            txtQuyen.setDisable(false);
            btnthem.setText("Lưu");
            tblNhanVien.setDisable(true);
           cboQuyen.setDisable(false);
        } else
        {
            if (text.equals(t2)) {
               
            String sql = "Insert into NhanVien(MaNV,HoTen,NgaySinh,TenDN,MatKhau,Quyen)Values(?,?,?,?,?,?)";
            String HoTen = txtHoTen.getText();
            String date = dpkNgaySinh.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String TenDN = txtDN.getText();
            String MatKhau = txtMK.getText();
            String Quyen = cboQuyen.getValue();
            System.out.print(date);
            if(ValidateEmpty()){
            if(ValidateName()){  
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
                pst.setString(3, date);
                pst.setString(4, TenDN);
                pst.setString(5, MatKhau);
                pst.setString(6, Quyen);
                int i = pst.executeUpdate();
                if (i == 1) {
                    AlertDialog.display("Infor","Thêm  Thành Công");
                     btnthem.setText("Thêm");
                     tblNhanVien.setDisable(false);
                    

                } else {
                     AlertDialog.display("Infor","Thêm thất bại");
                }
                setCellTable();
                LoadData();
            
            } catch (SQLException ex) {
                Logger.getLogger(NVController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            }
            
        }
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
                 AlertDialog.display("Infor","Delete Thành Công");
                btnXoa.setDisable(true);
                btnCapNhat.setDisable(true);
                
            } else {
                AlertDialog.display("Infor","Delete  Thất Bại");
              
            }
               setCellTable();
                 LoadData();
                  SreachNV();
        } catch (SQLException ex) {
            Logger.getLogger(NVController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    void btnCapNhatAction(ActionEvent event) {
        try {
            String text = btnCapNhat.getText();
            String t = "Cập Nhật";
            String t2 = "Lưu";
          
            if (text.equals(t)) {
               
                txtDN.setDisable(false);
                txtHoTen.setDisable(false);
                txtMK.setDisable(false);
                txtPreMK.setDisable(false);
                txtQuyen.setDisable(false);
                btnCapNhat.setText("Lưu");
            } else {
                if (text.equals(t2)) {

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
                    pst.setInt(5, Postion);
                    int i = pst.executeUpdate();
                    if (i == 1) {
                        AlertDialog.display("Infor","Cập nhật thành công");
                        btnCapNhat.setText("Cập Nhật");
                        btnCapNhat.setDisable(true);
                        btnXoa.setDisable(true);

                    } else {
                       AlertDialog.display("Infor","Cập nhật thất bại");
                    }
                    setCellTable();
                    LoadData();
                    SreachNV();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(NVController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        try {
            
            con = DBConncet.DBConnection.pmartConnection();
            data = FXCollections.observableArrayList();
            btnthem.setText("Thêm");
             btnCapNhat.setDisable(true);
             btnXoa.setDisable(true);
             btnCapNhat.setText("Cập Nhật");
            setCellTable();
            LoadData();
         SreachNV();
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

    private  void LoadData() throws SQLException { 
       
        txtDN.setDisable(true);
        txtHoTen.setDisable(true);
        txtMK.setDisable(true);
        txtPreMK.setDisable(true);
        txtDN.setDisable(true);
        cboQuyen.setDisable(true);
        
       //  cboQuyen.setItems(list);
       cboQuyen.setItems(list);
        
        
         //tblNhanVien.setEditable(false);
        data.clear();
        pst = con.prepareStatement("select * from NhanVien");
        rs = pst.executeQuery();

        while (rs.next()) {
            data.add(new NhanVien(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
        }
        tblNhanVien.setItems(data);
            tblNhanVien.getSelectionModel().clearAndSelect(0);   
            NhanVien nv= tblNhanVien.getItems().get(tblNhanVien.getSelectionModel().getSelectedIndex());
            txtHoTen.setText(nv.getHoTen());
            dpkNgaySinh.setValue(LocalDate.parse(nv.getNgaySinh()));
            txtDN.setText(nv.getTenDN());
            cboQuyen.setValue(nv.getQuyen());
    }
   
    private void setCellValueTable()
    {
        tblNhanVien.setOnMouseClicked((MouseEvent event) -> {
         //  NhanVien nv=tblNhanVien.getItems().get(0);
              NhanVien nv= tblNhanVien.getItems().get(tblNhanVien.getSelectionModel().getSelectedIndex());
            Postion=nv.getMaNV();
            txtHoTen.setText(nv.getHoTen());
            dpkNgaySinh.setValue(LocalDate.parse(nv.getNgaySinh()));
            txtDN.setText(nv.getTenDN());
            cboQuyen.setValue(nv.getQuyen());
            btnCapNhat.setDisable(false);
            btnXoa.setDisable(false);
        });
    }

   private void SreachNV()
   {
       //data.clear();
       FilteredList<NhanVien> filtereddata=new FilteredList<>(data,e->true);
       txtSreach.setOnKeyReleased(e->{
       txtSreach.textProperty().addListener((ObservableValue, oldValue,newValue)->{
           filtereddata.setPredicate((Predicate<?super NhanVien>) nv->{
           if(newValue==null||newValue.isEmpty())
           {
               return true;
           }
             String   lowerCaseFilter=newValue.toLowerCase();
             if(nv.getHoTen().contains(newValue))
             {
                 return true;
             }
             int a=nv.getMaNV();
            
            if( Integer.toString(a).contains(newValue))
              
             {
                 return true;
             }
             if( nv.getTenDN().contains(newValue))   
             {
                 return true;
             }        
               return false;
           });
           });
           
       });
       SortedList<NhanVien> sorted=new SortedList<>(filtereddata);
       sorted.comparatorProperty().bind(tblNhanVien.comparatorProperty());
       tblNhanVien.setItems(sorted);
   }
 private boolean ValidateName()
          
  {
      String USERNAME_PATTERN =  "^[a-zA-Z\\sàáạãđâậấầẩẫ_-]{3,25}$";
      Pattern p = Pattern.compile(USERNAME_PATTERN);
      Matcher m=p.matcher(txtHoTen.getText());
      if(m.find()&& m.group().equals(txtHoTen.getText())){
          return true;
      }
      else{
          Alert alert =new Alert (AlertType.WARNING);
          alert.setTitle("Tên Không Hợp Lệ ");
          alert.setHeaderText(null);
          alert.setContentText("Tên Không Hợp Lê");
          alert.showAndWait();
          return false;
      }
      
  }
 private boolean ValidateUserName()
          
  {
      String USERNAME_PATTERN =  "^[a-zA-Z]+[0-9]{3,25}$";
      Pattern p = Pattern.compile(USERNAME_PATTERN);
      Matcher m=p.matcher(txtDN.getText());
      if(m.find()&& m.group().equals(txtDN.getText())){
          return true;
      }
      else{
          Alert alert =new Alert (AlertType.WARNING);
          alert.setTitle("Tên DN Không Hợp Lệ ");
          alert.setHeaderText(null);
          alert.setContentText("Tên DN Không Hợp Lê");
          alert.showAndWait();
          return false;
      }
      
  }
 private boolean ValidateEmpty()
 {
     if(txtHoTen.getText().isEmpty()||txtDN.getText().isEmpty()||txtMK.getText().isEmpty()||txtPreMK.getText().isEmpty())
     {
         Alert alert =new Alert (AlertType.WARNING);
          alert.setTitle("Thieu thong tin");
          alert.setHeaderText(null);
          alert.setContentText("Thieu thong tin");
          alert.showAndWait();
          return false;
     }
     return true;
 }
}
