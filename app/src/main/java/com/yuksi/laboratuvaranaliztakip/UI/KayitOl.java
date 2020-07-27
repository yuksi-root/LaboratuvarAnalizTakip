package com.yuksi.laboratuvaranaliztakip.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.yuksi.laboratuvaranaliztakip.Database.LabDbHelper;
import com.yuksi.laboratuvaranaliztakip.DataClass.Kullanici;
import com.yuksi.laboratuvaranaliztakip.R;

public class KayitOl extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner yetkiSpin;
    private String yetkiString;
    private EditText firmaKod;
    private EditText firmaAd;
    private EditText yetkiliAd;
    private EditText yetkiliSoyad;
    private EditText kullaniciAd;
    private EditText sifre1;
    private EditText sifre2;
    private LabDbHelper dbHelper;
    private int kId;
    private String info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ol_ekran);
        setupUI();

        ArrayAdapter<CharSequence> adapter;
        adapter = ArrayAdapter.createFromResource(this, R.array.Yetki, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.test_spinner_item);
        yetkiSpin.setAdapter(adapter);
        yetkiSpin.setOnItemSelectedListener(this);
        try {
            Kullanici k500;
            k500 = dbHelper.getkullaniciBilgiCekme(kId);
            kullaniciAd.setText(k500.getKullaniciAdi());
            sifre1.setText(k500.getSifre());
            sifre2.setText(k500.getSifre());
            firmaKod.setText(k500.getFirmaKodu());
            firmaAd.setText(k500.getFirmaAdi());
            yetkiliAd.setText(k500.getYetkiliAdi());
            yetkiliSoyad.setText(k500.getYetkiliSoyadi());
            String test = dbHelper.getTest(kId);
            System.out.println("testAd hsoK " + test);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Kullanici bilgileri koyma hatası");
        }
    }

    private void setupUI() {
        Intent intent = getIntent();
        info = intent.getStringExtra("info");
        kId = intent.getIntExtra("info", 1);
        System.out.println("kId " + kId);
        if (info == (null)) {
            info = "add";
        }
        dbHelper = new LabDbHelper(getApplicationContext());
        yetkiSpin = findViewById(R.id.yetkiSpinner);
        firmaKod = findViewById(R.id.firmaKodEdit1);
        firmaAd = findViewById(R.id.firmaAdEdit);
        yetkiliAd = findViewById(R.id.yetkiliAdEdit);
        yetkiliSoyad = findViewById(R.id.yetkiliSoyadEdit);
        kullaniciAd = findViewById(R.id.kAdiEdit);
        sifre1 = findViewById(R.id.sifreEdit1);
        sifre2 = findViewById(R.id.sifreEdit2);
    }

    public void rgSave(View view) {
        String fAd = firmaAd.getText().toString();
        String fKod = firmaKod.getText().toString();
        String yAd = yetkiliAd.getText().toString();
        String ySad = yetkiliSoyad.getText().toString();
        String kAd = kullaniciAd.getText().toString();
        String s1 = sifre1.getText().toString();
        String s2 = sifre2.getText().toString();
        System.out.println("sifre1 " + s1 + " sifre2 " + s2);
        if (fAd.equals("") || yAd.equals("") || ySad.equals("") || kAd.equals("") || s1.equals("") || s2.equals("")
                || yetkiString.equals("Yetki Seçiniz")) {
            Toast.makeText(getApplicationContext(), "BOŞ YER BIRAKMAYINIZ", Toast.LENGTH_SHORT).show();
        } else {
            if (info.matches("update")) {
                if (s1.equals(s2)) {
                    Kullanici k400 = new Kullanici(kAd, fKod, s1, fAd, yAd, ySad, yetkiString);
                    if (dbHelper.updateKullanici(k400, kId) == true) {
                        System.out.println("kullanici_kayıt güncellendi");
                    } else System.out.println("kullanici kayit güncelleme hatası");
                } else
                    Toast.makeText(getApplicationContext(), "Şifreler Uyuşmuyor  ", Toast.LENGTH_SHORT).show();

            } else {
                if (s1.equals(s2)) {
                    try {
                        Kullanici k1 = new Kullanici(kAd, fKod, s1, fAd, yAd, ySad, yetkiString);
                        if (dbHelper.addKullanici(k1) == true) {
                            Toast.makeText(getApplicationContext(), "Kayıt başarılı!", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(getApplicationContext(), "Kayıt başarısız!", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("kullanıcı ekleme hatası");
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Şifreler Uyuşmuyor  ", Toast.LENGTH_SHORT).show();
                }
            }


        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        yetkiString = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
