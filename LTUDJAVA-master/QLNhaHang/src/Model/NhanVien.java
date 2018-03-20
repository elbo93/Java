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
public class NhanVien {
  private int MaNV;
  private String HoTen;
  private String NgaySinh;
  private String TenDN;
  private String MatKhau;
  private String Quyen;
  public   NhanVien(int MaNV,String HoTen,String NgaySinh,String TenDN,String MatKhau,String Quyen)
  {
      this.MaNV=MaNV;
      this.HoTen=HoTen;
      this.NgaySinh=NgaySinh;
      this.TenDN=TenDN;
      this.MatKhau=MatKhau;
      this.Quyen=Quyen;
  }
    /**
     * @return the MaNV
     */
    public int getMaNV() {
        return MaNV;
    }

    /**
     * @param MaNV the MaNV to set
     */
    public void setMaNV(int MaNV) {
        this.MaNV = MaNV;
    }

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

    /**
     * @return the NgaySinh
     */
    public String getNgaySinh() {
        return NgaySinh;
    }

    /**
     * @param NgaySinh the NgaySinh to set
     */
    public void setNgaySinh(String NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    /**
     * @return the TenDN
     */
    public String getTenDN() {
        return TenDN;
    }

    /**
     * @param TenDN the TenDN to set
     */
    public void setTenDN(String TenDN) {
        this.TenDN = TenDN;
    }

    /**
     * @return the Quyen
     */
    public String getQuyen() {
        return Quyen;
    }

    /**
     * @param Quyen the Quyen to set
     */
    public void setQuyen(String Quyen) {
        this.Quyen = Quyen;
    }
}
