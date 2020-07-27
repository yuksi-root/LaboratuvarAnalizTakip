package com.yuksi.laboratuvaranaliztakip.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yuksi.laboratuvaranaliztakip.DataClass.UygulananTestler;
import com.yuksi.laboratuvaranaliztakip.R;


import java.util.ArrayList;

public class UygulananTestlerAdapter extends BaseAdapter {

    private final Context context;
    private ArrayList<UygulananTestler> arrayList;

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void setData(ArrayList a1) {
        arrayList = a1;
    }

    public UygulananTestlerAdapter(Context context, ArrayList<UygulananTestler> hastalarArray) {
        this.context = context;
        this.arrayList = hastalarArray;
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

        UygulananTestlerAdapter.ViewHolder viewHolder = null;

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (inflater != null) {
                convertView = inflater.inflate(R.layout.custom_test_bilgileri, parent, false);
                viewHolder = new UygulananTestlerAdapter.ViewHolder(convertView);
                convertView.setTag(viewHolder);
            }

        } else {
            viewHolder = (UygulananTestlerAdapter.ViewHolder) convertView.getTag();
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

        private TextView t1_id_t;
        private TextView t2_tAd_t;
        private TextView t3_cGun_t;
        private TextView t4_cSure_t;
        private TextView t5_aciklama_t;

        ViewHolder(View view) {
            t1_id_t = view.findViewById(R.id.idz_txt_ctg);
            t2_tAd_t = view.findViewById(R.id.tAdz_txt_ctg);
            t3_cGun_t = view.findViewById(R.id.cGun_ctg);
            t4_cSure_t = view.findViewById(R.id.cSure_txt_ctg);
            t5_aciklama_t = view.findViewById(R.id.aciklama_txt_ctg);
        }

        void setTexts(UygulananTestler ct) {
            t1_id_t.setText(String.valueOf(ct.getTestId()));
            t2_tAd_t.setText(ct.getUygulanacakTest());
            t3_cGun_t.setText(ct.getCalismaGunleri());
            t4_cSure_t.setText(ct.getCalismaSuresi());
            t5_aciklama_t.setText(ct.getAciklama());
        }

    }

}
