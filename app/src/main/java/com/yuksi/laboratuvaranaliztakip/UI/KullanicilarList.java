package com.yuksi.laboratuvaranaliztakip.UI;

import androidx.appcompat.app.AppCompatActivity;

import com.yuksi.laboratuvaranaliztakip.Adapters.KullanicilarAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.yuksi.laboratuvaranaliztakip.Database.LabDbHelper;
import com.yuksi.laboratuvaranaliztakip.DataClass.Kullanici;
import com.yuksi.laboratuvaranaliztakip.R;


import java.util.ArrayList;

public class KullanicilarList extends AppCompatActivity {
    private Spinner sira_spin_kt;
    private LabDbHelper dbHelper;
    private ListView lkt;
    private ArrayList<Kullanici> arrayList;
    private KullanicilarAdapter myAdapter4;
    private boolean duzenlez = false;
    private boolean sil = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kullanicilar_list);
        dbHelper = new LabDbHelper(getApplicationContext());
        arrayList = new ArrayList<>();
        sira_spin_kt = findViewById(R.id.sira_spin_kt);
        lkt = findViewById(R.id.kt_List);
        //sira spinner
        ArrayAdapter<CharSequence> sAdapter;
        sAdapter = ArrayAdapter.createFromResource(this, R.array.Yetki, android.R.layout.simple_spinner_item);
        sAdapter.setDropDownViewResource(R.layout.kayit_spinner_item);
        sira_spin_kt.setAdapter(sAdapter);
        sira_spin_kt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String choice = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), choice, Toast.LENGTH_SHORT).show();
                if (choice.equals("Yönetici")) {
                    myAdapter4.setData(dbHelper.getAllYetki("DESC"));
                    myAdapter4.notifyDataSetChanged();
                } else if (choice.equals("Kullanici")) {
                    myAdapter4.setData(dbHelper.getAllYetki("ASC"));
                    myAdapter4.notifyDataSetChanged();
                } else {
                    myAdapter4.setData(dbHelper.getAllDataKt());
                    myAdapter4.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        loadDataInListView();
        lkt.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (duzenlez == true) {
                    sil = false;
                    Toast.makeText(getApplicationContext(), "Duzenle modu aktif", Toast.LENGTH_SHORT).show();
                    Intent intent2 = new Intent(getApplicationContext(), KayitOl.class);
                    intent2.putExtra("info", "update");
                    intent2.putExtra("id_kt", arrayList.get(position).getKullaniciId());
                    myAdapter4.setData(dbHelper.getAllDataKt());
                    myAdapter4.notifyDataSetChanged();
                    startActivity(intent2);
                } else if (sil == true) {
                    duzenlez = false;
                    Toast.makeText(getApplicationContext(), "Silme modu aktif", Toast.LENGTH_SHORT).show();
                    if (dbHelper.deleteKullanici(arrayList.get(position).getKullaniciId()) == true) {
                        System.out.println("silindi");
                        myAdapter4.setData(dbHelper.getAllDataKt());
                        myAdapter4.notifyDataSetChanged();
                    } else System.out.println("silemedi");
                } else {
                    duzenlez = false;
                    sil = false;
                    Toast.makeText(getApplicationContext(), "butonlar sıfırlandı", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }

    private void loadDataInListView() {
        arrayList = dbHelper.getAllDataKt();
        myAdapter4 = new KullanicilarAdapter(this, arrayList);
        lkt.setAdapter(myAdapter4);
        myAdapter4.notifyDataSetChanged();
    }

    public void sil_kt(View view) {
        sil = !sil;

    }

    public void duzenle_kt(View view) {
        duzenlez = !duzenlez;
    }


}
