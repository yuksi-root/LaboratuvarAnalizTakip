package com.yuksi.laboratuvaranaliztakip.DataClass;

public class Kullanici {

    private int kullaniciId;
    private String kullaniciAdi;
    private String firmaKodu;
    private String sifre;
    private String firmaAdi;
    private String yetkiliAdi;
    private String yetkiliSoyadi;
    private String yetkisi;

    public Kullanici(String kullaniciAdi, String firmaKodu, String sifre, String firmaAdi, String yetkiliAdi, String yetkiliSoyadi, String yetkisi) {
        this.kullaniciAdi = kullaniciAdi;
        this.firmaKodu = firmaKodu;
        this.sifre = sifre;
        this.firmaAdi = firmaAdi;
        this.yetkiliAdi = yetkiliAdi;
        this.yetkiliSoyadi = yetkiliSoyadi;
        this.yetkisi = yetkisi;
    }

    public Kullanici(int id, String kullaniciAdi, String firmaKodu, String sifre, String firmaAdi, String yetkiliAdi, String yetkiliSoyadi, String yetkisi) {
        this.kullaniciId = id;
        this.kullaniciAdi = kullaniciAdi;
        this.firmaKodu = firmaKodu;
        this.sifre = sifre;
        this.firmaAdi = firmaAdi;
        this.yetkiliAdi = yetkiliAdi;
        this.yetkiliSoyadi = yetkiliSoyadi;
        this.yetkisi = yetkisi;
    }

    public int getKullaniciId() {
        return kullaniciId;
    }

    public void setKullaniciId(int kullaniciId) {
        this.kullaniciId = kullaniciId;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getSifre() {
        return sifre;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getFirmaAdi() {
        return firmaAdi;
    }

    public void setFirmaAdi(String firmaAdi) {
        this.firmaAdi = firmaAdi;
    }

    public String getFirmaKodu() {
        return firmaKodu;
    }

    public void setFirmaKodu(String firmaKodu) {
        this.firmaKodu = firmaKodu;
    }

    public String getYetkiliAdi() {
        return yetkiliAdi;
    }

    public void setYetkiliAdi(String yetkiliAdi) {
        this.yetkiliAdi = yetkiliAdi;
    }

    public String getYetkiliSoyadi() {
        return yetkiliSoyadi;
    }

    public void setYetkiliSoyadi(String yetkiliSoyadi) {
        this.yetkiliSoyadi = yetkiliSoyadi;
    }

    public String getYetkisi() {
        return yetkisi;
    }

    public void setYetkisi(String yetkisi) {
        this.yetkisi = yetkisi;
    }
}
