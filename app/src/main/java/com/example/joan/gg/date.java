package com.example.joan.gg;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class date extends Fragment {

    private View dateview;
    private int mYear, mMonth, mDay,mYear1, mMonth1, mDay1;
    TextView t_go,t_leave;
    String format,format1;
    public date() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dateview=inflater.inflate(R.layout.fragment_date,container,false);
        Button datetogo=(Button)dateview.findViewById(R.id.button_go);
        Button datetoleave=(Button)dateview.findViewById(R.id.button_leave);
        t_go=(TextView)dateview.findViewById(R.id.daytogo_text);
        t_leave=(TextView)dateview.findViewById(R.id.leave_text);
        Button ok=(Button)dateview.findViewById(R.id.button_OK);
        datetogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c=Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day)
                    {
                        format=""+year+"/"+month+"/"+day;
                        Log.e(""+format,format);
                        t_go.setText(format);
                    }

                }, mYear,mMonth, mDay).show();

            }
        });

        datetoleave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar d=Calendar.getInstance();
                mYear1=d.get(Calendar.YEAR);
                mMonth1=d.get(Calendar.MONTH);
                mDay1=d.get(Calendar.DATE);

                new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day)
                    {
                        format1=""+year+"/"+month+"/"+day;
                        Log.e(""+format,format);
                        t_leave.setText(format1);
                    }

                }, mYear1,mMonth1, mDay1).show();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.useforpoi,BlankFragment.searchpoi);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return dateview;
    }

}
