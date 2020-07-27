package com.yuksi.laboratuvaranaliztakip.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yuksi.laboratuvaranaliztakip.Database.LabDbHelper;
import com.yuksi.laboratuvaranaliztakip.DataClass.HastaSonuc;
import com.yuksi.laboratuvaranaliztakip.DataClass.Hasta;
import com.yuksi.laboratuvaranaliztakip.R;


public class HastaSonucOlusturma extends AppCompatActivity {
    private EditText Esonuc;
    private EditText Ebirim;
    private EditText Eref;
    private EditText testAdz;
    private LabDbHelper dbHelper;
    private HastaSonuc hastaSonuc;
    private TextView hAdz_hso;
    private TextView hSdz_hso;
    private TextView yasz_hso;
    private TextView csz_hso;
    private TextView tcz_hso;
    private TextView tkpz_hso;
    private int bkSonucId;
    String takipNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasta_sonuc_olusturma);
        dbHelper = new LabDbHelper(getApplicationContext());
        Esonuc = findViewById(R.id.sonucEdit);
        Ebirim = findViewById(R.id.birimEdit);
        Eref = findViewById(R.id.refEdit);
        testAdz = findViewById(R.id.EtestAdz);
        hAdz_hso = findViewById(R.id.hAd_txt_hso);
        hSdz_hso = findViewById(R.id.hSad_txt_hso);
        yasz_hso = findViewById(R.id.yas_txt_hso);
        csz_hso = findViewById(R.id.cinsiyet_txt_hso);
        tcz_hso = findViewById(R.id.tcN_txt_hso);
        tkpz_hso = findViewById(R.id.tkN_txt_hso);
        Intent intent = getIntent();
        bkSonucId = intent.getIntExtra("info", 1);
        System.out.println("gelen id:" + bkSonucId);

        try {
            Hasta hastaz;
            hastaz = dbHelper.getkayitBilgiCekme(bkSonucId);
            hAdz_hso.setText(hastaz.getHastaAdi());
            hSdz_hso.setText(hastaz.getHastaSoyadi());
            yasz_hso.setText(hastaz.getYas());
            csz_hso.setText(hastaz.getCinsiyet());
            tcz_hso.setText(hastaz.getTcNo());
            tkpz_hso.setText(hastaz.getTakipNo());
            takipNo = hastaz.getTakipNo();
            System.out.println("takipNo_hso");
            String test = dbHelper.getTest(bkSonucId);
            testAdz.setText(test);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Kayıt bilgileri cekme hatası hso");
        }
    }

    public void saveBtn(View view) {
        String sonuc = Esonuc.getText().toString();
        String birim = Ebirim.getText().toString();
        String ref = Eref.getText().toString();
        String tAdz = testAdz.getText().toString();

        if (sonuc.equals("") || birim.equals("") || ref.equals("") || tAdz.equals("")) {
            Toast.makeText(getApplicationContext(), "boş yer bırakmayınız!", Toast.LENGTH_SHORT).show();
        } else {
            try {
                System.out.println("hastasonuc " + sonuc + " " + birim + " " + ref + tAdz);
                hastaSonuc = new HastaSonuc(tAdz, sonuc, birim, ref, takipNo);
                if (dbHelper.addHastaSonuc(hastaSonuc) && dbHelper.durumGuncelle(bkSonucId)) {

                    Toast.makeText(this, "Kayıt Başarılı!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(this, BeklenenSonuclarList.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                    finish();
                } else Toast.makeText(this, "Test Kayıt Başarısız!", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {

                e.printStackTrace();
                System.out.println("hasta_olusturma_save hatası");
            }
        }
    }
}
