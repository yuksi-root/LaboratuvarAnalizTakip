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

import com.yuksi.laboratuvaranaliztakip.Adapters.BeklenenSonucAdapter;
import com.yuksi.laboratuvaranaliztakip.Database.LabDbHelper;
import com.yuksi.laboratuvaranaliztakip.R;
import com.yuksi.laboratuvaranaliztakip.DataClass.BeklenenSonuc;

import java.util.ArrayList;

public class BeklenenSonuclarList extends AppCompatActivity {
    private Spinner sira_spin;
    private Spinner durum_spin;
    private LabDbHelper dbHelper;
    private ListView lbs;
    private ArrayList<BeklenenSonuc> arrayList;
    private BeklenenSonucAdapter myAdapter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beklenen_sonuc_list);
        dbHelper = new LabDbHelper(getApplicationContext());
        arrayList = new ArrayList<>();
        sira_spin = findViewById(R.id.sira_spin_bs);
        lbs = findViewById(R.id.bS_List);
        durum_spin = findViewById(R.id.durum_spin_bs);
        //sira spinner
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
                    myAdapter3.setData(dbHelper.getAllDateBs("ASC"));
                    myAdapter3.notifyDataSetChanged();
                } else if (choice.equals("Eskiden Yeniye")) {
                    myAdapter3.setData(dbHelper.getAllDateBs("DESC"));
                    myAdapter3.notifyDataSetChanged();
                } else {
                    myAdapter3.setData(dbHelper.getAllDataBs());
                    myAdapter3.notifyDataSetChanged();
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
                    myAdapter3.setData(dbHelper.getAllDurumBs("DESC"));
                    myAdapter3.notifyDataSetChanged();
                } else if (choice2.equals("Bekleniyor")) {
                    myAdapter3.setData(dbHelper.getAllDurumBs("ASC"));
                    myAdapter3.notifyDataSetChanged();
                } else {
                    myAdapter3.setData(dbHelper.getAllDataBs());
                    myAdapter3.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        loadDataInListView();
        lbs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplication(), HastaSonucOlusturma.class);
                intent.putExtra("info", (arrayList.get(position).getId()));
                startActivity(intent);
            }
        });
    }

    private void loadDataInListView() {
        arrayList = dbHelper.getAllDataBs();
        myAdapter3 = new BeklenenSonucAdapter(this, arrayList);
        lbs.setAdapter(myAdapter3);
        myAdapter3.notifyDataSetChanged();
    }

    public void geri_bs(View view) {
        Intent intent = new Intent(getApplication(), Anasayfa.class);
        startActivity(intent);
    }


}
