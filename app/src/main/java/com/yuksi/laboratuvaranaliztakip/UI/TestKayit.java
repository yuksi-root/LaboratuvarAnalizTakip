package com.yuksi.laboratuvaranaliztakip.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.yuksi.laboratuvaranaliztakip.Database.LabDbHelper;
import com.yuksi.laboratuvaranaliztakip.DataClass.Hasta;
import com.yuksi.laboratuvaranaliztakip.DataClass.UygulananTestler;
import com.yuksi.laboratuvaranaliztakip.R;

public class TestKayit extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner testSpin;
    private EditText EtcNo;
    private EditText EtakipNo;
    private EditText EhastaAdi;
    private EditText EhastaSoyadi;
    private EditText Eyas;
    private EditText EcalismaGunleri;
    private EditText EcalismaSuresi;
    private EditText Eaciklama;
    private RadioButton RbErkek;
    private RadioButton RbKadin;
    private String testSonuc;
    private RadioGroup gr;
    private Hasta hasta;
    private UygulananTestler uTest;
    private CompoundButton previousCheckedCompoundButton;
    private LabDbHelper dbHelper;
    private String info;
    private UygulananTestler u500;
    private Hasta hastaz;
    int hs_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_kayit);
        setupUI();
        ArrayAdapter<CharSequence> adapter;
        adapter = ArrayAdapter.createFromResource(this, R.array.tumTestler, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.test_spinner_item);
        testSpin.setAdapter(adapter);
        testSpin.setOnItemSelectedListener(this);
        try {
            hastaz = dbHelper.getkayitBilgiCekme(hs_id);
            u500 = dbHelper.getTestBilgiCekme(hs_id);
            EhastaAdi.setText(hastaz.getHastaAdi());
            EhastaSoyadi.setText(hastaz.getHastaSoyadi());
            Eyas.setText(hastaz.getYas());
            EtcNo.setText(hastaz.getTcNo());
            EtakipNo.setText(hastaz.getTakipNo());
            EcalismaGunleri.setText(u500.getCalismaSuresi());
            EcalismaSuresi.setText(u500.getCalismaSuresi());
            Eaciklama.setText(u500.getAciklama());
            // String test = dbHelper.getTest(hs_id);
            // System.out.println("testAd hso "+test);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Hasta bilgileri cekme hatası");
        }
    }

    private void setupUI() {
        Intent intent = getIntent();
        info = intent.getStringExtra("info");
        hs_id = intent.getIntExtra("hs_id", 1);
        System.out.println("info " + info);
        if (info == (null)) {
            info = "add";
        }
        dbHelper = new LabDbHelper(getApplicationContext());
        testSpin = findViewById(R.id.tumTestspin);
        EtcNo = findViewById(R.id.tcNoEdit);
        EtakipNo = findViewById(R.id.takipNoEdit);
        EhastaAdi = findViewById(R.id.hastaAdEdit);
        EhastaSoyadi = findViewById(R.id.hastaSoyadEdit);
        Eyas = findViewById(R.id.yasEdit);
        EcalismaGunleri = findViewById(R.id.cGunEdit);
        EcalismaSuresi = findViewById(R.id.cSureEdit);
        Eaciklama = findViewById(R.id.notEdit);
        RbErkek = findViewById(R.id.erkekRb);
        RbKadin = findViewById(R.id.kadinRb);
        CompoundButton.OnCheckedChangeListener onRadioButtonCheckedListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) return;
                if (previousCheckedCompoundButton != null) {
                    previousCheckedCompoundButton.setChecked(false);
                    previousCheckedCompoundButton = buttonView;
                } else {
                    previousCheckedCompoundButton = buttonView;
                }
            }
        };
        RbErkek.setOnCheckedChangeListener(onRadioButtonCheckedListener);
        RbKadin.setOnCheckedChangeListener(onRadioButtonCheckedListener);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        testSonuc = parent.getItemAtPosition(position).toString();
        Toast.makeText(getApplicationContext(), testSonuc, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void saveBtn(View view) {
        String tcNo = EtcNo.getText().toString();
        String takipNo = EtakipNo.getText().toString();
        String hastaAdi = EhastaAdi.getText().toString();
        String hastaSoyadi = EhastaSoyadi.getText().toString();
        String yas = Eyas.getText().toString();
        String cinsiyet;
        String cGun = EcalismaGunleri.getText().toString();
        String cSure = EcalismaSuresi.getText().toString();
        String aciklama = Eaciklama.getText().toString();
        String durum = "Bekleniyor";
        if (previousCheckedCompoundButton == RbErkek) {
            cinsiyet = "Erkek";
        } else {
            cinsiyet = "Kadın";
        }
        if (tcNo.equals("") || takipNo.equals("") || hastaAdi.equals("") || hastaSoyadi.equals("") || yas.equals("")
                || cGun.equals("") || cSure.equals("") || aciklama.equals("") || cinsiyet.equals("")) {
            Toast.makeText(this, "Boşluk bırakmayınız!", Toast.LENGTH_SHORT).show();
        } else {
            if (info.matches("update")) {
                UygulananTestler uTest = new UygulananTestler(testSonuc, cSure, cGun, aciklama);
                Hasta hastak = new Hasta(takipNo, tcNo, hastaAdi, hastaSoyadi, yas, cinsiyet, durum);
                if (dbHelper.updateHasta(hastak, hs_id) == true && dbHelper.updateUygulananTest(uTest, hs_id)) {
                    System.out.println("hasta_kayıt güncellendi");
                } else System.out.println("hasta kayit güncelleme hatası");
            } else {
                try {
                    System.out.println("hastalar " + tcNo + " " + hastaSoyadi + " " + yas + " " + cinsiyet + " " + durum);
                    hasta = new Hasta(takipNo, tcNo, hastaAdi, hastaSoyadi, yas, cinsiyet, durum);
                    uTest = new UygulananTestler(testSonuc, cGun, cSure, aciklama, durum);
                    System.out.println("uygulanan " + testSonuc + " " + cGun + " " + cSure + " " + aciklama + " " + durum);
                    if (dbHelper.addHasta(hasta) == true && dbHelper.addUygulananTest(uTest)) {
                        Toast.makeText(this, "Test Kayıt Başarılı!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Test Kayıt Başarısız!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();//no hata ? ?
                    System.out.println("hasta ve Uygulanan test ekleme hatası");
                }
            }
        }
    }

    public void clear(View view) {
        try {
            EtcNo.setText("");
            EtakipNo.setText("");
            EhastaAdi.setText("");
            EhastaSoyadi.setText("");
            Eyas.setText("");
            EcalismaGunleri.setText("");
            EcalismaSuresi.setText("");
            Eaciklama.setText("");
            RbErkek.clearAnimation();
            RbKadin.clearAnimation();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("clear text hatası test giris");
        }

    }

    public void geri(View view) {
        Intent intent = new Intent(getApplication(), Anasayfa.class);
        intent.putExtra("infos", "newc");
        startActivity(intent);
    }

}
