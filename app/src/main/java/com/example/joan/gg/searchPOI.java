package com.example.joan.gg;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class searchPOI extends Fragment {
    View vv;
    String temptime;
    String tempPOI;
    final String[] POI={"restaurant","hotel","hospital","attraction"};
    public static ArrayAdapter<String> poiadapter;
    public static ArrayAdapter<String> timeadapter;
    ListView list;
    public searchPOI() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vv=inflater.inflate(R.layout.fragment_search_poi, container, false);
        Button addpoi=(Button)vv.findViewById(R.id.button_add);
        poiadapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1);
        list=(ListView)vv.findViewById(R.id.listView);
        timeadapter=new ArrayAdapter<String>(getContext(),android.R.layout.list_content);
        Button ok=(Button)vv.findViewById(R.id.button_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.useforpoi,BlankFragment.device);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/
            }
        });

        addpoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflaterr=LayoutInflater.from(getContext());
                final View alterdia=inflaterr.inflate(R.layout.layoutpoi,null);


                final String[] POI={"restaurant","hotel","hospital","attraction"};
                ArrayAdapter<String> arr=new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,POI);
                Spinner spinner=(Spinner)alterdia.findViewById(R.id.spinner_POI);
                spinner.setAdapter(arr);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        tempPOI=adapterView.getSelectedItem().toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });


                final String[] time={"0.5hr","1hr","1.5hr","2hr","3hr","4hr","6hr"};
                ArrayAdapter<String> arr_time=new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,time);
                Spinner spinner_time=(Spinner)alterdia.findViewById(R.id.spinner_time);
                spinner_time.setAdapter(arr_time);

                spinner_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        temptime=adapterView.getSelectedItem().toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                new AlertDialog.Builder(getContext()).setTitle("POI").setView(alterdia).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getcardview(temptime,tempPOI);
                    }
                }).show();

            }
        });

        return vv;
    }

    private void getcardview(String time,String poi)
    {
        poiadapter.add(poi);
        timeadapter.add(time);
        list.setAdapter(poiadapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(),timeadapter.getItem(i).toString(), Toast.LENGTH_LONG).show();
            }
        });
        return;

    }

}
