package com.yuksi.laboratuvaranaliztakip.DataClass;

public class BeklenenSonuc {
    private int id;
    private String testAdi;
    private String hasta;
    private String tarih;
    private String durum;

    public BeklenenSonuc(int id, String testAdi, String hasta, String tarih, String durum) {
        this.id = id;
        this.testAdi = testAdi;
        this.hasta = hasta;
        this.tarih = tarih;
        this.durum = durum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTestAdi() {
        return testAdi;
    }

    public void setTestAdi(String testAdi) {
        this.testAdi = testAdi;
    }

    public String getHasta() {
        return hasta;
    }

    public void setHasta(String hasta) {
        this.hasta = hasta;
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
