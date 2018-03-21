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
public class MonAn {
    private int  MaLoai;
    private String Nhom;
    private String TenLoai;
    public MonAn (int MaLoai,String Nhom,String TenLoai)
    {
        this.MaLoai=MaLoai;
        this.Nhom=Nhom;
        this.TenLoai=TenLoai;
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
     * @return the Nhom
     */
    public String getNhom() {
        return Nhom;
    }

    /**
     * @param Nhom the Nhom to set
     */
    public void setNhom(String Nhom) {
        this.Nhom = Nhom;
    }

    /**
     * @return the TenLoai
     */
    public String getTenLoai() {
        return TenLoai;
    }

    /**
     * @param TenLoai the TenLoai to set
     */
    public void setTenLoai(String TenLoai) {
        this.TenLoai = TenLoai;
    }
}
