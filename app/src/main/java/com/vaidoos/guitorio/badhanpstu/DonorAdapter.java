package com.vaidoos.guitorio.badhanpstu;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class DonorAdapter extends BaseAdapter {


    private Activity activity;
    private LayoutInflater inflater;
    private List<Donor> donors;


    public DonorAdapter(Activity activity, List<Donor> donors){
        this.activity = activity;
        this.donors = donors;
    }


    @Override
    public int getCount() {
        return donors.size();
    }

    @Override
    public Object getItem(int position) {
        return donors.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.donor_list_item_row, null);
        }

        Donor dt1 = donors.get(position); //new Student(id,name);

        TextView full_name = (TextView) convertView.findViewById(R.id.tvName);
        TextView blood_group = (TextView) convertView.findViewById(R.id.tvBloodGroup);
        TextView contact_no = (TextView) convertView.findViewById(R.id.tvContact_no);

        full_name.setText(dt1.getFull_name());
        blood_group.setText(dt1.getBlood_group());
        contact_no.setText(dt1.getContact_no());

        return convertView;
    }
}

