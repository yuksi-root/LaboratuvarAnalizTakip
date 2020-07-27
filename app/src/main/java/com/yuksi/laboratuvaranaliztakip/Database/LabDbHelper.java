package com.yuksi.laboratuvaranaliztakip.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.yuksi.laboratuvaranaliztakip.DataClass.Hasta;
import com.yuksi.laboratuvaranaliztakip.DataClass.Kullanici;
import com.yuksi.laboratuvaranaliztakip.DataClass.BeklenenSonuc;
import com.yuksi.laboratuvaranaliztakip.DataClass.HastaSonuc;
import com.yuksi.laboratuvaranaliztakip.DataClass.TableContacts;
import com.yuksi.laboratuvaranaliztakip.DataClass.UygulananTestler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LabDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "LabAnaliz";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;
    private ContentValues cv;
    private Hasta hastaz;
    private Kullanici k30;
    public static String yetkiz;
    private UygulananTestler u100;

    public LabDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        cv = new ContentValues();
        final String SQL_CREATE_HASTALAR_TABLE = "CREATE TABLE " +
                TableContacts.hastalarTable.TABLE_NAME + " ( " +
                TableContacts.hastalarTable.COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT," +
                TableContacts.hastalarTable.COLUMN_TC_NO + " VARCHAR, " +
                TableContacts.hastalarTable.COLUMN_TAKIP_NO + " VARCHAR, " +
                TableContacts.hastalarTable.COLUMN_HASTA_ADI + " VARCHAR, " +
                TableContacts.hastalarTable.COLUMN_HASTA_SOYADI + " VARCHAR, " +
                TableContacts.hastalarTable.COLUMN_YAS + " VARCHAR, " +
                TableContacts.hastalarTable.COLUMN_CINSIYET + " VARCHAR, " +
                TableContacts.hastalarTable.COLUMN_KAYIT_TARIH + " TEXT, " +
                TableContacts.hastalarTable.COLUMN_DURUM + " TEXT " +
                ")";
        db.execSQL(SQL_CREATE_HASTALAR_TABLE);
        final String SQL_CREATE_UYGULANACAK_TESTLER_TABLE = "CREATE TABLE " +
                TableContacts.uygulanacakTestler.TABLE_NAME + " ( " +
                TableContacts.uygulanacakTestler.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TableContacts.uygulanacakTestler.COLUMN_YAPILACAK_TEST + " VARCHAR, " +
                TableContacts.uygulanacakTestler.COLUMN_HASTA_ID + " VARCHAR, " +
                TableContacts.uygulanacakTestler.COLUMN_TEST_ID + " VARCHAR, " +
                TableContacts.uygulanacakTestler.COLUMN_ACIKLAMA + " VARCHAR, " +
                TableContacts.uygulanacakTestler.COLUMN_CALISMA_GUNLER + " VARCHAR, " +
                TableContacts.uygulanacakTestler.COLUMN_CALISMA_SURESI + " VARCHAR, " +
                TableContacts.uygulanacakTestler.COLUMN_DURUM + " VARCHAR, " +
                TableContacts.uygulanacakTestler.COLUMN_TARIH + " VARCHAR " +
                ")";
        db.execSQL(SQL_CREATE_UYGULANACAK_TESTLER_TABLE);
        final String SQL_CREATE_KULLANICILAR_TABLE = "CREATE TABLE " +
                TableContacts.kullanicilarTable.TABLE_NAME + " ( " +
                TableContacts.kullanicilarTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TableContacts.kullanicilarTable.COLUMN_KULLANICI_ADI + " VARCHAR, " +
                TableContacts.kullanicilarTable.COLUMN_FIRMA_KODU + " VARCHAR, " +
                TableContacts.kullanicilarTable.COLUMN_SIFRE + " VARCHAR, " +
                TableContacts.kullanicilarTable.COLUMN_YETKISI + " VARCHAR, " +
                TableContacts.kullanicilarTable.COLUMN_YETKILI_AD + " VARCHAR, " +
                TableContacts.kullanicilarTable.COLUMN_YETKILI_SOYAD + " VARCHAR, " +
                TableContacts.kullanicilarTable.COLUMN_FIRMA_ADI + " VARCHAR " +
                ")";
        db.execSQL(SQL_CREATE_KULLANICILAR_TABLE);
        final String SQL_CREATE_HASTA_SONUC_TABLE = "CREATE TABLE " +
                TableContacts.hastaSonucTable.TABLE_NAME + " ( " +
                TableContacts.hastaSonucTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TableContacts.hastaSonucTable.COLUMN_TEST_AD + " VARCHAR, " +
                TableContacts.hastaSonucTable.COLUMN_TAKIP_NO + " VARCHAR, " +
                TableContacts.hastaSonucTable.COLUMN_BIRIM + " VARCHAR, " +
                TableContacts.hastaSonucTable.COLUMN_REFERANS_ARALIGI + " VARCHAR, " +
                TableContacts.hastaSonucTable.COLUMN_SONUC + " VARCHAR " +
                ")";
        db.execSQL(SQL_CREATE_HASTA_SONUC_TABLE);
        final String SQL_CREATE_BEKLENEN_SONUC_TABLE = "CREATE TABLE " +
                TableContacts.beklenenSonuclarTable.TABLE_NAME + " ( " +
                TableContacts.beklenenSonuclarTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                TableContacts.beklenenSonuclarTable.COLUMN_TARIH + " VARCHAR, " +
                TableContacts.beklenenSonuclarTable.COLUMN_TEST_ADI + " VARCHAR, " +
                TableContacts.beklenenSonuclarTable.COLUMN_HASTA_ADI + " VARCHAR, " +
                TableContacts.beklenenSonuclarTable.COLUMN_DURUM + " VARCHAR " +
                ")";
        db.execSQL(SQL_CREATE_BEKLENEN_SONUC_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS kullanicilar");
        onCreate(db);
    }

    private String getNow() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
        simpleDateFormat.applyLocalizedPattern(" d MMM yyyy HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    private int randoms() {
        int rndzx = (int) (Math.random() * 30 + 1);
        return rndzx;
    }

    public boolean addHasta(Hasta hasta) {
        try {
            db = this.getWritableDatabase();
            cv = new ContentValues();
            cv.put(TableContacts.hastalarTable.COLUMN_TC_NO, hasta.getTcNo());
            cv.put(TableContacts.hastalarTable.COLUMN_TAKIP_NO, hasta.getTakipNo());
            cv.put(TableContacts.hastalarTable.COLUMN_HASTA_ADI, hasta.getHastaAdi());
            cv.put(TableContacts.hastalarTable.COLUMN_HASTA_SOYADI, hasta.getHastaSoyadi());
            cv.put(TableContacts.hastalarTable.COLUMN_YAS, hasta.getYas());
            cv.put(TableContacts.hastalarTable.COLUMN_CINSIYET, hasta.getCinsiyet());
            cv.put(TableContacts.hastalarTable.COLUMN_KAYIT_TARIH, getNow());
            cv.put(TableContacts.hastalarTable.COLUMN_DURUM, hasta.getDurum());
            db.insert(TableContacts.hastalarTable.TABLE_NAME, null, cv);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("HASTA EKLEME HATASI sql addHasta");
            return false;
        }
        return true;
    }

    public boolean updateHasta(Hasta hasta, int hsId) {
        try {
            db = this.getWritableDatabase();
            cv = new ContentValues();
            cv.put(TableContacts.hastalarTable.COLUMN_TC_NO, hasta.getTcNo());
            cv.put(TableContacts.hastalarTable.COLUMN_TAKIP_NO, hasta.getTakipNo());
            cv.put(TableContacts.hastalarTable.COLUMN_HASTA_ADI, hasta.getHastaAdi());
            cv.put(TableContacts.hastalarTable.COLUMN_HASTA_SOYADI, hasta.getHastaSoyadi());
            cv.put(TableContacts.hastalarTable.COLUMN_YAS, hasta.getYas());
            cv.put(TableContacts.hastalarTable.COLUMN_CINSIYET, hasta.getCinsiyet());
            cv.put(TableContacts.hastalarTable.COLUMN_KAYIT_TARIH, getNow());//randoms() + "-03-2020"
            cv.put(TableContacts.hastalarTable.COLUMN_DURUM, hasta.getDurum());
            db.update(TableContacts.hastalarTable.TABLE_NAME, cv, " id = ?", new String[]{String.valueOf(hsId)});
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("HASTA GUNCELLEME HATASI sql addHasta");
            return false;
        }
        return true;
    }

    public boolean deleteHasta(int hsId) {
        try {
            db = this.getReadableDatabase();
            db.execSQL("DELETE FROM hastalar WHERE id=?", new String[]{String.valueOf(hsId)});

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Hasta Silme hatası");
            return false;
        }
        return true;
    }

    public boolean deleteKullanici(int kId) {
        try {
            db = this.getReadableDatabase();
            db.execSQL("DELETE FROM kullanicilar WHERE id=?", new String[]{String.valueOf(kId)});

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Kullanici Silme hatası");
            return false;
        }
        return true;
    }

    public boolean deleteHastaSonuc(int hsoId) {
        try {
            db = this.getReadableDatabase();
            db.execSQL("DELETE FROM hastaSonuc WHERE id=?", new String[]{String.valueOf(hsoId)});

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("HastaSonuc Silme hatası");
            return false;
        }
        return true;
    }

    public boolean deleteTest(int hsId) {
        try {
            db = this.getReadableDatabase();
            db.execSQL("DELETE FROM uygulanacakTestler WHERE id=?", new String[]{String.valueOf(hsId)});

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Test Silme hatası");
            return false;
        }
        return true;
    }

    public boolean addUygulananTest(UygulananTestler uTest) {
        db = this.getWritableDatabase();
        cv = new ContentValues();
        try {
            cv.put(TableContacts.uygulanacakTestler.COLUMN_YAPILACAK_TEST, uTest.getUygulanacakTest());

            cv.put(TableContacts.uygulanacakTestler.COLUMN_CALISMA_GUNLER, uTest.getCalismaGunleri());
            cv.put(TableContacts.uygulanacakTestler.COLUMN_CALISMA_SURESI, uTest.getCalismaSuresi());
            cv.put(TableContacts.uygulanacakTestler.COLUMN_ACIKLAMA, uTest.getAciklama());
            cv.put(TableContacts.uygulanacakTestler.COLUMN_TEST_ID, uTest.getTestId());
            cv.put(TableContacts.uygulanacakTestler.COLUMN_HASTA_ID, uTest.getHastaId());
            cv.put(TableContacts.uygulanacakTestler.COLUMN_DURUM, uTest.getDurum());
            cv.put(TableContacts.uygulanacakTestler.COLUMN_TARIH, getNow());
            db.insert(TableContacts.uygulanacakTestler.TABLE_NAME, null, cv);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("addUygulananTest Hatası");
            return false;
        }
        return true;
    }

    public boolean updateUygulananTest(UygulananTestler uTest, int hsId) {
        db = this.getWritableDatabase();
        cv = new ContentValues();
        try {
            cv.put(TableContacts.uygulanacakTestler.COLUMN_YAPILACAK_TEST, uTest.getUygulanacakTest());
            cv.put(TableContacts.uygulanacakTestler.COLUMN_CALISMA_GUNLER, uTest.getCalismaGunleri());
            cv.put(TableContacts.uygulanacakTestler.COLUMN_CALISMA_SURESI, uTest.getCalismaSuresi());
            cv.put(TableContacts.uygulanacakTestler.COLUMN_ACIKLAMA, uTest.getAciklama());
            cv.put(TableContacts.uygulanacakTestler.COLUMN_TARIH, getNow());
            db.update(TableContacts.uygulanacakTestler.TABLE_NAME, cv, " id = ?", new String[]{String.valueOf(hsId)});
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("addUygulananTest Hatası");
            return false;
        }
        return true;
    }

    public boolean addKullanici(Kullanici kullanici) {
        try {
            db = this.getWritableDatabase();
            cv = new ContentValues();
            cv.put(TableContacts.kullanicilarTable.COLUMN_KULLANICI_ADI, kullanici.getKullaniciAdi());
            cv.put(TableContacts.kullanicilarTable.COLUMN_FIRMA_KODU, kullanici.getFirmaKodu());
            cv.put(TableContacts.kullanicilarTable.COLUMN_SIFRE, kullanici.getSifre());
            cv.put(TableContacts.kullanicilarTable.COLUMN_YETKISI, kullanici.getYetkisi());
            cv.put(TableContacts.kullanicilarTable.COLUMN_YETKILI_AD, kullanici.getYetkiliAdi());
            cv.put(TableContacts.kullanicilarTable.COLUMN_YETKILI_SOYAD, kullanici.getYetkiliSoyadi());
            cv.put(TableContacts.kullanicilarTable.COLUMN_FIRMA_ADI, kullanici.getFirmaAdi());
            db.insert(TableContacts.kullanicilarTable.TABLE_NAME, null, cv);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Kullanici Ekleme hatası sql addKullanici");
            return false;
        }
        return true;
    }

    public boolean updateKullanici(Kullanici kullanici, int kId) {
        try {
            db = this.getWritableDatabase();
            cv = new ContentValues();
            cv.put(TableContacts.kullanicilarTable.COLUMN_KULLANICI_ADI, kullanici.getKullaniciAdi());
            cv.put(TableContacts.kullanicilarTable.COLUMN_FIRMA_KODU, kullanici.getFirmaKodu());
            cv.put(TableContacts.kullanicilarTable.COLUMN_SIFRE, kullanici.getSifre());
            cv.put(TableContacts.kullanicilarTable.COLUMN_YETKISI, kullanici.getYetkisi());
            cv.put(TableContacts.kullanicilarTable.COLUMN_YETKILI_AD, kullanici.getYetkiliAdi());
            cv.put(TableContacts.kullanicilarTable.COLUMN_YETKILI_SOYAD, kullanici.getYetkiliSoyadi());
            cv.put(TableContacts.kullanicilarTable.COLUMN_FIRMA_ADI, kullanici.getFirmaAdi());
            db.update(TableContacts.kullanicilarTable.TABLE_NAME, cv, " id = ?", new String[]{String.valueOf(kId)});
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Kullanici Update hatası sql addKullanici");
            return false;
        }
        return true;
    }

    public boolean checkLogin(String firmaKodu, String kAdi, String sifre, String yetki) {
        db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM kullanicilar WHERE firmaKodu=? and kullaniciAdi=? and sifre=?"
                , new String[]{firmaKodu, kAdi, sifre});
        while (c.moveToNext()) {
            //  System.out.println("firma = " + c.getString(c.getColumnIndex("firmaKodu")));
            //   System.out.println("sifre = " + c.getString(c.getColumnIndex("sifre")));
            //   System.out.println("kADi = " + c.getString(c.getColumnIndex("kullaniciAdi")));
            //   System.out.println("yetki = " + c.getString(c.getColumnIndex("yetki")));
            yetkiz = c.getString(c.getColumnIndex("yetki"));
        }
        c.close();
        if (c.getCount() > 0) return true;
        else return false;
    }

    public String getDurum(int id) {
        db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM hastalar WHERE id=?"
                , new String[]{id + ""});
        String durum = "";
        if (c.moveToFirst()) {
            durum = c.getString(c.getColumnIndex("durum"));
        }
        c.close();
        return durum;

    }

    public String getYetki(int kId) {
        db = this.getReadableDatabase();
        String yetkiz = "";
        Cursor c = db.rawQuery("SELECT * FROM uygulanacakTestler where id=? ",
                new String[]{"" + kId});
        try {
            if (c.moveToFirst()) {
                yetkiz = c.getString(c.getColumnIndex("yetki"));
            }
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("getyetki hata");
        }
        return yetkiz;

    }

    public ArrayList<Hasta> getAllData() {
        ArrayList<Hasta> hastaArrayList = new ArrayList<>();
        try {

            db = this.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM hastalar ", null);

            while (c.moveToNext()) {
                int id = c.getInt(c.getColumnIndex("id"));
                System.out.println("aramaId " + c.getColumnIndex("id"));
                String tarih = c.getString(c.getColumnIndex("kayitTarih"));
                String takip = c.getString(c.getColumnIndex("takipNo"));
                String hastaA = c.getString(c.getColumnIndex("hastaAdi"));
                String hastaS = c.getString(c.getColumnIndex("hastaSoyadi"));
                String durum = c.getString(c.getColumnIndex("durum"));
                Hasta hasta = new Hasta(id, tarih, takip, hastaA + " " + hastaS, durum);
                hastaArrayList.add(hasta);
            }
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Hasta arraylist getAllData hata");
        }
        return hastaArrayList;
    }

    public ArrayList<BeklenenSonuc> getAllDataBs() {
        ArrayList<BeklenenSonuc> BsArrayList = new ArrayList<>();
        int countz = 0;
        try {
            db = this.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM hastalar h inner join uygulanacakTestler u on " +
                    "h.id = u.id where h.durum = 'Bekleniyor' ", null);
            System.out.println("count" + c.getCount());
            while (c.moveToNext()) {
                int id = c.getInt(c.getColumnIndex("id"));
                System.out.println("idBs" + c.getInt(c.getColumnIndex("id")));
                String tarih = c.getString(c.getColumnIndex("kayitTarih"));
                System.out.println("tarihbs " + c.getString(c.getColumnIndex("kayitTarih")));
                String testAd = c.getString(c.getColumnIndex("yapilacakTest"));
                System.out.println("testAdbs " + c.getString(c.getColumnIndex("yapilacakTest")));
                String hasta = c.getString(c.getColumnIndex("hastaAdi"));
                String hasta2 = c.getString(c.getColumnIndex("hastaSoyadi"));
                String durum = c.getString(c.getColumnIndex("durum"));
                System.out.println("durumbs " + c.getString(c.getColumnIndex("durum")));
                BeklenenSonuc cBs = new BeklenenSonuc(id, testAd, hasta + " " + hasta2, tarih, durum);
                BsArrayList.add(cBs);
                countz++;
            }
            c.close();
            System.out.println("c =" + countz);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Hasta arraylist getAllDataBs hata");
        }
        return BsArrayList;
    }

    public ArrayList<Kullanici> getAllDataKt() {
        ArrayList<Kullanici> ktArrayList = new ArrayList<>();
        try {

            db = this.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM kullanicilar", null);
            System.out.println("count" + c.getCount());
            while (c.moveToNext()) {
                int id = c.getInt(c.getColumnIndex("id"));
                System.out.println("idKi" + c.getInt(c.getColumnIndex("id")));
                String kAd = c.getString(c.getColumnIndex("kullaniciAdi"));
                String yetkiliAd = c.getString(c.getColumnIndex("yetkiliAdi"));
                String sifre = c.getString(c.getColumnIndex("sifre"));
                String yetkiliSoyad = c.getString(c.getColumnIndex("yetkiliSoyad"));
                String fAd = c.getString(c.getColumnIndex("firmaAdi"));
                String fKod = c.getString(c.getColumnIndex("firmaKodu"));
                String yetki = c.getString(c.getColumnIndex("yetki"));
                Kullanici k400 = new Kullanici(id, kAd, fKod, sifre, fAd, yetkiliAd, yetkiliSoyad, yetki);
                if (ktArrayList.add(k400) == true) System.out.println("kt eklendi");
                else System.out.println("kt eklenmedi");

            }
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("KT arraylist getAllDatakt hata");
        }
        return ktArrayList;
    }

    public ArrayList<Kullanici> getAllYetki(String yetki_kt) {
        ArrayList<Kullanici> ktArrayList = new ArrayList<>();
        try {
            db = this.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM kullanicilar ORDER BY yetki " + yetki_kt, null);
            while (c.moveToNext()) {
                int id = c.getInt(c.getColumnIndex("id"));
                String kAd = c.getString(c.getColumnIndex("kullaniciAdi"));
                String fKod = c.getString(c.getColumnIndex("firmaKodu"));
                String yetkiAd = c.getString(c.getColumnIndex("yetkiliAdi"));
                String fAd = c.getString(c.getColumnIndex("firmaAdi"));
                String yetki = c.getString(c.getColumnIndex("yetki"));
                String sifre = c.getString(c.getColumnIndex("sifre"));
                String yetkiSoyad = c.getString(c.getColumnIndex("yetkiliSoyad"));
                Kullanici k1 = new Kullanici(id, kAd, fKod, sifre, fAd, yetkiAd, yetkiSoyad, yetki);
                ktArrayList.add(k1);
            }
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("getall yetki hata");
        }
        return ktArrayList;
    }

    public ArrayList<Hasta> getAllDate(String sira) {
        ArrayList<Hasta> hastaArrayList = new ArrayList<>();
        try {

            db = this.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM hastalar order by cast([kayitTarih] as datetime) " + sira, null);

            while (c.moveToNext()) {
                int id = c.getInt(c.getColumnIndex("id"));
                String tarih = c.getString(c.getColumnIndex("kayitTarih"));
                String takip = c.getString(c.getColumnIndex("takipNo"));
                String hasta = c.getString(c.getColumnIndex("hastaAdi"));
                String hasta2 = c.getString(c.getColumnIndex("hastaSoyadi"));
                String durum = c.getString(c.getColumnIndex("durum"));
                Hasta chasta = new Hasta(id, tarih, takip, hasta + " " + hasta2, durum);
                hastaArrayList.add(chasta);
            }
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Hasta arraylist getAllData hata");
        }
        return hastaArrayList;
    }

    public ArrayList<Hasta> getAllDurum(String durumz) {
        ArrayList<Hasta> hastaArrayList = new ArrayList<>();
        try {

            db = this.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM hastalar order by durum " + durumz, null);

            while (c.moveToNext()) {
                int id = c.getInt(c.getColumnIndex("id"));
                String tarih = c.getString(c.getColumnIndex("kayitTarih"));
                String takip = c.getString(c.getColumnIndex("takipNo"));
                String hasta = c.getString(c.getColumnIndex("hastaAdi"));
                String hasta2 = c.getString(c.getColumnIndex("hastaSoyadi"));
                String durum = c.getString(c.getColumnIndex("durum"));
                Hasta chasta = new Hasta(id, tarih, takip, hasta + " " + hasta2, durum);
                hastaArrayList.add(chasta);
            }
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Hasta arraylist getAllData hata");
        }
        return hastaArrayList;
    }

    public ArrayList<BeklenenSonuc> getAllDurumBs(String durumz) {
        ArrayList<BeklenenSonuc> BSarrayList = new ArrayList<>();
        try {

            db = this.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM hastalar  h inner  join uygulanacakTestler u " +
                    "on h.id=u.id where h.durum = 'Bekleniyor' order by cast([kayitTarih] as datetime) " + durumz, null);

            while (c.moveToNext()) {
                int id = c.getInt(c.getColumnIndex("id"));
                String tarih = c.getString(c.getColumnIndex("kayitTarih"));
                String testAd = c.getString(c.getColumnIndex("yapilacakTest"));
                String hasta = c.getString(c.getColumnIndex("hastaAdi"));
                String hasta2 = c.getString(c.getColumnIndex("hastaSoyadi"));
                String durum = c.getString(c.getColumnIndex("durum"));
                BeklenenSonuc beklenenSonuc = new BeklenenSonuc(id, tarih, testAd, hasta + " " + hasta2, durum);
                BSarrayList.add(beklenenSonuc);
            }
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Hasta arraylist getAllDateBs hata");
        }
        return BSarrayList;
    }

    public ArrayList<BeklenenSonuc> getAllDateBs(String sira) {
        ArrayList<BeklenenSonuc> BSarrayList = new ArrayList<>();
        try {

            db = this.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM hastalar  h inner  join uygulanacakTestler u " +
                    "on h.id=u.id where h.durum = 'Bekleniyor' order by cast([kayitTarih] as datetime) " + sira, null);

            while (c.moveToNext()) {
                int id = c.getInt(c.getColumnIndex("id"));
                String tarih = c.getString(c.getColumnIndex("kayitTarih"));
                String testAd = c.getString(c.getColumnIndex("yapilacakTest"));
                String hasta = c.getString(c.getColumnIndex("hastaAdi"));
                String hasta2 = c.getString(c.getColumnIndex("hastaSoyadi"));
                String durum = c.getString(c.getColumnIndex("durum"));
                BeklenenSonuc beklenenSonuc = new BeklenenSonuc(id, tarih, testAd, hasta + " " + hasta2, durum);
                BSarrayList.add(beklenenSonuc);
            }
            System.out.println(" count id sıralama " + c.getCount());
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Hasta arraylist getAllDateBs hata");
        }
        return BSarrayList;
    }

    public HastaSonuc getHastaSonuc(int id) {
        HastaSonuc hs = null;
        try {

            db = this.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM hastaSonuc "
                    + "where id=" + id, null);
            System.out.println("id hastaSonuc" + id);
            while (c.moveToNext()) {
                String testAd = c.getString(c.getColumnIndex("testAd"));
                String sonuc = c.getString(c.getColumnIndex("sonuc"));
                String birim = c.getString(c.getColumnIndex("birim"));
                String ref = c.getString(c.getColumnIndex("referansAraligi"));
                hs = new HastaSonuc(id, testAd, sonuc, birim, ref);
                System.out.println("ch id " + hs.getHastaSonucId());
            }
            if (c.getCount() > 0) System.out.println("veri var");
            else System.out.println("veri yok ");
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Hasta sonuc arraylist getAllData hata");
        }
        return hs;
    }


    public UygulananTestler getTestBilgileri(int id) {
        UygulananTestler ct = null;
        try {
            db = this.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM uygulanacakTestler where id=" + id, null);

            while (c.moveToNext()) {
                String testAd = c.getString(c.getColumnIndex("yapilacakTest"));
                String cGun = c.getString(c.getColumnIndex("calismaGunler"));
                String cSure = c.getString(c.getColumnIndex("calismaSuresi"));
                String aciklama = c.getString(c.getColumnIndex("aciklama"));
                ct = new UygulananTestler(id, testAd, cGun, cSure, aciklama);
                System.out.println("id x" + id + testAd);
            }
            if (c.getCount() > 0) System.out.println("id var");
            else System.out.println("id yok");
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Hasta sonuc arraylist getAllData hata");
        }
        return ct;
    }


    public boolean addHastaSonuc(HastaSonuc hSonuc) {
        try {
            db = this.getWritableDatabase();
            cv = new ContentValues();
            cv.put(TableContacts.hastaSonucTable.COLUMN_TEST_AD, hSonuc.getTestAdi());
            cv.put(TableContacts.hastaSonucTable.COLUMN_SONUC, hSonuc.getSonuc());
            cv.put(TableContacts.hastaSonucTable.COLUMN_BIRIM, hSonuc.getBirim());
            cv.put(TableContacts.hastaSonucTable.COLUMN_TAKIP_NO, hSonuc.getTakipNo());
            cv.put(TableContacts.hastaSonucTable.COLUMN_REFERANS_ARALIGI, hSonuc.getReferansAraligi());
            db.insert(TableContacts.hastaSonucTable.TABLE_NAME, null, cv);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Hasta sonuc ekleme sql hata");
            return false;
        }
        return true;
    }

    public boolean durumGuncelle(int id) {
        try {
            db = this.getWritableDatabase();
            cv = new ContentValues();
            cv.put(TableContacts.hastalarTable.COLUMN_DURUM, "Hazır");
            db.update(TableContacts.hastalarTable.TABLE_NAME, cv, "id=?", new String[]{id + ""});
            db.update(TableContacts.uygulanacakTestler.TABLE_NAME, cv, "id=?", new String[]{id + ""});
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Hasta sonuc ekleme sql hata");
            return false;
        }
        return true;
    }

    public Hasta getkayitBilgiCekme(int bkSId) {
        db = this.getReadableDatabase();
        // ArrayList<hastalar> arkC = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM hastalar where id=? ",
                new String[]{" " + bkSId});
        while (c.moveToNext()) {
            System.out.println("tcNo " + c.getString(c.getColumnIndex("tcNo")));
            String hastaAd = (c.getString(c.getColumnIndex("hastaAdi")));
            String hSoyad = (c.getString(c.getColumnIndex("hastaSoyadi")));
            String yas = (c.getString(c.getColumnIndex("yas")));
            String cs = (c.getString(c.getColumnIndex("cinsiyet")));
            String tcNo = c.getString(c.getColumnIndex("tcNo"));
            String takipNo = c.getString(c.getColumnIndex("takipNo"));
            String durum = "Hazır";
            hastaz = new Hasta(takipNo, tcNo, hastaAd, hSoyad, yas, cs, durum);
            //arkC.add(hastaz);
        }
        c.close();
        return hastaz;
    }

    public Hasta testGirisCekme(int bkSId) {
        db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM hastalar where id=? ",
                new String[]{" " + bkSId});
        while (c.moveToNext()) {
            System.out.println("tcNo " + c.getString(c.getColumnIndex("tcNo")));
            String hastaAd = (c.getString(c.getColumnIndex("hastaAdi")));
            String hSoyad = (c.getString(c.getColumnIndex("hastaSoyadi")));
            String yas = (c.getString(c.getColumnIndex("yas")));
            String cs = (c.getString(c.getColumnIndex("cinsiyet")));
            String tcNo = c.getString(c.getColumnIndex("tcNo"));
            String takipNo = c.getString(c.getColumnIndex("takipNo"));
            String durum = "Hazır";
            hastaz = new Hasta(takipNo, tcNo, hastaAd, hSoyad, yas, cs, durum);
        }
        c.close();
        return hastaz;
    }

    public Kullanici getkullaniciBilgiCekme(int kId) {
        db = this.getReadableDatabase();

        // ArrayList<hastalar> arkC = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM kullanicilar where id=? ",
                new String[]{" " + kId});
        while (c.moveToNext()) {
            System.out.println("get_kullaniciAd " + c.getString(c.getColumnIndex("kullaniciAdi")));
            String kAd = c.getString(c.getColumnIndex("kullaniciAdi"));
            String fKod = (c.getString(c.getColumnIndex("firmaKodu")));
            String sifre = (c.getString(c.getColumnIndex("sifre")));
            String yAd = (c.getString(c.getColumnIndex("yetkiliAdi")));
            String ySoyad = (c.getString(c.getColumnIndex("yetkiliSoyad")));
            String yetki = c.getString(c.getColumnIndex("yetki"));
            String fAd = c.getString(c.getColumnIndex("firmaAdi"));

            k30 = new Kullanici(kAd, fKod, sifre, fAd, yAd, ySoyad, yetki);
            //arkC.add(hastaz);
        }
        c.close();
        return k30;
    }

    public String getTest(int bkSId) {
        db = this.getReadableDatabase();
        UygulananTestler u;
        String testAd = "";
        Cursor c = db.rawQuery("SELECT * FROM uygulanacakTestler where id=? ",
                new String[]{"" + bkSId});
        try {
            if (c.moveToFirst()) {
                testAd = c.getString(c.getColumnIndex("yapilacakTest"));
            }
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("getTest hata");
        }
        return testAd;
    }

    public String getTakip(int hsId) {
        db = this.getReadableDatabase();
        String takip = "";
        Cursor c = db.rawQuery("SELECT * FROM hastalar where id=? ",
                new String[]{"" + hsId});
        try {
            if (c.moveToFirst()) {
                takip = c.getString(c.getColumnIndex("takipNo"));
            }
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("getTest hata");
        }
        return takip;
    }

    public UygulananTestler getTestBilgiCekme(int hs_id) {
        db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM uygulanacakTestler where id=? ",
                new String[]{" " + hs_id});
        while (c.moveToNext()) {
            System.out.println("tcNo " + c.getString(c.getColumnIndex("calismaGunler")));
            String test = (c.getString(c.getColumnIndex("yapilacakTest")));
            String cGun = (c.getString(c.getColumnIndex("calismaGunler")));
            String cSure = (c.getString(c.getColumnIndex("calismaSuresi")));
            String aciklama = (c.getString(c.getColumnIndex("aciklama")));
            u100 = new UygulananTestler(test, cGun, cSure, aciklama);
        }
        c.close();
        return u100;
    }


}