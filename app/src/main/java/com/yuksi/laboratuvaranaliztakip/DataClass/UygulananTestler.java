package com.yuksi.laboratuvaranaliztakip.DataClass;

public class UygulananTestler {
    private int testId;
    private int hastaId;
    private String uygulanacakTest;
    private String calismaGunleri;
    private String calismaSuresi;
    private String aciklama;
    private String durum;

    public UygulananTestler(int id, String testAd, String cGun, String cSure, String aciklama) {
        this.testId = id;
        this.uygulanacakTest = testAd;
        this.calismaGunleri = cGun;
        this.calismaSuresi = cSure;
        this.aciklama = aciklama;
    }

    public UygulananTestler(String uygulanacakTest, String cGun, String cSure, String aciklama) {
        this.uygulanacakTest = uygulanacakTest;
        this.calismaGunleri = cGun;
        this.calismaSuresi = cSure;
        this.aciklama = aciklama;
    }

    public UygulananTestler(String uygulanacakTest, String calismaGunleri, String calismaSuresi, String aciklama, String durum) {
        this.uygulanacakTest = uygulanacakTest;
        this.calismaGunleri = calismaGunleri;
        this.calismaSuresi = calismaSuresi;
        this.aciklama = aciklama;
        this.durum = durum;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getHastaId() {
        return hastaId;
    }

    public void setHastaId(int hastaId) {
        this.hastaId = hastaId;
    }

    public String getUygulanacakTest() {
        return uygulanacakTest;
    }

    public void setUygulanacakTest(String uygulanacakTest) {
        this.uygulanacakTest = uygulanacakTest;
    }

    public String getCalismaGunleri() {
        return calismaGunleri;
    }

    public void setCalismaGunleri(String calismaGunleri) {
        this.calismaGunleri = calismaGunleri;
    }

    public String getCalismaSuresi() {
        return calismaSuresi;
    }

    public void setCalismaSuresi(String calismaSuresi) {
        this.calismaSuresi = calismaSuresi;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getDurum() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }
}
