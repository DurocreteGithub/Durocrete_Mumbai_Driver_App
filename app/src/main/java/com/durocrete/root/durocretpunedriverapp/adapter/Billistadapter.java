package com.durocrete.root.durocretpunedriverapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.durocrete.root.durocretpunedriverapp.R;
import com.durocrete.root.durocretpunedriverapp.model.Bill;

import java.util.List;

/**
 * Created by root on 26/5/17.
 */

public class Billistadapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Bill> Allbilllist;


    public Billistadapter(FragmentActivity activity, List<Bill> alltestlist) {
        this.activity = activity;

        this.Allbilllist = alltestlist;
    }

    @Override
    public int getCount() {
        return Allbilllist.size();
    }

    @Override
    public Object getItem(int position) {
        return Allbilllist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public List<Bill> Alltestlist() {
        return this.Allbilllist;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.bill_list_single_row, null);
        }
        TextView testname = (TextView) convertView.findViewById(R.id.testname);
//            TextView testrate = (TextView) convertView.findViewById(R.id.testrate);
        final CheckBox selectcheckbox = (CheckBox) convertView.findViewById(R.id.checkbox);


        final Bill billlist = Allbilllist.get(position);




            testname.setText(billlist.getBills());


        selectcheckbox.setChecked(billlist.getIsChecked());


        selectcheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selectcheckbox.isChecked()) {
                    billlist.setIschecked(true);

                } else {
                    billlist.setIschecked(false);

                }
            }
        });

//

        return convertView;
    }


}


