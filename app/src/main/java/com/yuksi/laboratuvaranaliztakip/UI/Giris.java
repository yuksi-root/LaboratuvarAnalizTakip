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
import com.yuksi.laboratuvaranaliztakip.R;

import static com.yuksi.laboratuvaranaliztakip.Database.LabDbHelper.yetkiz;

public class Giris extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText kAdi;
    private EditText sifre;
    private EditText firmaKodu;
    private LabDbHelper dbHelper;
    private Spinner yetki_spin_bs;
    private String yetkiString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);
        dbHelper = new LabDbHelper(getApplicationContext());
        kAdi = findViewById(R.id.kullaniciAdiEdit);
        sifre = findViewById(R.id.sifreEdit);
        firmaKodu = findViewById(R.id.firmaKoduEdit);
        yetki_spin_bs = findViewById(R.id.yetkiSpinner_ge);
        ArrayAdapter<CharSequence> adapter;
        adapter = ArrayAdapter.createFromResource(this, R.array.Yetki, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.test_spinner_item);
        yetki_spin_bs.setAdapter(adapter);
        yetki_spin_bs.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        yetkiString = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void btnRg(View view) {
        Intent intent = new Intent(getApplication(), KayitOl.class);
        intent.putExtra("info", "kayit");
        startActivity(intent);
    }

    public void btnGiris(View view) {
        String firmaKod = firmaKodu.getText().toString();
        String kAdiT = kAdi.getText().toString();
        String sifreT = sifre.getText().toString();
        if (firmaKod.equals("") || kAdiT.equals("") || sifreT.equals("")) {
            Toast.makeText(getApplicationContext(), "Boş yer bırakmayınız!", Toast.LENGTH_SHORT).show();
        } else {
            try {
                Boolean checkLogin = dbHelper.checkLogin(firmaKod, kAdiT, sifreT, yetkiString);
                System.out.println("chk = " + checkLogin);
                if (firmaKod.equals("q") && kAdiT.equals("q") && sifreT.equals("q")) {
                    Intent intent = new Intent(getApplication(), Anasayfa.class);
                    intent.putExtra("info", "giris");
                    startActivity(intent);
                }
                if (checkLogin == true) {
                    if (yetkiz.equals("Yönetici")) {
                        Intent intent = new Intent(getApplication(), Anasayfa.class);
                        intent.putExtra("info", "giris");
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(getApplication(), KullaniciAnasayfa.class);
                        intent.putExtra("info", "giris");
                        startActivity(intent);
                    }
                } else
                    Toast.makeText(getApplicationContext(), "Giriş Başarısız", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("BtnGiriş hatası");
            }
        }
    }
}
