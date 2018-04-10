package com.durocrete.root.durocretpunedriverapp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.durocrete.root.durocretpunedriverapp.MainActivity;
import com.durocrete.root.durocretpunedriverapp.R;
import com.durocrete.root.durocretpunedriverapp.Utillity.MyPreferenceManager;
import com.durocrete.root.durocretpunedriverapp.Utillity.SharedPreference;
import com.durocrete.root.durocretpunedriverapp.comman.Constants;
import com.durocrete.root.durocretpunedriverapp.model.SiteDetailModel;

import java.util.ArrayList;


public class SitesSelectionAdapter extends RecyclerView.Adapter<SitesSelectionAdapter.SelectedSidesHolder> {
    private String TAG = SitesSelectionAdapter.class.getSimpleName();
    private Activity mContext = null;
    private ArrayList<SiteDetailModel> objects = null;
    MyPreferenceManager sharedpref;


    public SitesSelectionAdapter(Activity context, ArrayList<SiteDetailModel> arrayListSides) {
        this.mContext = context;
        this.objects = arrayListSides;

    }

    public void setArray(ArrayList<SiteDetailModel> arrayList) {
        objects.addAll(arrayList);
        this.notifyDataSetChanged();
    }

    public void clearAdapter() {
        this.objects.clear();
        this.notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(final SelectedSidesHolder holder, final int position) {
        final SiteDetailModel siteObject = objects.get(position);
        holder.txtSideName.setText(siteObject.getSiteName());
        holder.txtClientName.setText(siteObject.getClientName());
        holder.txtenquiryid.setText(siteObject.getEnquiry_id());
        holder.checkBoxSideSelected.setChecked(siteObject.isChecked());


        if (!(siteObject.getSiteLatitude() == 0) && !(siteObject.getSiteLongitude() == 0)) {
//            holder.llTextView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.light_orange));
            siteObject.setLatLongPresent(true);
        } else {
//            holder.llTextView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.light_blue));
            siteObject.setLatLongPresent(false);
        }

//        if (position == 0) {
//            holder.imgSiteDetails.setVisibility(View.GONE);
//        } else {
//            holder.imgSiteDetails.setVisibility(View.VISIBLE);
//        }
        String abc= String.valueOf(sharedpref.getIntPreferences(MyPreferenceManager.Siteid));
        if (sharedpref.getBooleanPreferences(MyPreferenceManager.done_bit) && abc.equalsIgnoreCase(String.valueOf(siteObject.getSiteId())))
        {
            holder.llTextView.setBackgroundColor(Color.CYAN);
        }
        else
        {
            holder.llTextView.setBackgroundColor(Color.WHITE);
        }

        holder.llTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String abc= String.valueOf(sharedpref.getIntPreferences(MyPreferenceManager.Siteid));

                if (!sharedpref.getBooleanPreferences(MyPreferenceManager.done_bit) || abc.equalsIgnoreCase(String.valueOf(siteObject.getSiteId()))) {
                    sharedpref.setIntPreferences(MyPreferenceManager.Siteid, siteObject.getSiteId());
                    Intent intent = new Intent(mContext, MainActivity.class);
                    SharedPreference.getInstanceProfileData(mContext).setCheckIn("1");
                    intent.putExtra(Constants.SITEDETAILMODEL, siteObject);
                    mContext.startActivity(intent);

                } else {
                    Toast.makeText(mContext, "Please Check Out First", Toast.LENGTH_SHORT).show();
                }


                Log.v(TAG, " llTextView is Clicked");
                /*Bundle bundle = new Bundle();
                bundle.putInt(Constants.SITEID,siteObject.getSiteId());
                CheckInActivity checkI  nFragment = new CheckInActivity();
                mContext.getSupportFragmentManager().beginTransaction().replace(R.id.container, mapFragment).addToBackStack(TAG).commit();*/

               /* FragmentManager fragmentManager = mContext.getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putInt(Constants.SITEID,siteObject.getSiteId());
                CheckInActivity checkInFragment = new CheckInActivity();
                checkInFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.container, checkInFragment);
                fragmentTransaction.commit();*/

                Log.v(TAG, " siteDetailModel : " + siteObject.toString());
//
//                if (position == 0) {
//
//                } else  {


//                }


            }
        });


        if (position == 0) {
            holder.checkBoxSideSelected.setChecked(true);
            holder.checkBoxSideSelected.setSelected(false);
            Log.v(TAG, " checkBoxSideSelected : " + holder.checkBoxSideSelected.isChecked());
        }


        holder.checkBoxSideSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox checkBox = (CheckBox) view;
                if (position == 0) {
                    checkBox.setChecked(false);
                    checkBox.setSelected(false);
                }

                Log.v(TAG, " CheckBox button is checked : " + objects.get(position).getSiteName());
                if (checkBox.isChecked()) {
                    siteObject.setChecked(true);
                } else {
                    siteObject.setChecked(false);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public ArrayList<SiteDetailModel> getSideObjectArrayList() {
        return objects;
    }


    @Override
    public SelectedSidesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_show_sides, parent, false);

        return new SelectedSidesHolder(rowView);
    }


    /*Create Holder Class for adapter*/

    public class SelectedSidesHolder extends RecyclerView.ViewHolder {
        public TextView txtSideName, txtClientName,txtenquiryid;
        private CheckBox checkBoxSideSelected;
        private LinearLayout llTextView;
        private ImageView imgSiteDetails;

        public SelectedSidesHolder(View view) {
            super(view);
            llTextView = (LinearLayout) view.findViewById(R.id.llTextView);
            txtSideName = (TextView) view.findViewById(R.id.txtSideName);
            txtClientName = (TextView) view.findViewById(R.id.txtClientName);
            txtenquiryid = (TextView) view.findViewById(R.id.txtenquiryid);
            checkBoxSideSelected = (CheckBox) view.findViewById(R.id.checkBoxSideSelected);
            imgSiteDetails = (ImageView) view.findViewById(R.id.imgSiteDetails);
            sharedpref = new MyPreferenceManager(mContext);

        }
    }
}
