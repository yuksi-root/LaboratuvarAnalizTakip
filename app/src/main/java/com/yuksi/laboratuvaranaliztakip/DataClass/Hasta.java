package com.yuksi.laboratuvaranaliztakip.DataClass;

public class Hasta {

    private int hastaId;
    private String takipNo;
    private String tcNo;
    private String hastaAdi;
    private String hastaSoyadi;
    private String yas;
    private String cinsiyet;
    private String tarih;
    private String durum;

    public Hasta(int id, String tarih, String takipNo, String hasta, String durum) {
        this.hastaId = id;
        this.tarih = tarih;
        this.takipNo = takipNo;
        this.hastaAdi = hasta;
        this.durum = durum;
    }

    public Hasta(String takipNo, String tcNo, String hastaAdi, String hastaSoyadi, String yas, String cinsiyet, String durum) {
        this.takipNo = takipNo;
        this.tcNo = tcNo;
        this.hastaAdi = hastaAdi;
        this.hastaSoyadi = hastaSoyadi;
        this.yas = yas;
        this.cinsiyet = cinsiyet;
        this.durum = durum;
    }

    public int getHastaId() {
        return hastaId;
    }

    public void setHastaId(int hastaId) {
        this.hastaId = hastaId;
    }

    public String getTakipNo() {
        return takipNo;
    }

    public void setTakipNo(String takipNo) {
        this.takipNo = takipNo;
    }

    public String getTcNo() {
        return tcNo;
    }

    public void setTcNo(String tcNo) {
        this.tcNo = tcNo;
    }

    public String getHastaAdi() {
        return hastaAdi;
    }

    public void setHastaAdi(String hastaAdi) {
        this.hastaAdi = hastaAdi;
    }

    public String getHastaSoyadi() {
        return hastaSoyadi;
    }

    public void setHastaSoyadi(String hastaSoyadi) {
        this.hastaSoyadi = hastaSoyadi;
    }

    public String getYas() {
        return yas;
    }

    public void setYas(String yas) {
        this.yas = yas;
    }

    public String getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getDurum() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }
}
