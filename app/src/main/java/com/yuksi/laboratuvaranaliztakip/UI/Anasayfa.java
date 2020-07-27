package com.yuksi.laboratuvaranaliztakip.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yuksi.laboratuvaranaliztakip.R;

public class Anasayfa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anasayfa);

    }

    public void testGiris(View view) {
        Intent intent = new Intent(getApplication(), TestKayit.class);
        startActivity(intent);
    }

    public void hasta_arama(View view) {
        Intent intent = new Intent(getApplication(), HastaArama.class);
        startActivity(intent);
    }

    public void bs_ekran(View view) {
        Intent intent = new Intent(getApplication(), BeklenenSonuclarList.class);
        startActivity(intent);
    }

    public void kullanici_table(View view) {
        Intent intent = new Intent(getApplication(), KullanicilarList.class);
        startActivity(intent);
    }

    public void exit_y(View view) {
        Intent intent = new Intent(getApplication(), Giris.class);
        startActivity(intent);
    }

    public void kEkle_y(View view) {
        Intent intent = new Intent(getApplication(), KayitOl.class);
        startActivity(intent);
    }
}
