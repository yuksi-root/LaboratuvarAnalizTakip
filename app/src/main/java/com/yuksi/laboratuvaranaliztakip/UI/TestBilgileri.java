package com.yuksi.laboratuvaranaliztakip.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.yuksi.laboratuvaranaliztakip.DataClass.UygulananTestler;
import com.yuksi.laboratuvaranaliztakip.R;
import com.yuksi.laboratuvaranaliztakip.Adapters.UygulananTestlerAdapter;
import com.yuksi.laboratuvaranaliztakip.Database.LabDbHelper;
import com.yuksi.laboratuvaranaliztakip.DataClass.Hasta;

import java.util.ArrayList;

public class TestBilgileri extends AppCompatActivity {
    private ListView l2;
    private ArrayList<UygulananTestler> arrayList2;
    private UygulananTestlerAdapter myAdapter5;
    private LabDbHelper dbHelper;
    private TextView hAdz_tg;
    private TextView hSdz_tg;
    private TextView yasz_tg;
    private TextView csz_tg;
    private TextView tcz_tg;
    private TextView tkpz_tg;
    private int hastaSonucId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_bilgileri);
        l2 = findViewById(R.id.tgList_tg);
        dbHelper = new LabDbHelper(getApplicationContext());
        arrayList2 = new ArrayList<>();
        hAdz_tg = findViewById(R.id.had_txt_tg);
        hSdz_tg = findViewById(R.id.hSad_txt_tg);
        yasz_tg = findViewById(R.id.hyas_txt_tg);
        csz_tg = findViewById(R.id.hcs_txt_tg);
        tcz_tg = findViewById(R.id.tc_txt_tg);
        tkpz_tg = findViewById(R.id.tk_txt_tg);

        Intent intent = getIntent();
        hastaSonucId = intent.getIntExtra("bksId", 1);
        System.out.println("hastaSonucId " + hastaSonucId);
        loadDataInListView();
        try {
            Hasta hastaz;
            hastaz = dbHelper.getkayitBilgiCekme(hastaSonucId);
            hAdz_tg.setText(hastaz.getHastaAdi());
            hSdz_tg.setText(hastaz.getHastaSoyadi());
            yasz_tg.setText(hastaz.getYas());
            csz_tg.setText(hastaz.getCinsiyet());
            tcz_tg.setText(hastaz.getTcNo());
            tkpz_tg.setText(hastaz.getTakipNo());
            String test = dbHelper.getTest(hastaSonucId);
            System.out.println("testAd hsoK " + test);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Kayıt bilgileri cekme hatası hso_ekran");
        }

    }

    private void loadDataInListView() {
        try {
            arrayList2.add(dbHelper.getTestBilgileri(hastaSonucId));
            System.out.println("id x" + arrayList2.get(0).getTestId());
            if (arrayList2.get(0) != null) {
                myAdapter5 = new UygulananTestlerAdapter(this, arrayList2);
                l2.setAdapter(myAdapter5);
                myAdapter5.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("hasta_sonuc_ekran loadData hatası");
        }

    }
}
