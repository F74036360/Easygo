package com.example.joan.gg;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Device extends Fragment {
    View device_view;
    DBHelper DH=new DBHelper(getContext());


    int place1[]=new int[1000],place2[]=new int[1000];
    int countplace1=0,countplace2=0;


    public Device() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        device_view=inflater.inflate(R.layout.fragment_device, container, false);
        ListView device_list=(ListView)device_view.findViewById(R.id.listView_device);



        return device_view;
    }




    public double Distance(double longitude1, double latitude1, double longitude2,double latitude2)
    {
        double radLatitude1 = latitude1 * Math.PI / 180;
        double radLatitude2 = latitude2 * Math.PI / 180;
        double l = radLatitude1 - radLatitude2;
        double p = longitude1 * Math.PI / 180 - longitude2 * Math.PI / 180;
        double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(l / 2), 2)
                + Math.cos(radLatitude1) * Math.cos(radLatitude2)
                * Math.pow(Math.sin(p / 2), 2)));
        distance = distance * 6378137.0;
        distance = Math.round(distance * 10000) / 10000;

        return distance ;
    }



}
