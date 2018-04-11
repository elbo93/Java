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
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
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
    private TextField txtTK;
    @FXML
    private TableColumn<?, ?> ColNhom;

    @FXML
    private TableColumn<?, ?> ColTenNhom;
   
    @FXML
    private Button btnXoa;
    @FXML
    private TextField txtTimkiem;
     @FXML
    private ComboBox<String> cbo;
     @FXML
    private TableView<MonAn> tblMonAn;

    /**
     * Initializes the controller class.
     */
       ObservableList<String>Item;
     private Connection con = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private PreparedStatement pstid = null;
    private ObservableList<MonAn> data;
    private int Postion;
    private int id;

    public MonAnController() {
        this.Item = FXCollections.observableArrayList("Thức Ăn","Nước Uống");
    }

   
   
//  --------------------------------------------------------------------------------
//    -----------------------------------------------------------------------------
      @FXML
    void btnThemAction(ActionEvent event) {
        String sql = "Insert into LoaiThucDon(MaLoai,Nhom,TenLoai)Values(?,?,?)";
        String Nhom = cbo.getValue();
        String TenLoai = txtTenLoai.getText();
        String text = btnThem.getText();
        String t = "Thêm";
        String t2 = "Lưu";
        if (text.equals(t)) {
            btnThem.setText("Lưu");
            cbo.setDisable(false);
            txtTenLoai.clear();
            txtTenLoai.setDisable(false);
        } else {
            if (text.equals(t2)) {
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
                    pst.setString(3, TenLoai);
                    int i = pst.executeUpdate();
                    if (i == 1) {
                        AlertDialog.display("Infor", "Them Thanh Cong");
                        btnThem.setText("Thêm");
                    } else {
                        AlertDialog.display("Infor", "Them That Bai");
                         btnThem.setText("Thêm");
                    }
                    setCellTable();
                    LoadData();
                    Sreach();
                } catch (SQLException ex) {
                    Logger.getLogger(NVController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
  @FXML
    void btnSuaAction(ActionEvent event) {
        try {
            String text = btnSua.getText();
            String t = "Cập Nhật";
            String t2 = "Lưu";
            if (text.equals(t)) {
                txtTenLoai.setDisable(false);
                cbo.setDisable(false);
                btnSua.setText("Lưu");
            } else {
                if (text.equals(t2)) {
                    setCellValueTable();
                    String sql = "update LoaiThucDon set Nhom=?,TenLoai =? where MaLoai=?";
                    String Nhom = cbo.getValue();
                    String TenLoai = txtTenLoai.getText();
                    pst = con.prepareStatement(sql);
                    pst.setString(1, Nhom);
                    pst.setString(2, TenLoai);
                    pst.setInt(3, Postion);
                    int i = pst.executeUpdate();
                    if (i == 1) {
                        // System.out.print("Cap nhat Thanh Cong");
                        AlertDialog.display("Infor", "Cap Nhat Thanh Cong");
                       btnSua.setText("Cập Nhật");
                       btnSua.setDisable(true);
                       btnXoa.setDisable(true);
                    } else {
                        AlertDialog.display("Infor", "Cap Nhat That Bai");
                        btnSua.setText("Cập Nhật");
                         btnSua.setDisable(true);
                        btnXoa.setDisable(true);
                    }
                    setCellTable();
                    LoadData();
                    Sreach();
                }
            }
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
                      btnXoa.setDisable(true);
                  } else {
                      AlertDialog.display("Infor", "Xoa That Bai");
                       btnXoa.setDisable(true);
                  }
                  setCellTable();
                  LoadData();
                    Sreach();
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
           Sreach();
           btnThem.setText("Thêm");
           btnSua.setText("Cập Nhật");
           cbo.setDisable(true);
           btnSua.setDisable(true);
           btnXoa.setDisable(true);
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
       txtTenLoai.setDisable(true);
       cbo.setDisable(true);
         cbo.setItems(Item);
        cbo.setValue("Thức Ăn");
         data.clear();
        pst = con.prepareStatement("select * from LoaiThucDon");
        rs = pst.executeQuery();

        while (rs.next()) {
            data.add(new MonAn(rs.getInt(1), rs.getString(2), rs.getString(3)));
        }
        tblMonAn.setItems(data);
          tblMonAn.getSelectionModel().clearAndSelect(0);   
            MonAn nv= tblMonAn.getItems().get(tblMonAn.getSelectionModel().getSelectedIndex());
         // txtNhom.setText(nv.getNhom());
          txtTenLoai.setText(nv.getTenLoai());
          cbo.setValue(nv.getNhom());
   }
     private void setCellValueTable()
    {
        tblMonAn.setOnMouseClicked((MouseEvent event) -> {
            MonAn ma= tblMonAn.getItems().get(tblMonAn.getSelectionModel().getSelectedIndex());
            Postion=ma.getMaLoai();
            cbo.setValue(ma.getNhom());
            txtTenLoai.setText(ma.getTenLoai());
            btnXoa.setDisable(false);
            btnSua.setDisable(false);
        });
       
    }
  private void Sreach()
   {
       FilteredList<MonAn> filtereddata=new FilteredList<>(data,e->true);
       txtTimkiem.setOnKeyReleased(e->{
       txtTimkiem.textProperty().addListener((ObservableValue, oldValue,newValue)->{
           filtereddata.setPredicate((Predicate<?super MonAn>) nv->{
           if(newValue==null||newValue.isEmpty())
           {
               return true;
           }
//             String   lowerCaseFilter=newValue.toLowerCase();
             if(nv.getTenLoai().contains(newValue))
             {
                 return true;
             }
             int a=nv.getMaLoai();
            
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
       SortedList<MonAn> sorted=new SortedList<>(filtereddata);
       sorted.comparatorProperty().bind(tblMonAn.comparatorProperty());
       tblMonAn.setItems(sorted);
   }
  
}
