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
public class CTHoaDon {
    private int SoHD;
    private int MaThucDon;
    private int SoLuong;
    private float DonGia;
    private String TenThucDon;
    public CTHoaDon(int SoHD, int MaThucDon,int SoLuong,float DonGia,String TenThucDon)
    {
        this.SoHD=SoHD;
        this.MaThucDon=MaThucDon;
        this.SoLuong=SoLuong;
        this.DonGia=DonGia;
        this.TenThucDon=TenThucDon;
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
     * @return the DonGia
     */
    public float getDonGia() {
        return DonGia;
    }

    /**
     * @param DonGia the DonGia to set
     */
    public void setDonGia(float DonGia) {
        this.DonGia = DonGia;
    }

    /**
     * @return the TenThucDon
     */
    public String getTenThucDon() {
        return TenThucDon;
    }

    /**
     * @param TenThucDon the TenThucDon to set
     */
    public void setTenThucDon(String TenThucDon) {
        this.TenThucDon = TenThucDon;
    }

   
}
