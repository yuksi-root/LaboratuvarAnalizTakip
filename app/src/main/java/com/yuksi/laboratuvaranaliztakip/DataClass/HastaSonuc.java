package com.yuksi.laboratuvaranaliztakip.DataClass;

public class HastaSonuc {
    private int hastaSonucId;
    private String takipNo;
    private String testAdi;
    private String sonuc;
    private String birim;
    private String referansAraligi;

    public HastaSonuc(int id, String testAdi, String sonuc, String birim, String ref) {
        this.hastaSonucId = id;
        this.testAdi = testAdi;
        this.sonuc = sonuc;
        this.birim = birim;
        this.referansAraligi = ref;
    }

    public HastaSonuc(String testAdi, String sonuc, String birim, String referansAraligi, String takipNo) {
        this.testAdi = testAdi;
        this.sonuc = sonuc;
        this.birim = birim;
        this.referansAraligi = referansAraligi;
        this.takipNo = takipNo;
    }

    public int getHastaSonucId() {
        return hastaSonucId;
    }

    public void setHastaSonucId(int hastaSonucId) {
        this.hastaSonucId = hastaSonucId;
    }

    public String getTakipNo() {
        return takipNo;
    }

    public void setTakipNo(String takipNo) {
        this.takipNo = takipNo;
    }

    public String getTestAdi() {
        return testAdi;
    }

    public void setTestAdi(String testAdi) {
        this.testAdi = testAdi;
    }

    public String getSonuc() {
        return sonuc;
    }

    public void setSonuc(String sonuc) {
        this.sonuc = sonuc;
    }

    public String getBirim() {
        return birim;
    }

    public void setBirim(String birim) {
        this.birim = birim;
    }

    public String getReferansAraligi() {
        return referansAraligi;
    }

    public void setReferansAraligi(String referansAraligi) {
        this.referansAraligi = referansAraligi;
    }
}
