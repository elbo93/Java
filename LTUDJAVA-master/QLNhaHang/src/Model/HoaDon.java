/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.util.Date;
import java.util.Calendar;

/**
 *
 * @author Bo
 */
public class HoaDon {

    /**
     * @return the HoTen
     */
    public String getHoTen() {
        return HoTen;
    }

    /**
     * @param HoTen the HoTen to set
     */
    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }
    private  int  SoHD;
    private Date ThoiGianLap;
    private int MaSoBan;
    private int SoKhach;
    private int MaNVlap;
    private float TongTien;
    private String HoTen;
    public HoaDon(int SoHD,Date ThoiGianLap,int MaSoBan,int SoKhach,int MaNVLap,float TongTien,String HoTen)
    {
        this.SoHD=SoHD;
        this.ThoiGianLap=ThoiGianLap;
        this.MaSoBan=MaSoBan;
        this.SoKhach=SoKhach;
        this.MaNVlap=MaNVLap;
        this.TongTien=TongTien;
        this.HoTen=HoTen;
        
    }


    /**
     * @return the SoHD
     */
    public int getSoHD() {
        return SoHD;
    }

    /**
     * @param SoHD the SoHD to set
     */
    public void setSoHD(int SoHD) {
        this.SoHD = SoHD;
    }

    /**
     * @return the ThoiGianLap
     */
    public Date getThoiGianLap() {
        return ThoiGianLap;
    }

    /**
     * @param ThoiGianLap the ThoiGianLap to set
     */
    public void setThoiGianLap(Date ThoiGianLap) {
        this.ThoiGianLap = ThoiGianLap;
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

    /**
     * @return the SoKhach
     */
    public int getSoKhach() {
        return SoKhach;
    }

    /**
     * @param SoKhach the SoKhach to set
     */
    public void setSoKhach(int SoKhach) {
        this.SoKhach = SoKhach;
    }

    /**
     * @return the MaNVlap
     */
    public int getMaNVlap() {
        return MaNVlap;
    }

    /**
     * @param MaNVlap the MaNVlap to set
     */
    public void setMaNVlap(int MaNVlap) {
        this.MaNVlap = MaNVlap;
    }

    /**
     * @return the TongTien
     */
    public float getTongTien() {
        return TongTien;
    }

    /**
     * @param TongTien the TongTien to set
     */
    public void setTongTien(float TongTien) {
        this.TongTien = TongTien;
    }
   
}
