package com.durocrete.root.durocretpunedriverapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.durocrete.root.durocretpunedriverapp.R;
import com.durocrete.root.durocretpunedriverapp.Utillity.MyPreferenceManager;
import com.durocrete.root.durocretpunedriverapp.Utillity.Utility;
import com.durocrete.root.durocretpunedriverapp.comman.URLS;
import com.durocrete.root.durocretpunedriverapp.listeners.VolleyResponseListener;
import com.durocrete.root.durocretpunedriverapp.model.Report;
import com.durocrete.root.durocretpunedriverapp.network.RequestHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class reportFragment extends Fragment {

    private List<Report> Allreportlist;
    private ArrayAdapter<Report> reportlistadapter;
    MyPreferenceManager Sharedpref;
    private ListView materiallistview;
    private TextView Clientname;
    private TextView sitename;
    private EditText contact_name;
    private EditText contact_number;
    private EditText email_id;
    private Button sendqoutation;
    private Button btnplaceorder;
    private Spinner spAllsiteslist;
    private Long Siteselction;
    private String selectedsitename;
    private ListView reportrecycleview;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report, container, false);

        Initview(view);

        Fetchreportlist();

        return view;
    }

    private void Fetchreportlist() {
        HashMap<String, String> param = new HashMap<>();
        param.put("", "");

        Allreportlist = new ArrayList<>();
        RequestHandler.makeWebservice(true, getActivity(), Request.Method.POST, URLS.getInstance().get_reports, param, Report[].class, new VolleyResponseListener<Report>() {
                    @Override
                    public void onResponse(Report[] object) {
                        if (object[0] instanceof Report) {
                            for (Report materialobject : object) {
                                Allreportlist.add(materialobject);
                            }
                        }
                    }


                    @Override
                    public void onError(String message) {
                        Utility.errorDialog(message, getActivity());

                    }
                }
        );

    }


    private void Initview(View view) {
        Sharedpref = new MyPreferenceManager(getActivity());
        reportrecycleview = (ListView) view.findViewById(R.id.lvreportlist);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Report  ");
    }


}

