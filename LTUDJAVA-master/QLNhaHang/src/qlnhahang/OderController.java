/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlnhahang;

import DiaLog.AlertDialog;
import Model.GoiMon;
import Model.Menu;
import Model.NhanVien;
import static java.awt.SystemColor.text;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
    private Button btnSua;
    @FXML
    private TextField txtMaThucDon;

    @FXML

    private ComboBox<String> colTenLoai;

    @FXML
    private ListView<String> listthucdon;
    private Connection con = null;
    private ResultSet rs = null;
    ObservableList<String> list = FXCollections.observableArrayList();
    ObservableList<String> listmonan = FXCollections.observableArrayList();
    ObservableList<String> listbanan = FXCollections.observableArrayList();
    ObservableList<String> listgia = FXCollections.observableArrayList();
    int a;
    private int postion;
    //    ObservableList<String> listadd =FXCollections.observableArrayList("4","Goi Cuon","5000","1");
    private PreparedStatement pst = null;
    private PreparedStatement pstid = null;
    private int id;
    private ObservableList<GoiMon> data = FXCollections.observableArrayList();
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
            setCellValueTable();
            txtGia.setDisable(true);

        } catch (SQLException ex) {
            Logger.getLogger(OderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void LoadCombobox() throws SQLException {

        pst = con.prepareStatement("select TenLoai  from LoaiThucDon");
        rs = pst.executeQuery();

        while (rs.next()) {
            list.add(rs.getString("TenLoai"));
        }
        colTenLoai.setItems(list);

    }

    private void LoadComboboxBan() throws SQLException {

        pst = con.prepareStatement("select MaSoBan  from BanAn");
        rs = pst.executeQuery();

        while (rs.next()) {
            listbanan.add(rs.getString("MaSoBan"));
        }
        cboBan.setItems(listbanan);

    }

    private void GetGia() {
        listthucdon.setOnMouseClicked((MouseEvent event) -> {
            String t = listthucdon.getSelectionModel().getSelectedItem();
            try {
                pst = con.prepareStatement("select  Gia From Gia g, ThucDon t where g.MaThucDon =t.MaThucDon and t.TenThucDon LIKE N'" + t + "'");
                rs = pst.executeQuery();

                while (rs.next()) {
                    String gia = String.valueOf(rs.getInt("Gia"));
                    txtGia.setText(gia);
                    Gia = rs.getInt("Gia");

                }
            } catch (SQLException ex) {
                Logger.getLogger(OderController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }

    private void setCellTable() {
        ColMTD.setCellValueFactory(new PropertyValueFactory<>("MaThucDon"));
        colTenThucDon.setCellValueFactory(new PropertyValueFactory<>("ThucDon"));
        ColDonGia.setCellValueFactory(new PropertyValueFactory<>("DonGia"));
        ColSoLuong.setCellValueFactory(new PropertyValueFactory<>("SoLuong"));

    }

    private void LoadData() throws SQLException {
        String msb = cboBan.getValue();
        data.clear();
        pst = con.prepareStatement("select * from GoiMon where MaSoBan=" + msb + "");
        rs = pst.executeQuery();

        while (rs.next()) {
            data.add(new GoiMon(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getInt(5)));
            tblMenu.setItems(data);
        }
    }

    private void setCellValueTable() {
        tblMenu.setOnMouseClicked((MouseEvent event) -> {
            //  NhanVien nv=tblNhanVien.getItems().get(0);
            GoiMon gm = tblMenu.getItems().get(tblMenu.getSelectionModel().getSelectedIndex());
            postion = gm.getId();
          //  txtMaThucDon.setText(String.valueOf(gm.getMaSoBan()));
            txtSoLuong.setText(String.valueOf(gm.getSoLuong()));
        });
    }

    @FXML
    void cobTenLoaiAction(ActionEvent event) throws SQLException {
        listthucdon.getItems().clear();

        String text = colTenLoai.getValue();
        pst = con.prepareStatement("select * from LoaiThucDon l , ThucDon t where l.MaLoai=t.MaLoai and l.TenLoai LIKE N'" + text + "'");
        rs = pst.executeQuery();

        while (rs.next()) {
            listmonan.add(rs.getString("TenThucDon"));
        }
        // System.out.print(listmonan);
        listthucdon.setItems(listmonan);

    }

    @FXML
    void btnthemAction(ActionEvent event) throws SQLException {
         String TenThucDon = listthucdon.getSelectionModel().getSelectedItem();
        pst = con.prepareStatement("select * from ThucDon  where TenThucDon LIKE N'" + TenThucDon + "'");
        rs = pst.executeQuery();

        while (rs.next()) {
            a=Integer.parseInt(rs.getString("MaThucDon"));
        }
        String sql = "Insert into GoiMon(ID,MaSoBan,TenThucDon,DonGia,SoLuong)Values(?,?,?,?,?)";
        String MaSoBan = cboBan.getValue();
       
        int DonGia = Integer.parseInt(txtGia.getText());
        int SoLuong = Integer.parseInt(txtSoLuong.getText());
        try {
            pstid = con.prepareStatement("select top 1 * from GoiMon order by  ID desc;");
            pst = con.prepareStatement(sql);
            rs = pstid.executeQuery();
            int j = 0;
            while (rs.next()) {
                id = rs.getInt("ID") + 1;

            }
            pst.setInt(1, id);
            pst.setString(2, MaSoBan);
            pst.setString(3, TenThucDon);
            pst.setInt(4, DonGia);
            pst.setInt(5, SoLuong);
            int i = pst.executeUpdate();
            if (i == 1) {
                AlertDialog.display("Infor", "Them Thanh Cong");

            } else {
                AlertDialog.display("Infor", "Them That Bai");
            }
            setCellTable();
            LoadData();
        } catch (SQLException ex) {
            Logger.getLogger(NVController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void btnDeleteRowAvtion(ActionEvent event) {

        GoiMon gm = tblMenu.getSelectionModel().getSelectedItem();
        String sql = "delete from GoiMon where Id=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, gm.getId());
            int i = pst.executeUpdate();
            if (i == 1) {
                AlertDialog.display("Infor", "Delete Thành Công");

            } else {
                AlertDialog.display("Infor", "Delete  Thất Bại");

            }
            setCellTable();
            LoadData();
            //   SreachNV();
        } catch (SQLException ex) {
            Logger.getLogger(NVController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void btndeleteDSAction(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

            String MaSoBan = cboBan.getValue();
            String sql = "delete from GoiMon where MaSoBan=?";
            try {
                pst = con.prepareStatement(sql);
                pst.setInt(1, Integer.parseInt(MaSoBan));
                int i = pst.executeUpdate();
                setCellTable();
                LoadData();
            } catch (SQLException ex) {
                Logger.getLogger(NVController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

        }
    }

    @FXML
    void btnSuaAction(ActionEvent event) throws SQLException {
        setCellValueTable();
        GoiMon gm = tblMenu.getSelectionModel().getSelectedItem();
        String sql = "update GoiMon set SoLuong=? where Id=?";
        int SoLuong = Integer.parseInt(txtSoLuong.getText());
        pst = con.prepareStatement(sql);

        pst.setInt(1, SoLuong);
        pst.setInt(2, gm.getId());

        int i = pst.executeUpdate();
        if (i == 1) {
            AlertDialog.display("Infor", "Cập nhật thành công");
        } else {
            AlertDialog.display("Infor", "Cập nhật thất bại");
        }
        setCellTable();
        LoadData();
    }

    @FXML
    void cboBanAction(ActionEvent event) throws SQLException {
        setCellTable();
        LoadData();

    }
    
   
}
