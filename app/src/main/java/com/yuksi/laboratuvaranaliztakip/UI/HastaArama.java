package com.yuksi.laboratuvaranaliztakip.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.yuksi.laboratuvaranaliztakip.DataClass.Hasta;
import com.yuksi.laboratuvaranaliztakip.Database.LabDbHelper;
import com.yuksi.laboratuvaranaliztakip.R;
import com.yuksi.laboratuvaranaliztakip.Adapters.HastaAramaAdapter;

import java.util.ArrayList;

public class HastaArama extends AppCompatActivity {
    private Spinner sira_spin;
    private Spinner durum_spin;
    private LabDbHelper dbHelper;
    private ListView l1;
    private ArrayList<Hasta> arrayList;
    private HastaAramaAdapter mAdapter;
    private boolean duzenlez = false;
    private boolean sil = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasta_arama);
        dbHelper = new LabDbHelper(getApplicationContext());
        arrayList = new ArrayList<>();
        sira_spin = findViewById(R.id.sira_spin);
        l1 = findViewById(R.id.hastaLists);
        durum_spin = findViewById(R.id.durum_spin);


        ArrayAdapter<CharSequence> sAdapter;
        sAdapter = ArrayAdapter.createFromResource(this, R.array.TariheGore, android.R.layout.simple_spinner_item);
        sAdapter.setDropDownViewResource(R.layout.kayit_spinner_item);
        sira_spin.setAdapter(sAdapter);
        sira_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String choice = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), choice, Toast.LENGTH_SHORT).show();
                if (choice.equals("Yeniden Eskiye")) {
                    mAdapter.setData(dbHelper.getAllDate("ASC"));
                    mAdapter.notifyDataSetChanged();
                } else if (choice.equals("Eskiden Yeniye")) {
                    mAdapter.setData(dbHelper.getAllDate("DESC"));
                    mAdapter.notifyDataSetChanged();
                } else {
                    mAdapter.setData(dbHelper.getAllData());
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //durum spinner
        ArrayAdapter<CharSequence> dAdapter;
        dAdapter = ArrayAdapter.createFromResource(this, R.array.Durum, android.R.layout.simple_spinner_item);
        dAdapter.setDropDownViewResource(R.layout.kayit_spinner_item);
        durum_spin.setAdapter(dAdapter);
        durum_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String choice2 = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), choice2, Toast.LENGTH_SHORT).show();
                if (choice2.equals("Hazır")) {
                    mAdapter.setData(dbHelper.getAllDurum("DESC"));
                    mAdapter.notifyDataSetChanged();
                } else if (choice2.equals("Bekleniyor")) {
                    mAdapter.setData(dbHelper.getAllDurum("ASC"));
                    mAdapter.notifyDataSetChanged();
                } else {
                    mAdapter.setData(dbHelper.getAllData());
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        loadDataInListView();

        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int idz = (arrayList.get(position).getHastaId());

                String durum = dbHelper.getDurum(idz);

                Intent i;

                if (durum.equals("Bekleniyor")) {
                    i = new Intent(getApplication(), TestBilgileri.class);
                } else {
                    i = new Intent(getApplication(), HastaSonuclar.class);
                }

                i.putExtra("bksId", idz);
                startActivity(i);

            }
        });
        l1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (duzenlez == true) {
                    sil = false;
                    Toast.makeText(getApplicationContext(), "Duzenle modu aktif", Toast.LENGTH_SHORT).show();
                    Intent intent2 = new Intent(getApplicationContext(), TestKayit.class);
                    intent2.putExtra("info", "update");
                    intent2.putExtra("id_hs", arrayList.get(position).getHastaId());
                    mAdapter.setData(dbHelper.getAllData());
                    mAdapter.notifyDataSetChanged();
                    startActivity(intent2);
                } else if (sil == true) {
                    duzenlez = false;
                    Toast.makeText(getApplicationContext(), "Silme modu aktif", Toast.LENGTH_SHORT).show();
                    if (dbHelper.deleteHasta(arrayList.get(position).getHastaId()) == true) {
                        System.out.println("silindi");
                        mAdapter.setData(dbHelper.getAllData());
                        mAdapter.notifyDataSetChanged();
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
        arrayList = dbHelper.getAllData();
        mAdapter = new HastaAramaAdapter(this, arrayList);
        l1.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    public void duzenlez(View view) {
        duzenlez = !duzenlez;
    }

    public void sil(View view) {
        sil = !sil;
    }
}
