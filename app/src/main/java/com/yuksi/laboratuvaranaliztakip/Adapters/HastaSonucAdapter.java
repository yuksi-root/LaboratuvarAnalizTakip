package com.yuksi.laboratuvaranaliztakip.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yuksi.laboratuvaranaliztakip.DataClass.HastaSonuc;
import com.yuksi.laboratuvaranaliztakip.R;


import java.util.ArrayList;

public class HastaSonucAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<HastaSonuc> arrayList;

    public HastaSonucAdapter(Context context, ArrayList<HastaSonuc> Array) {
        this.context = context;
        this.arrayList = Array;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (inflater != null) {
                convertView = inflater.inflate(R.layout.custom_hastasonuc, parent, false);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            }

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (viewHolder != null) {
            viewHolder.setTexts(arrayList.get(position));
        }

        return convertView;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    class ViewHolder {

        private TextView t1_idz;
        private TextView t2_tAd;
        private TextView t3_sonuc;
        private TextView t4_birim;
        private TextView t5_ref;

        ViewHolder(View view) {
            t1_idz = view.findViewById(R.id.list_id_txt);
            t2_tAd = view.findViewById(R.id.list_ad_txt);
            t3_sonuc = view.findViewById(R.id.list_sonuc_txt);
            t4_birim = view.findViewById(R.id.list_birim_txt);
            t5_ref = view.findViewById(R.id.list_ref_txt);
        }

        void setTexts(HastaSonuc hastaSonuc) {
            System.out.println("hso_idz " + (hastaSonuc.getHastaSonucId()));
            t1_idz.setText(String.valueOf(hastaSonuc.getHastaSonucId()));
            t2_tAd.setText(hastaSonuc.getTestAdi());
            t3_sonuc.setText(hastaSonuc.getSonuc());
            t4_birim.setText(hastaSonuc.getBirim());
            t5_ref.setText(hastaSonuc.getReferansAraligi());
        }

    }

}
