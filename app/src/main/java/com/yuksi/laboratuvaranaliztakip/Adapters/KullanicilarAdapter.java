package com.yuksi.laboratuvaranaliztakip.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yuksi.laboratuvaranaliztakip.R;
import com.yuksi.laboratuvaranaliztakip.DataClass.Kullanici;

import java.util.ArrayList;

public class KullanicilarAdapter extends BaseAdapter {

    private final Context context;
    private ArrayList<Kullanici> ArrayList;

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void setData(ArrayList a1) {
        ArrayList = a1;
    }

    public KullanicilarAdapter(Context context, ArrayList<Kullanici> hastalarArray) {
        this.context = context;
        this.ArrayList = hastalarArray;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return ArrayList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        KullanicilarAdapter.ViewHolder viewHolder = null;

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (inflater != null) {
                convertView = inflater.inflate(R.layout.custom_kullanicilar, parent, false);
                viewHolder = new KullanicilarAdapter.ViewHolder(convertView);
                convertView.setTag(viewHolder);
            }

        } else {
            viewHolder = (KullanicilarAdapter.ViewHolder) convertView.getTag();
        }

        if (viewHolder != null) {
            viewHolder.setTexts(ArrayList.get(position));
        }

        return convertView;
    }

    @Override
    public int getCount() {
        return this.ArrayList.size();
    }

    class ViewHolder {

        private TextView t1_id_ktz;
        private TextView t2_kAdi_ktz;
        private TextView t3_sifre_ktz;
        private TextView t4_fAd_ktz;
        private TextView t5_yetki_ktz;

        ViewHolder(View view) {
            t1_id_ktz = view.findViewById(R.id.id_txt_ktc);
            t2_kAdi_ktz = view.findViewById(R.id.kAdi_txt_ktc);
            t3_sifre_ktz = view.findViewById(R.id.sifre_txt_ktc);
            t4_fAd_ktz = view.findViewById(R.id.firma_txt_ktc);
            t5_yetki_ktz = view.findViewById(R.id.yetki_txt_ktc);
        }

        void setTexts(Kullanici k) {
            t1_id_ktz.setText(String.valueOf(k.getKullaniciId()));
            t2_kAdi_ktz.setText(k.getKullaniciAdi());
            t3_sifre_ktz.setText(k.getYetkiliAdi());
            t4_fAd_ktz.setText(k.getFirmaAdi());
            t5_yetki_ktz.setText(k.getYetkisi());
        }

    }

}
