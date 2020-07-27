package com.yuksi.laboratuvaranaliztakip.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yuksi.laboratuvaranaliztakip.R;
import com.yuksi.laboratuvaranaliztakip.DataClass.BeklenenSonuc;

import java.util.ArrayList;

public class BeklenenSonucAdapter extends BaseAdapter {

    private final Context context;
    private ArrayList<BeklenenSonuc> ArrayList;

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void setData(ArrayList a1) {
        ArrayList = a1;
    }

    public BeklenenSonucAdapter(Context context, ArrayList<BeklenenSonuc> hastalarArray) {
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

        BeklenenSonucAdapter.ViewHolder viewHolder = null;

        if (convertView == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (inflater != null) {
                convertView = inflater.inflate(R.layout.custom_beklenen_sonuc_list, parent, false);
                viewHolder = new BeklenenSonucAdapter.ViewHolder(convertView);
                convertView.setTag(viewHolder);
            }

        } else {
            viewHolder = (BeklenenSonucAdapter.ViewHolder) convertView.getTag();
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

        private TextView t1_id_lc;
        private TextView t2_tarih_lc;
        private TextView t3_testAd_lc;
        private TextView t4_hasta_lc;
        private TextView t5_durum_lc;

        ViewHolder(View view) {
            t1_id_lc = view.findViewById(R.id.id_txt_listc);
            t2_tarih_lc = view.findViewById(R.id.tarih_txt_listc);
            t3_testAd_lc = view.findViewById(R.id.testAd_txt_listc);
            t4_hasta_lc = view.findViewById(R.id.hasta_txt_listc);
            t5_durum_lc = view.findViewById(R.id.durum_txt_listc);
        }

        void setTexts(BeklenenSonuc cbS) {
            t1_id_lc.setText(String.valueOf(cbS.getId()));
            System.out.println("setTextz BSidBS" + (cbS.getId()));
            t2_tarih_lc.setText(cbS.getTarih());
            System.out.println("setTextz BStarihBS" + cbS.getTarih());
            t3_testAd_lc.setText(cbS.getTestAdi());
            System.out.println("setTextz BStestBS" + cbS.getTestAdi());
            t4_hasta_lc.setText(cbS.getHasta());
            System.out.println("setTextz BShastaBS" + cbS.getHasta());
            t5_durum_lc.setText(cbS.getDurum());
            System.out.println("setTextz BSdurum" + cbS.getDurum());
        }

    }

}
