package com.yuksi.laboratuvaranaliztakip.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.yuksi.laboratuvaranaliztakip.DataClass.HastaSonuc;
import com.yuksi.laboratuvaranaliztakip.Database.LabDbHelper;
import com.yuksi.laboratuvaranaliztakip.DataClass.Hasta;
import com.yuksi.laboratuvaranaliztakip.R;
import com.yuksi.laboratuvaranaliztakip.Adapters.HastaSonucAdapter;

import java.util.ArrayList;

public class HastaSonuclar extends AppCompatActivity {
    private ListView l2;
    private ArrayList<HastaSonuc> arrayList2 = new ArrayList<>();
    private HastaSonucAdapter myAdapter2;
    private LabDbHelper dbHelper;
    private TextView hAdz_hs;
    private TextView hSdz_hs;
    private TextView yasz_hs;
    private TextView csz_hs;
    private TextView tcz_hs;
    private TextView tkpz_hs;
    private int hastaSonucId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasta_sonuc);
        l2 = findViewById(R.id.hastaSonuc_hs);
        dbHelper = new LabDbHelper(getApplicationContext());
        arrayList2 = new ArrayList<>();
        hAdz_hs = findViewById(R.id.had_txt_hs);
        hSdz_hs = findViewById(R.id.hSad_txt_hs);
        yasz_hs = findViewById(R.id.hyas_txt_hs);
        csz_hs = findViewById(R.id.hcs_txt_hs);
        tcz_hs = findViewById(R.id.tc_txt_hs);
        tkpz_hs = findViewById(R.id.tk_txt_hs);

        Intent intent = getIntent();
        hastaSonucId = intent.getIntExtra("bksId", 1);
        System.out.println("hastaSonucId gelen: " + hastaSonucId);
        loadDataInListView();
        try {
            Hasta hastaz;
            hastaz = dbHelper.getkayitBilgiCekme(hastaSonucId);
            hAdz_hs.setText(hastaz.getHastaAdi());
            hSdz_hs.setText(hastaz.getHastaSoyadi());
            yasz_hs.setText(hastaz.getYas());
            csz_hs.setText(hastaz.getCinsiyet());
            tcz_hs.setText(hastaz.getTcNo());
            tkpz_hs.setText(hastaz.getTakipNo());
            String test = dbHelper.getTest(hastaSonucId);
            System.out.println("testAd hsoK " + test);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Kayıt bilgileri cekme hatası hso_ekran");
        }

    }

    private void loadDataInListView() {
        try {
            arrayList2.add(dbHelper.getHastaSonuc(hastaSonucId));
            if (arrayList2.get(0) != null) {
                myAdapter2 = new HastaSonucAdapter(this, arrayList2);
                l2.setAdapter(myAdapter2);
                myAdapter2.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("hasta_sonuc_ekran loadData hatası");
        }

    }
}
