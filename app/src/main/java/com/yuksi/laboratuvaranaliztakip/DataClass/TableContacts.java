package com.yuksi.laboratuvaranaliztakip.DataClass;

import android.provider.BaseColumns;

public class TableContacts {
    public TableContacts() {
    }

    public static final class hastalarTable implements BaseColumns {
        public static final String TABLE_NAME = "hastalar";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_TC_NO = "tcNo";
        public static final String COLUMN_TAKIP_NO = "takipNo";
        public static final String COLUMN_HASTA_ADI = "hastaAdi";
        public static final String COLUMN_HASTA_SOYADI = "hastaSoyadi";
        public static final String COLUMN_YAS = "yas";
        public static final String COLUMN_CINSIYET = "cinsiyet";
        public static final String COLUMN_KAYIT_TARIH = "kayitTarih";
        public static final String COLUMN_DURUM = "durum";
    }

    public static final class kullanicilarTable implements BaseColumns {
        public static final String TABLE_NAME = "kullanicilar";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_KULLANICI_ADI = "kullaniciAdi";
        public static final String COLUMN_FIRMA_KODU = "firmaKodu";
        public static final String COLUMN_SIFRE = "sifre";
        public static final String COLUMN_YETKILI_AD = "yetkiliAdi";
        public static final String COLUMN_YETKILI_SOYAD = "yetkiliSoyad";
        public static final String COLUMN_YETKISI = "yetki";
        public static final String COLUMN_FIRMA_ADI = "firmaAdi";
    }

    public static final class uygulanacakTestler implements BaseColumns {
        public static final String TABLE_NAME = "uygulanacakTestler";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_YAPILACAK_TEST = "yapilacakTest";
        public static final String COLUMN_CALISMA_GUNLER = "calismaGunler";
        public static final String COLUMN_CALISMA_SURESI = "calismaSuresi";
        public static final String COLUMN_ACIKLAMA = "aciklama";
        public static final String COLUMN_TEST_ID = "testId";
        public static final String COLUMN_HASTA_ID = "hastaId";
        public static final String COLUMN_DURUM = "durum";
        public static final String COLUMN_TARIH = "tarih";
    }

    public static final class hastaSonucTable implements BaseColumns {
        public static final String TABLE_NAME = "hastaSonuc";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_TEST_AD = "testAd";
        public static final String COLUMN_TAKIP_NO = "takipNo";
        public static final String COLUMN_SONUC = "sonuc";
        public static final String COLUMN_BIRIM = "birim";
        public static final String COLUMN_REFERANS_ARALIGI = "referansAraligi";
    }

    public static final class beklenenSonuclarTable implements BaseColumns {
        public static final String TABLE_NAME = "beklenenSonucTable";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_TARIH = "testAd";
        public static final String COLUMN_TEST_ADI = "takipNo";
        public static final String COLUMN_HASTA_ADI = "sonuc";
        public static final String COLUMN_DURUM = "birim";
    }
}
