/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlnhahang;

import Model.GoiMon;
import Model.Menu;
import static java.awt.SystemColor.text;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

  
public class OderController implements Initializable { 
    @FXML
    private ComboBox<String> cboBan;
    @FXML
    private TextField txtSoLuong;
   @FXML
    private TableColumn<?, ?> ColDonGia;

    @FXML
    private TableColumn<?, ?> ColSoLuong;
      @FXML
    private TableColumn<?, ?> colTenThucDon;
     @FXML
    private TableView<GoiMon> tblMenu;
    @FXML
    private TableColumn<?, ?> ColMTD;
     @FXML
    private TextField txtGia;
     
    @FXML
    
    private ComboBox<String> colTenLoai;

    @FXML
    private ListView<String> listthucdon;
      private Connection con = null;
    private ResultSet rs = null;
    ObservableList<String> list =FXCollections.observableArrayList();
     ObservableList<String> listmonan =FXCollections.observableArrayList();
      ObservableList<String> listbanan =FXCollections.observableArrayList();
      ObservableList<String> listgia =FXCollections.observableArrayList();
   //    ObservableList<String> listadd =FXCollections.observableArrayList("4","Goi Cuon","5000","1");
    private PreparedStatement pst = null;
    private  ObservableList<GoiMon> data=FXCollections.observableArrayList();
   int Gia;
      @FXML
    private Button btntest;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    try {
        con = DBConncet.DBConnection.pmartConnection();
        LoadCombobox();
        LoadComboboxBan();
        GetGia();
       
       
    } catch (SQLException ex) {
        Logger.getLogger(OderController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }   
    private void LoadCombobox() throws SQLException
    {
       
        pst = con.prepareStatement("select TenLoai  from LoaiThucDon");
        rs = pst.executeQuery();
            
        while (rs.next()) {
           list.add(rs.getString("TenLoai"));
        }
        colTenLoai.setItems(list);
       
    }
     private void LoadComboboxBan() throws SQLException
    {
       
        pst = con.prepareStatement("select MaSoBan  from BanAn");
        rs = pst.executeQuery();
            
        while (rs.next()) {
           listbanan.add(rs.getString("MaSoBan"));
        }
        cboBan.setItems(listbanan);
        
       
    }
     private void GetGia(){
       listthucdon.setOnMouseClicked((MouseEvent event) -> {
         String t= listthucdon.getSelectionModel().getSelectedItem();   
           try {
               pst = con.prepareStatement("select  Gia From Gia g, ThucDon t where g.MaThucDon =t.MaThucDon and t.TenThucDon LIKE N'"+t+"'");
                rs = pst.executeQuery();
              
             while (rs.next()) {               
                String gia=String.valueOf(rs.getInt("Gia"));
                txtGia.setText(gia);   
                Gia=rs.getInt("Gia");
           
        }
           } catch (SQLException ex) {
               Logger.getLogger(OderController.class.getName()).log(Level.SEVERE, null, ex);
           }  
           
     });
    }
      private void setCellTable()
    {
         ColMTD.setCellValueFactory(new PropertyValueFactory<>("MaThucDon"));
        colTenThucDon.setCellValueFactory(new PropertyValueFactory<>("ThucDon"));
         ColDonGia.setCellValueFactory(new PropertyValueFactory<>("DonGia"));
         ColSoLuong.setCellValueFactory(new PropertyValueFactory<>("SoLuong"));
           
    }
    @FXML
    void cobTenLoaiAction(ActionEvent event) throws SQLException {
      listthucdon.getItems().clear();
        String text=colTenLoai.getValue();
        pst = con.prepareStatement("select * from LoaiThucDon l , ThucDon t where l.MaLoai=t.MaLoai and l.TenLoai LIKE N'"+text+"'");
        rs = pst.executeQuery();

        while (rs.next()) {
            listmonan.add(rs.getString("TenThucDon"));
        }
       // System.out.print(listmonan);
        listthucdon.setItems(listmonan);
        
    }
    @FXML
    void btnthemAction(ActionEvent event) throws SQLException {
        String monan=listthucdon.getSelectionModel().getSelectedItem();
        System.out.print(monan);
        System.out.print(Gia);
    data.add(new GoiMon(1,monan,Gia,Integer.parseInt(txtSoLuong.getText())));
          tblMenu.setItems(data);
        setCellTable();
       
    }
  
  @FXML
    void btnDeleteRowAvtion(ActionEvent event) {
        GoiMon gm=tblMenu.getSelectionModel().getSelectedItem();
        tblMenu.getItems().remove(gm);
   }

    @FXML
    void btndeleteDSAction(ActionEvent event) {
        tblMenu.getItems().clear();
    }
    

    
}
