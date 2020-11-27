package com.pbp.gd11_c_9847.ui.download;

public class DataDownload {
    private String nama;
    private String ext;
    private String url;
    private  float besar;

    public DataDownload(String nama, String ext, String url, float besar) {
        this.nama = nama;
        this.ext = ext;
        this.url = url;
        this.besar = besar;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public float getBesar() {
        return besar;
    }

    public void setBesar(float besar) {
        this.besar = besar;
    }
}
