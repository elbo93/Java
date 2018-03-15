/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classss;

/**
 *
 * @author Dell
 */
public class Menu {
    private int MaThucDon;
    private int MaLoai;
    private String TenThucDon;
    private String DonViTinh;
    private Float GiaHienHanh;
    public Menu(int MaThucDon,int MaLoai,String TenThucDon,String DonViTinh,Float GiaHienHanh)
    {
        this.MaThucDon=MaThucDon;
        this.MaLoai=MaLoai;
        this.TenThucDon=TenThucDon;
        this.DonViTinh=DonViTinh;
        this.GiaHienHanh=GiaHienHanh;
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
     * @return the MaLoai
     */
    public int getMaLoai() {
        return MaLoai;
    }

    /**
     * @param MaLoai the MaLoai to set
     */
    public void setMaLoai(int MaLoai) {
        this.MaLoai = MaLoai;
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

    /**
     * @return the DonViTinh
     */
    public String getDonViTinh() {
        return DonViTinh;
    }

    /**
     * @param DonViTinh the DonViTinh to set
     */
    public void setDonViTinh(String DonViTinh) {
        this.DonViTinh = DonViTinh;
    }

    /**
     * @return the GiaHienHanh
     */
    public Float getGiaHienHanh() {
        return GiaHienHanh;
    }

    /**
     * @param GiaHienHanh the GiaHienHanh to set
     */
    public void setGiaHienHanh(Float GiaHienHanh) {
        this.GiaHienHanh = GiaHienHanh;
    }
}
