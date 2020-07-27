package com.yuksi.laboratuvaranaliztakip;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.yuksi.laboratuvaranaliztakip.UI.Giris;
import com.yuksi.laboratuvaranaliztakip.UI.HastaSonuclar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void go(View view) {
        Intent intent = new Intent(getApplication(), Giris.class);
        intent.putExtra("info", "new");
        startActivity(intent);
    }


    public void hasta_sonuc(View view) {
        Intent intent = new Intent(getApplication(), HastaSonuclar.class);
        intent.putExtra("info", "new");
        startActivity(intent);
    }


}
