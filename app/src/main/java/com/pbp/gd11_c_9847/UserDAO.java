package com.pbp.gd11_c_9847;

import com.google.gson.annotations.SerializedName;

public class UserDAO {
    @SerializedName ( "idBuku" )
    private String id;

    @SerializedName ( "namaBuku" )
    private String nama;

    @SerializedName ( "pengarang" )
    private String pengarang;

    @SerializedName ( "gambar" )
    private String gambar;

    @SerializedName ( "harga" )
    private Double harga;

    public UserDAO(String id, String nama, String pengarang, String gambar, Double harga) {
        this.id = id;
        this.nama = nama;
        this.pengarang = pengarang;
        this.gambar = gambar;
        this.harga = harga;
    }

    public UserDAO(String nama, String pengarang, String gambar, Double harga) {
        this.nama = nama;
        this.pengarang = pengarang;
        this.gambar = gambar;
        this.harga = harga;
    }

    public UserDAO(String nama, String pengarang, Double harga) {
        this.nama = nama;
        this.pengarang = pengarang;
        this.harga = harga;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPengarang() {
        return pengarang;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public Double getHarga() {
        return harga;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
    }
}
