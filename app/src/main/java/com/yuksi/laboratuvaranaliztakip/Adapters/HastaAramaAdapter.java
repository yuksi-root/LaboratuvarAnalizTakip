package com.yuksi.laboratuvaranaliztakip.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yuksi.laboratuvaranaliztakip.DataClass.Hasta;
import com.yuksi.laboratuvaranaliztakip.R;


import java.util.ArrayList;

public class HastaAramaAdapter extends BaseAdapter {

    private final Context context;
    private ArrayList<Hasta> hastalarArrayList;

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void setData(ArrayList a1) {
        hastalarArrayList = a1;
    }

    public HastaAramaAdapter(Context context, ArrayList<Hasta> hastalarArray) {
        this.context = context;
        this.hastalarArrayList = hastalarArray;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return hastalarArrayList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        HastaAramaAdapter.ViewHolder viewHolder = null;

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (inflater != null) {
                convertView = inflater.inflate(R.layout.custom_hasta_arama, parent, false);
                viewHolder = new HastaAramaAdapter.ViewHolder(convertView);
                convertView.setTag(viewHolder);
            }

        } else {
            viewHolder = (HastaAramaAdapter.ViewHolder) convertView.getTag();
        }

        if (viewHolder != null) {
            viewHolder.setTexts(hastalarArrayList.get(position));
        }

        return convertView;
    }

    @Override
    public int getCount() {
        return this.hastalarArrayList.size();
    }

    class ViewHolder {

        private TextView t1_id_l;
        private TextView t2_tarih_l;
        private TextView t3_takipNo_l;
        private TextView t4_hasta;
        private TextView t5_durum;

        ViewHolder(View view) {
            t1_id_l = view.findViewById(R.id.id_txt_alist);
            t2_tarih_l = view.findViewById(R.id.tarih_txt_alist);
            t3_takipNo_l = view.findViewById(R.id.takipNo_txt_alist);
            t4_hasta = view.findViewById(R.id.hastaAd_txt_alist);
            t5_durum = view.findViewById(R.id.durum_txt_alist);
        }

        void setTexts(Hasta hasta) {
            t1_id_l.setText(String.valueOf(hasta.getHastaId()));
            t2_tarih_l.setText(hasta.getTarih());
            t3_takipNo_l.setText(hasta.getTakipNo());
            t4_hasta.setText(hasta.getHastaAdi());
            t5_durum.setText(hasta.getDurum());
        }

    }

}
