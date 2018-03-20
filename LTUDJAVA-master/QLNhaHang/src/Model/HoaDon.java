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
    private int  SoHD;
    private Date ThoiGianLap;
    private int MaSoBan;
    private int SoKhach;
    private int MaNVlap;
    private int MaNVTT;
    private float TongTien;
    public HoaDon(int SoHD,Date ThoiGianLap,int MaSoBan,int SoKhach,int MaNVLap,int MaNVTT,float TongTien)
    {
        this.SoHD=SoHD;
        this.ThoiGianLap=ThoiGianLap;
        this.MaSoBan=MaSoBan;
        this.SoKhach=SoKhach;
        this.MaNVlap=MaNVLap;
        this.MaNVTT=MaNVTT;
        this.TongTien=TongTien;
        
    }
    public int getSoHD(){
        return this.SoHD;
    }
    public Date getThoiGianLap(){
        return this.ThoiGianLap;
    }
    public int getMasoBan(){
        return this.MaSoBan;
    }
    public int getSoKhach(){
        return this.SoKhach;
    }
    public int getMaNVLap(){
        return this.MaNVlap;
    }   
    public int getMaNVTT(){
        return this.MaNVTT;
    }
    public float getTongTien(){
        return this.TongTien;
    }
}
