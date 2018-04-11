/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Dell
 */
public class GoiMon {
    
    private String ThucDon;
    private int DonGia;
    private int SoLuong;
    private int Id;
    private int MaSoBan;
    public GoiMon(int id,int MaSoBan, String ThucDon,int DonGia,int SoLuong)
    {
        this.MaSoBan=MaSoBan;
        this.Id=id;
     
        this.ThucDon=ThucDon;
        this.SoLuong=SoLuong;
        this.DonGia=DonGia;
    }

  
    public String getThucDon() {
        return ThucDon;
    }

    /**
     * @param ThucDon the ThucDon to set
     */
    public void setThucDon(String ThucDon) {
        this.ThucDon = ThucDon;
    }

    /**
     * @return the DonGia
     */
    public int getDonGia() {
        return DonGia;
    }

    /**
     * @param DonGia the DonGia to set
     */
    public void setDonGia(int DonGia) {
        this.DonGia = DonGia;
    }

    /**
     * @return the SoLuong
     */
    public int getSoLuong() {
        return SoLuong;
    }

    /**
     * @param SoLuong the SoLuong to set
     */
    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    /**
     * @return the Id
     */
    public int getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(int Id) {
        this.Id = Id;
    }

    /**
     * @return the MaSoBan
     */
    public int getMaSoBan() {
        return MaSoBan;
    }

    /**
     * @param MaSoBan the MaSoBan to set
     */
    public void setMaSoBan(int MaSoBan) {
        this.MaSoBan = MaSoBan;
    }
}
