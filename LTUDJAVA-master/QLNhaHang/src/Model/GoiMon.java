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
    private int MaThucDon;
    private String ThucDon;
    private int DonGia;
    private int SoLuong;
    public GoiMon(int MaThucDon, String ThucDon,int DonGia,int SoLuong)
    {
        this.MaThucDon=MaThucDon;
        this.ThucDon=ThucDon;
        this.SoLuong=SoLuong;
        this.DonGia=DonGia;
    }

    /**
     * @return the MaThucDon
     */
    public int getMaThucDon() {
        return MaThucDon;
    }

    /**
     * @param MaThucDon the MaThucDon to set
     */
    public void setMaThucDon(int MaThucDon) {
        this.MaThucDon = MaThucDon;
    }

    /**
     * @return the ThucDon
     */
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
}
