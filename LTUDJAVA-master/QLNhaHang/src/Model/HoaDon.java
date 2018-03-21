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
        return SoHD;
    }
    public Date getThoiGianLap(){
        return ThoiGianLap;
    }
    public int getMaSoBan(){
        return MaSoBan;
    }
    public int getSoKhach(){
        return SoKhach;
    }
    public int getMaNVLap(){
        return MaNVlap;
    }   
    public int getMaNVTT(){
        return MaNVTT;
    }
    public float getTongTien(){
        return TongTien;
    }
    public void setSoHD(int SoHD){
        this.SoHD=SoHD;
    }
    public void setThoiGianLap(Date ThoiGianLap){
        this.ThoiGianLap=ThoiGianLap;
    }
     public void setMaSoBan(int MaSoBan){
        this.MaSoBan=MaSoBan;
    }
    public void setSoKhach(int SoKhach){
        this.SoKhach=SoKhach;
    }
    public void setMaVNLap(int MaNVLap){
        this.MaNVlap=MaNVLap;
    }
    public void setMaNVTT(int MaNVTT){
        this.MaNVTT=MaNVTT;
    }
    public void setTongTien(float TongTien){
        this.TongTien=TongTien;
    }
    
}
