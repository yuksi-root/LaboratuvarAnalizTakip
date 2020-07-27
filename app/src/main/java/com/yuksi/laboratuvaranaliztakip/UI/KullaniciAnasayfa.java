package com.yuksi.laboratuvaranaliztakip.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yuksi.laboratuvaranaliztakip.R;

public class KullaniciAnasayfa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_k_anasayfa);
    }

    public void testGiris_k(View view) {
        Intent intent = new Intent(getApplication(), TestKayit.class);
        intent.putExtra("info", "kayit");
        startActivity(intent);
    }

    public void hasta_arama_k(View view) {
        Intent intent = new Intent(getApplication(), HastaArama.class);
        intent.putExtra("info", "arama");
        startActivity(intent);
    }

    public void sk_test_k(View view) {
        Intent intent = new Intent(getApplication(), SkTestler.class);
        intent.putExtra("info", "sk");
        startActivity(intent);
    }

    public void exitBtn_k(View view) {
        Intent intent = new Intent(getApplication(), Giris.class);
        intent.putExtra("info", "exit");
        startActivity(intent);
    }
}
