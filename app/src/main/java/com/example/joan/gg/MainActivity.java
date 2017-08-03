package com.example.joan.gg;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Button myButton,addtoFavorite,bnearby;
    TextView textView;

    public static MapsActivity mapsActivity=new MapsActivity();
    FragmentManager fm = getSupportFragmentManager();

    public static JSONArray arr;
    BlankFragment bb=new BlankFragment();
    DBHelper DH=new DBHelper(this);
    public static String[] name_Ch;
    public static double[] latitude;
    public static double[] longitude;
    public static String[] tel;
    public static String[] told;
    public static String[] web;
    public static String[] address;
    public static String[] opentime;
    public static Cursor choosed=null;
    Fragment fragment = null;
    Class fragmentClass=mapsActivity.getClass();
    static Marker mLocationMarker;
    static int fromwhichOption;
    RelativeLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new JsonTask().execute("http://data.tycg.gov.tw/api/v1/rest/datastore/0daad6e6-0632-44f5-bd25-5e1de1e9146f?format=json");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        myButton = new Button(MainActivity.this);
        myButton.setVisibility(View.INVISIBLE);
        myButton.setText("more info");
        myButton.setBackgroundColor(Color.rgb(255, 184, 184));


        ll = (RelativeLayout)findViewById(R.id.content);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);

        ll.addView(myButton, lp);


        textView = new Button(MainActivity.this);
        textView.setVisibility(View.INVISIBLE);
        textView.setBackgroundColor(Color.rgb(255, 184, 184));

        ll.addView(textView, lp);

        addtoFavorite=new Button(MainActivity.this);
        addtoFavorite.setVisibility(View.INVISIBLE);
        addtoFavorite.setText("add to favorite ");
        addtoFavorite.setBackgroundColor(Color.rgb(255, 184, 184));
        RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        lp2.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);
        ll.addView(addtoFavorite,lp2);

        bnearby=new Button(MainActivity.this);
        bnearby.setVisibility(View.INVISIBLE);
        bnearby.setText("nearby");
        bnearby.setBackgroundColor(Color.rgb(255, 184, 184));
        RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp3.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        lp3.addRule(RelativeLayout.ALIGN_PARENT_LEFT,RelativeLayout.TRUE);
        ll.addView(bnearby,lp3);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double latitude1=24.41837; //拉拉山
                double longitude1=121.25307;
                double dis=Distance(mapsActivity.mLastLocation.getLongitude(),mapsActivity.mLastLocation.getLatitude(),longitude1,latitude1);
                if(dis<2000)
                {

                    Log.e("into fruit","ohoh");
                    final ImageView mv = new ImageView(MainActivity.this);
                    mv.setImageResource(R.drawable.fruit);

                    RelativeLayout ll = (RelativeLayout)findViewById(R.id.content);
                    RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);


                    ll.addView(mv, lp);

                    mv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mv.setVisibility(View.INVISIBLE);
                        }
                    });
                    Log.e("YAYA ","有喔有喔");

                }

                double latitude2=24.885128; //大溪
                double longitude2=121.287747;
                double mmL=mapsActivity.mLastLocation.getLongitude();
                double mmlat=mapsActivity.mLastLocation.getLatitude();
                Log.d(" "+mmL,"  "+mmlat);
                double dis2=Distance(mapsActivity.mLastLocation.getLongitude(),mapsActivity.mLastLocation.getLatitude(),longitude2,latitude2);
                if(dis2<2000)
                {

                    final ImageView mv = new ImageView(MainActivity.this);
                    mv.setImageResource(R.drawable.doo);

                    RelativeLayout ll = (RelativeLayout)findViewById(R.id.content);
                    RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);


                    ll.addView(mv, lp);

                    mv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mv.setVisibility(View.INVISIBLE);
                        }
                    });
                    Log.e("YAYAYA","有喔有喔");

                }

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        fm.beginTransaction().replace(R.id.content, fragment).commit();




    }
    GoogleMap.OnMarkerClickListener gmapbuttonListener=new GoogleMap.OnMarkerClickListener(){

        @Override
        public boolean onMarkerClick(final Marker marker) {
            myButton.setVisibility(View.VISIBLE);
            addtoFavorite.setVisibility(View.VISIBLE);
            bnearby.setVisibility(View.VISIBLE);
            final String str=marker.getSnippet();
            myButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+str));
                    startActivity(intent);
                    myButton.setVisibility(View.INVISIBLE);
                }
            });

            addtoFavorite.setVisibility(View.VISIBLE);
            addtoFavorite.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if(choosed!=null)
                    {
                        SQLiteDatabase db=DH.getWritableDatabase();
                        choosed.moveToFirst();
                        ContentValues value=new ContentValues();
                        value.put(DBHelper.NAME_CHINESE,marker.getTitle());
                        value.put(DBHelper.Web,str);
                        value.put(DBHelper.LATITUDE_,marker.getPosition().latitude);
                        value.put(DBHelper.LONGITUDE_,marker.getPosition().longitude);
                        db.insertOrThrow(DBHelper.TABLE_JP_TRAVEL,null,value);
                    }

                }


            });

            bnearby.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    nearby(marker.getPosition(),marker.getTitle());
                }
            });


           // mapsActivity.mMap.animateCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));
            mapsActivity.mMap.getUiSettings().setMapToolbarEnabled(true);

            mapsActivity.mMap.setOnMapClickListener(mapClickListener);
            marker.showInfoWindow();

            return false;
        }


    };

    private void addtoBASE() {
      // Log.e("plz","gogo"+choosed.getString(1));
}

    GoogleMap.OnMapClickListener mapClickListener=new GoogleMap.OnMapClickListener(){

        @Override
        public void onMapClick(LatLng latLng) {
            myButton.setVisibility(View.INVISIBLE);
            textView.setVisibility(View.INVISIBLE);
            addtoFavorite.setVisibility(View.INVISIBLE);
            bnearby.setVisibility(View.INVISIBLE);
        }
    };



    GoogleMap.OnMarkerClickListener gmapTextListener = new GoogleMap.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(Marker marker) {

                  ll.removeView(textView);
                if(marker.getSnippet()!=null)
                {
                    textView.setVisibility(View.VISIBLE);
                    textView.setText(marker.getSnippet());
                    RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
                    lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
                    ll.addView(textView,lp);

                }

            mapsActivity.mMap.getUiSettings().setMapToolbarEnabled(true);
            mapsActivity.mMap.setOnMapClickListener(mapClickListener);
            marker.showInfoWindow();
            return false;

        }
    };

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        mapsActivity.mMap.setOnMapClickListener(mapClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        fromwhichOption=0;
        if (id == R.id.nav_camera) {
            fragmentClass=mapsActivity.getClass();
            camera();
        } else if (id == R.id.travel) {
            fragmentClass=mapsActivity.getClass();
            fromwhichOption=6;
            switch_travel();
            //mapsActivity.mMap.setOnMarkerClickListener(gmapbuttonListener);
        } else if (id == R.id.hotel) {
            fragmentClass=mapsActivity.getClass();
            fromwhichOption=1;
            switch_hotel();
           // mapsActivity.mMap.setOnMarkerClickListener(gmapbuttonListener);
        } else if (id == R.id.police) {
            fragmentClass=mapsActivity.getClass();
            fromwhichOption=2;
            police();
        }
        else if (id == R.id.parking) {
            fragmentClass=mapsActivity.getClass();
            fromwhichOption=4;
            new JsonTask().execute("http://data.tycg.gov.tw/api/v1/rest/datastore/0daad6e6-0632-44f5-bd25-5e1de1e9146f?format=json");
            park();
        }else if(id==R.id.favorite) {
            fragmentClass=mapsActivity.getClass();
            favorite();


        }
        else if(id==R.id.stolen) {
            fragmentClass=mapsActivity.getClass();
            fromwhichOption=5;
            LatLng nowplace=new LatLng(mapsActivity.mLastLocation.getLatitude(),mapsActivity.mLastLocation.getLongitude());
            stole(nowplace);
        }

        else if(id==R.id.POI_search)
        {
            fragmentClass=bb.getClass();
        }



        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        fm.beginTransaction().replace(R.id.content, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mapsActivity.mMap.setOnMapClickListener(mapClickListener);
        drawer.closeDrawer(GravityCompat.START);



        return true;
    }

    private void favorite() {
        /*ListView v=new ListView(MainActivity.this);
        ll = (RelativeLayout)findViewById(R.id.mapppp);*/
        mapsActivity.mMap.clear();
        final SQLiteDatabase db=DH.getWritableDatabase();
        //db.delete(DBHelper.TABLE_JP_TRAVEL,null,null);
        Cursor cc=db.rawQuery("SELECT * FROM "+DBHelper.TABLE_JP_TRAVEL, null);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1);
        adapter.add("delete all in favorite ");
        cc.moveToFirst();
        while (cc.isAfterLast() == false) {
            adapter.add(cc.getString(1));
            cc.moveToNext();
        }
       /* v.setAdapter(adapter);
        ll.addView(v);*/
        AlertDialog.Builder dialog_list = new AlertDialog.Builder(this);
        dialog_list.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Cursor c=db.rawQuery("SELECT * FROM " + DBHelper.TABLE_JP_TRAVEL + " WHERE " + DBHelper.NAME_CHINESE + " = '" + adapter.getItem(i) + "'", null);
                if(c!=null)
                {
                    c.moveToFirst();
                    double lat=c.getDouble(3);
                    double lon=c.getDouble(4);
                    LatLng place=new LatLng(lat,lon);
                    fromwhichOption=6;
                    moveMap(place,c);
                }


            }
        });
        final AlertDialog ad = dialog_list.create(); //don't show dialog yet
        ad.setOnShowListener(new DialogInterface.OnShowListener()
        {
            @Override
            public void onShow(DialogInterface dialog)
            {
                ListView lv = ad.getListView(); //this is a ListView with your "buds" in it
                lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
                {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id)
                    {
                        final String temp=adapter.getItem(position).toString();
                        Log.d("Long Click!","List Item #  " +temp+"was long clicked");
                        AlertDialog.Builder bb=new AlertDialog.Builder(MainActivity.this);

                        bb.setTitle("delete");
                        bb.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(position!=0)
                                {
                                    db.delete(DBHelper.TABLE_JP_TRAVEL,  DBHelper.NAME_CHINESE + " = '" + temp + "'", null);
                                }

                            }
                        });
                        bb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        bb.show();
                        return true;
                    }
                });
            }
        });
        ad.show();

    }

    private void camera() {
       Intent intent = new Intent(MainActivity.this, Camera.class);
        startActivity(intent);
        //finish();
    }


    private void stole(final LatLng place) {
        final String[] options = {"Bike stolen", "Scooter stolen"};
        Log.v("Stole", "");
        AlertDialog.Builder dialog_list = new AlertDialog.Builder(this);
        dialog_list.setTitle("Nearby");

        dialog_list.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String option;
                option = options[which];
                MapsActivity.mMap.clear();
                switch (which) {
                    case 0:
                        SQLiteDatabase temp = DH.getWritableDatabase();
                        Cursor c=temp.rawQuery("SELECT * FROM " + DBHelper.TABLE_STOLEN_BIKE, null);

                        if (c != null) {
                            mapsActivity.mMap.clear();
                            c.moveToFirst();
                            while (c.isAfterLast() == false) {
                                double longitude=c.getDouble(2);
                                double latitude=c.getDouble(1);
                                //Log.e(""+mapsActivity.mLastLocation.getLongitude(),"  "+mapsActivity.mLastLocation.getLatitude());

                                double dis=Distance(place.longitude,place.latitude,longitude,latitude);
                                if(dis<1500)
                                {
                                    Log.d("DISTANCE BIKE: ",""+dis);
                                    LatLng place = new LatLng(c.getDouble(1), c.getDouble(2));
                                    moveMap(place, c);

                                }
                                c.moveToNext();
                            }
                        }
                        break;
                    case 1:
                        temp = DH.getWritableDatabase();
                        c=temp.rawQuery("SELECT * FROM " + DBHelper.TABLE_STOLEN_SCOOTER, null);
                        fromwhichOption=3;
                        if (c != null) {
                            mapsActivity.mMap.clear();
                            c.moveToFirst();
                            while (c.isAfterLast() == false) {
                                double longitude = c.getDouble(2);
                                double latitude = c.getDouble(1);
                                Log.e("" + mapsActivity.mLastLocation.getLongitude(), "  " + mapsActivity.mLastLocation.getLatitude());

                                double dis = Distance(place.longitude, place.latitude, longitude, latitude);
                                if (dis < 1500) {
                                    Log.d("DISTANCE: ", "" + dis);
                                    LatLng place = new LatLng(c.getDouble(1), c.getDouble(2));
                                    moveMap(place, c);

                                }
                                c.moveToNext();
                            }
                        }
                        break;
                  /*  case 2:
                        temp = DH.getWritableDatabase();
                        c=temp.rawQuery("SELECT * FROM " + DBHelper.TABLE_STOLEN_CAR, null);
                        fromwhichOption=3;
                        if (c != null) {
                            mapsActivity.mMap.clear();
                            c.moveToFirst();
                            while (c.isAfterLast() == false) {
                                double longitude = c.getDouble(2);
                                double latitude = c.getDouble(1);
                                Log.e("" + mapsActivity.mLastLocation.getLongitude(), "  " + mapsActivity.mLastLocation.getLatitude());

                                double dis = Distance(mapsActivity.mLastLocation.getLongitude(), mapsActivity.mLastLocation.getLatitude(), longitude, latitude);
                                if (dis < 1500) {
                                    Log.d("DISTANCE: ", "" + dis);
                                    LatLng place = new LatLng(c.getDouble(1), c.getDouble(2));
                                    moveMap(place, c);

                                }
                                c.moveToNext();
                            }
                        }
                        break;*/
                }
            }
        });
        dialog_list.show();

    }

    private void nearby(final LatLng place, final String name) {
        final String[] options = {"景點","旅社","警察局","停車場","失竊"};
        new JsonTask().execute("http://data.tycg.gov.tw/api/v1/rest/datastore/0daad6e6-0632-44f5-bd25-5e1de1e9146f?format=json");

        AlertDialog.Builder dialog_list = new AlertDialog.Builder(MainActivity.this);
        dialog_list.setTitle("探索周邊");

        dialog_list.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MapsActivity.mMap.clear();
                switch (which) {
                    case 0:
                        fromwhichOption=6;
                        SQLiteDatabase temp = DH.getWritableDatabase();
                        Cursor c=temp.rawQuery("SELECT * FROM " + DBHelper.TABLE_JP_STORE, null);
                        if (c != null) {
                            mapsActivity.mMap.clear();
                            c.moveToFirst();
                            while (c.isAfterLast() == false) {
                                double longitude=c.getDouble(4);
                                double latitude=c.getDouble(3);

                                double dis=Distance(place.longitude,place.latitude,longitude,latitude);
                                if(dis<5000)
                                {
                                    Log.d("DISTANCE: ",""+dis);
                                    LatLng place = new LatLng(c.getDouble(3), c.getDouble(4));
                                    moveMap(place, c);

                                }
                                c.moveToNext();
                            }
                        }

                        c.close();
                        temp.close();
                        break;
                    case 1:
                        fromwhichOption=1;
                        temp = DH.getWritableDatabase();
                        c=temp.rawQuery("SELECT * FROM " + DBHelper.TABLE_SLEEP_JP, null);
                        if (c != null) {
                            mapsActivity.mMap.clear();
                            c.moveToFirst();
                            while (c.isAfterLast() == false) {
                                double longitude=c.getDouble(4);
                                double latitude=c.getDouble(3);

                                double dis=Distance(place.longitude,place.latitude,longitude,latitude);
                                if(dis<5000)
                                {
                                    Log.d("DISTANCE: ",""+dis);
                                    LatLng place = new LatLng(c.getDouble(3), c.getDouble(4));
                                    moveMap(place, c);

                                }
                                c.moveToNext();
                            }
                        }

                        c.close();
                        temp.close();
                        break;
                    case 2:
                        fromwhichOption=2;
                        temp = DH.getWritableDatabase();
                        c=temp.rawQuery("SELECT * FROM " + DBHelper.TABLE_POLICE, null);
                        if (c != null) {
                            mapsActivity.mMap.clear();
                            c.moveToFirst();
                            while (c.isAfterLast() == false) {
                                double longitude=c.getDouble(4);
                                double latitude=c.getDouble(3);


                                double dis=Distance(place.longitude,place.latitude,longitude,latitude);
                                if(dis<5000)
                                {
                                    Log.d("DISTANCE: ",""+dis);
                                    LatLng place = new LatLng(c.getDouble(3), c.getDouble(4));
                                    moveMap(place, c);

                                }
                                c.moveToNext();
                            }
                        }

                        c.close();
                        temp.close();
                        break;

                    case 3:
                        temp = DH.getWritableDatabase();
                        c=temp.rawQuery("SELECT * FROM " + DBHelper.TABLE_PARK, null);
                        if (c != null) {
                            mapsActivity.mMap.clear();
                            c.moveToFirst();
                            while (c.isAfterLast() == false) {
                                double longitude=c.getDouble(3);
                                double latitude=c.getDouble(2);
                                double dis=Distance(place.longitude,place.latitude,longitude,latitude);
                                Log.e("dis"," "+dis);
                                if(dis<5000)
                                {
                                    fromwhichOption=4;
                                    Log.d("DISTANCE: ",""+dis);
                                    LatLng place = new LatLng(c.getDouble(2), c.getDouble(3));
                                    moveMap(place, c);

                                }
                                c.moveToNext();
                            }
                        }

                        c.close();
                        temp.close();
                        break;
                    case 4:
                        fromwhichOption=5;
                        stole(place);
                        break;

                }
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(place);
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                mLocationMarker = MapsActivity.mMap.addMarker(markerOptions);
                mLocationMarker.setTitle(name);
                CameraPosition cameraPosition =
                        new CameraPosition.Builder()
                                .target(place)
                                .zoom(15)
                                .build();

                // 使用動畫的效果移動地圖
                MapsActivity.mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });

        dialog_list.show();
    }

    private void park() {
       //new JsonTask().execute("http://data.tycg.gov.tw/api/v1/rest/datastore/0daad6e6-0632-44f5-bd25-5e1de1e9146f?format=json");
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1);
        adapter.add("stations nearby");
        SQLiteDatabase db=DH.getReadableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM " + DBHelper.TABLE_PARK , null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            adapter.add(c.getString(1));
            c.moveToNext();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if(i!=0)
                {
                    SQLiteDatabase temp = DH.getReadableDatabase();
                    Cursor c=temp.rawQuery("SELECT * FROM " + DBHelper.TABLE_PARK + " WHERE " + DBHelper.NAME_CHINESE + " = '" + adapter.getItem(i) + "'", null);
                    c.moveToFirst();
                    mapsActivity.mMap.clear();
                    LatLng place = new LatLng(c.getDouble(2), c.getDouble(3));
                    moveMap(place, c);
                    c.close();
                    temp.close();
                }
                else
                {
                    SQLiteDatabase temp = DH.getWritableDatabase();
                    Cursor c=temp.rawQuery("SELECT * FROM " + DBHelper.TABLE_PARK, null);

                    if (c != null) {
                        mapsActivity.mMap.clear();
                        c.moveToFirst();
                        while (c.isAfterLast() == false) {
                            double longitude = c.getDouble(3);
                            double latitude = c.getDouble(2);
                            Log.e("" + mapsActivity.mLastLocation.getLongitude(), "  " + mapsActivity.mLastLocation.getLatitude());

                            double dis = Distance(mapsActivity.mLastLocation.getLongitude(), mapsActivity.mLastLocation.getLatitude(), longitude, latitude);
                            if (dis < 5000) {
                                Log.d("DISTANCE: ", "" + dis);
                                LatLng place = new LatLng(c.getDouble(2), c.getDouble(3));
                                moveMap(place, c);

                            }
                            c.moveToNext();
                        }
                        currect();
                    }
                }
            }
        });
        builder.show();
    }

    private void police() {
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1);
        adapter.add("stations nearby");
        SQLiteDatabase db=DH.getReadableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM " + DBHelper.TABLE_POLICE , null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            adapter.add(c.getString(1));
           // LatLng place = new LatLng(c.getDouble(3), c.getDouble(4));
            //moveMap(place, c);
            c.moveToNext();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                    if(i!=0)
                    {
                        SQLiteDatabase temp = DH.getReadableDatabase();
                        Cursor c=temp.rawQuery("SELECT * FROM " + DBHelper.TABLE_POLICE + " WHERE " + DBHelper.NAME_CHINESE + " = '" + adapter.getItem(i) + "'", null);
                        c.moveToFirst();
                        mapsActivity.mMap.clear();
                        LatLng place = new LatLng(c.getDouble(3), c.getDouble(4));
                        moveMap(place, c);
                        c.close();
                        temp.close();
                    }



               else
                {
                    SQLiteDatabase temp = DH.getWritableDatabase();
                    Cursor c=temp.rawQuery("SELECT * FROM " + DBHelper.TABLE_POLICE, null);

                    if (c != null) {
                        mapsActivity.mMap.clear();
                        c.moveToFirst();
                        while (c.isAfterLast() == false) {
                            double longitude=c.getDouble(4);
                            double latitude=c.getDouble(3);
                            Log.e(""+mapsActivity.mLastLocation.getLongitude(),"  "+mapsActivity.mLastLocation.getLatitude());

                            double dis=Distance(mapsActivity.mLastLocation.getLongitude(),mapsActivity.mLastLocation.getLatitude(),longitude,latitude);
                            if(dis<5000)
                            {
                                Log.d("DISTANCE: ",""+dis);
                                LatLng place = new LatLng(c.getDouble(3), c.getDouble(4));
                                moveMap(place, c);

                            }
                            c.moveToNext();
                        }
                        currect();
                    }
                }
            }
        });
        builder.show();
    }

    private void currect() {
        MarkerOptions markerOptions = new MarkerOptions();
        LatLng current=new LatLng(mapsActivity.mLastLocation.getLatitude(),mapsActivity.mLastLocation.getLongitude());
        markerOptions.position(current);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        mLocationMarker = MapsActivity.mMap.addMarker(markerOptions);
        mLocationMarker.setTitle("Your current location");
        CameraPosition cameraPosition =
                new CameraPosition.Builder()
                        .target(current)
                        .zoom(15)
                        .build();

        // 使用動畫的效果移動地圖
        MapsActivity.mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    private double Distance(double longitude1, double latitude1, double longitude2,double latitude2)
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

    private void switch_hotel() {
        final String[] options = {"English", "Chinese", "Japanese"};
        Log.v("Language", "");
        AlertDialog.Builder dialog_list = new AlertDialog.Builder(this);
        dialog_list.setTitle("Language");

        dialog_list.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String option;
                option = options[which];
                MapsActivity.mMap.clear();
                switch (which) {
                    case 0:
                        SQLiteDatabase db = DH.getWritableDatabase();
                        Cursor cd = db.rawQuery("SELECT * FROM " + DBHelper.TABLE_SLEEP_ENG, null);
                        second_alertDialog(cd,4);
                        break;
                    case 1:
                        fromwhichOption=7;
                        db = DH.getWritableDatabase();
                        cd = db.rawQuery("SELECT * FROM " + DBHelper.TABLE_SLEEP_CH, null);
                        if (cd != null) {
                            second_alertDialog(cd,5);
                        }
                        cd.close();
                       // db.close();
                        break;
                    case 2:
                        db = DH.getWritableDatabase();
                        cd = db.rawQuery("SELECT * FROM " + DBHelper.TABLE_SLEEP_JP, null);
                        if (cd != null) {
                            second_alertDialog(cd,6);
                        }
                        cd.close();
                     //   db.close();
                        break;
                }
            }
        });
        dialog_list.show();
    }

    private void switch_travel() {
        final String[] options = {"English", "Japanese", "Chinese","Korean"};
        Log.v("Language", "");
        AlertDialog.Builder dialog_list = new AlertDialog.Builder(this);
        dialog_list.setTitle("Language");

        dialog_list.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String option;
                option = options[which];
                MapsActivity.mMap.clear();
                switch (which) {
                    case 0:
                        fromwhichOption=1;
                        SQLiteDatabase db = DH.getWritableDatabase();
                        Cursor c = db.rawQuery("SELECT * FROM " + DBHelper.TABLE_STORE_ENG, null);
                        if (c != null) {
                            second_alertDialog(c,0);
                        }
                        c.close();
                       // db.close();
                        break;
                    case 1:
                        db = DH.getWritableDatabase();
                        c = db.rawQuery("SELECT * FROM " + DBHelper.TABLE_JP_STORE, null);
                        if (c != null) {
                            second_alertDialog(c,1);
                        }
                        c.close();
                       // db.close();
                        break;
                    case 2:
                        fromwhichOption=7;
                        db = DH.getWritableDatabase();
                        c = db.rawQuery("SELECT * FROM " + DBHelper.TABLE_TRAVEL_POT, null);
                        if (c != null) {
                            second_alertDialog(c,2);
                        }
                        c.close();
                     //   db.close();
                        break;
                    case 3:
                        db = DH.getWritableDatabase();
                        c = db.rawQuery("SELECT * FROM " + DBHelper.TABLE_PLAY_KOERA, null);
                        if (c != null) {
                            second_alertDialog(c,3);
                        }
                        c.close();
                     //   db.close();
                        break;
                }
            }
        });
        dialog_list.show();

    }
    public void second_alertDialog(Cursor c, final int type) {
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1);
        adapter.add("Nearby ");
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            adapter.add(c.getString(1));
            LatLng place = new LatLng(c.getDouble(3), c.getDouble(4));
            moveMap(place, c);
            c.moveToNext();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i != 0) {
                    SQLiteDatabase temp = DH.getWritableDatabase();
                    Cursor c=null;

                    switch (type)
                    {
                        case 0:
                            fromwhichOption=1;
                            c=temp.rawQuery("SELECT * FROM " + DBHelper.TABLE_STORE_ENG + " WHERE " + DBHelper.NAME_CHINESE + " = '" + adapter.getItem(i) + "'", null);
                            break;
                        case 1:
                            c=temp.rawQuery("SELECT * FROM " + DBHelper.TABLE_JP_STORE + " WHERE " + DBHelper.NAME_CHINESE + " = '" + adapter.getItem(i) + "'", null);
                            break;
                        case 2:
                            fromwhichOption=7;
                            c=temp.rawQuery("SELECT * FROM " + DBHelper.TABLE_TRAVEL_POT + " WHERE " + DBHelper.NAME_CHINESE + " = '" + adapter.getItem(i) + "'", null);
                            break;
                        case 3:
                            c=temp.rawQuery("SELECT * FROM " + DBHelper.TABLE_PLAY_KOERA + " WHERE " + DBHelper.NAME_CHINESE + " = '" + adapter.getItem(i) + "'", null);
                            break;
                        case 4:
                            Log.e("sleeeeep!","       ENGLISH");
                            c=temp.rawQuery("SELECT * FROM " + DBHelper.TABLE_SLEEP_ENG + " WHERE " + DBHelper.NAME_CHINESE + " = '" + adapter.getItem(i) + "'", null);
                            break;
                        case 5:
                            c=temp.rawQuery("SELECT * FROM " + DBHelper.TABLE_SLEEP_CH + " WHERE " + DBHelper.NAME_CHINESE + " = '" + adapter.getItem(i) + "'", null);
                            break;
                        case 6:
                            c=temp.rawQuery("SELECT * FROM " + DBHelper.TABLE_SLEEP_JP + " WHERE " + DBHelper.NAME_CHINESE + " = '" + adapter.getItem(i) + "'", null);
                            break;
                    }

                    if (c != null) {
                        c.moveToFirst();
                        while (c.isAfterLast() == false) {
                            mapsActivity.mMap.clear();
                            LatLng place = new LatLng(c.getDouble(3), c.getDouble(4));
                            Log.e("GOGO","switttttch!!!  "+c.getString(1));
                            choosed=c;
                            Log.e("GOGO","switttttch!!!"+choosed.getString(1));
                            addtoBASE();
                            moveMap(place, c);
                            c.moveToNext();
                        }
                    }
                    /*else if(c!=null)
                    {
                        c.moveToFirst();
                        while (c.isAfterLast() == false) {
                            mapsActivity.mMap.clear();
                            LatLng place = new LatLng(c.getDouble(3), c.getDouble(4));

                            moveMap(place, c);
                            c.moveToNext();
                        }
                    }*/
                    //c.close();
                    //temp.close();
                }
                else
                {
                   switch (type)
                   {
                       case 0:
                           SQLiteDatabase temp = DH.getWritableDatabase();
                           Cursor c=temp.rawQuery("SELECT * FROM " + DBHelper.TABLE_STORE_ENG, null);

                           if (c != null)
                           {
                               mapsActivity.mMap.clear();
                               c.moveToFirst();
                               while (c.isAfterLast() == false) {
                                   double longitude=c.getDouble(4);
                                   double latitude=c.getDouble(3);
                                   Log.e(""+mapsActivity.mLastLocation.getLongitude(),"  "+mapsActivity.mLastLocation.getLatitude());

                                   double dis=Distance(mapsActivity.mLastLocation.getLongitude(),mapsActivity.mLastLocation.getLatitude(),longitude,latitude);
                                   if(dis<5000)
                                   {
                                       Log.d("DISTANCE: ",""+dis);
                                       LatLng place = new LatLng(c.getDouble(3), c.getDouble(4));
                                       moveMap(place, c);

                                   }
                                   c.moveToNext();
                               }
                               currect();
                           }
                           break;
                       case 1:
                            temp = DH.getWritableDatabase();
                            c=temp.rawQuery("SELECT * FROM " + DBHelper.TABLE_JP_STORE, null);

                           if (c != null)
                           {
                               mapsActivity.mMap.clear();
                               c.moveToFirst();
                               while (c.isAfterLast() == false) {
                                   double longitude=c.getDouble(4);
                                   double latitude=c.getDouble(3);
                                   Log.e(""+mapsActivity.mLastLocation.getLongitude(),"  "+mapsActivity.mLastLocation.getLatitude());

                                   double dis=Distance(mapsActivity.mLastLocation.getLongitude(),mapsActivity.mLastLocation.getLatitude(),longitude,latitude);
                                   if(dis<5000)
                                   {
                                       Log.d("DISTANCE: ",""+dis);
                                       LatLng place = new LatLng(c.getDouble(3), c.getDouble(4));
                                       moveMap(place, c);

                                   }
                                   c.moveToNext();
                               }
                               currect();
                           }
                           break;
                       case 2:
                           temp = DH.getWritableDatabase();
                           c=temp.rawQuery("SELECT * FROM " + DBHelper.TABLE_TRAVEL_POT, null);

                           if (c != null)
                           {
                               mapsActivity.mMap.clear();
                               c.moveToFirst();
                               while (c.isAfterLast() == false) {
                                   double longitude=c.getDouble(4);
                                   double latitude=c.getDouble(3);
                                   Log.e(""+mapsActivity.mLastLocation.getLongitude(),"  "+mapsActivity.mLastLocation.getLatitude());

                                   double dis=Distance(mapsActivity.mLastLocation.getLongitude(),mapsActivity.mLastLocation.getLatitude(),longitude,latitude);
                                   if(dis<5000)
                                   {
                                       Log.d("DISTANCE: ",""+dis);
                                       LatLng place = new LatLng(c.getDouble(3), c.getDouble(4));
                                       moveMap(place, c);

                                   }
                                   c.moveToNext();
                               }
                               currect();
                           }
                           break;
                       case 3:
                           temp = DH.getWritableDatabase();
                           c=temp.rawQuery("SELECT * FROM " + DBHelper.TABLE_PLAY_KOERA, null);

                           if (c != null)
                           {
                               mapsActivity.mMap.clear();
                               c.moveToFirst();
                               while (c.isAfterLast() == false) {
                                   double longitude=c.getDouble(4);
                                   double latitude=c.getDouble(3);
                                   Log.e(""+mapsActivity.mLastLocation.getLongitude(),"  "+mapsActivity.mLastLocation.getLatitude());

                                   double dis=Distance(mapsActivity.mLastLocation.getLongitude(),mapsActivity.mLastLocation.getLatitude(),longitude,latitude);
                                   if(dis<5000)
                                   {
                                       Log.d("DISTANCE: ",""+dis);
                                       LatLng place = new LatLng(c.getDouble(3), c.getDouble(4));
                                       moveMap(place, c);

                                   }
                                   c.moveToNext();
                               }
                               currect();
                           }
                           break;
                       case 4:
                           temp = DH.getWritableDatabase();
                           c=temp.rawQuery("SELECT * FROM " + DBHelper.TABLE_SLEEP_ENG, null);

                           if (c != null)
                           {
                               mapsActivity.mMap.clear();
                               c.moveToFirst();
                               while (c.isAfterLast() == false) {
                                   double longitude=c.getDouble(4);
                                   double latitude=c.getDouble(3);
                                   Log.e(""+mapsActivity.mLastLocation.getLongitude(),"  "+mapsActivity.mLastLocation.getLatitude());

                                   double dis=Distance(mapsActivity.mLastLocation.getLongitude(),mapsActivity.mLastLocation.getLatitude(),longitude,latitude);
                                   if(dis<5000)
                                   {
                                       Log.d("DISTANCE: ",""+dis);
                                       LatLng place = new LatLng(c.getDouble(3), c.getDouble(4));
                                       moveMap(place, c);

                                   }
                                   c.moveToNext();
                               }
                               currect();
                           }
                           break;
                       case 5:
                           temp = DH.getWritableDatabase();
                           c=temp.rawQuery("SELECT * FROM " + DBHelper.TABLE_SLEEP_CH, null);

                           if (c != null)
                           {
                               mapsActivity.mMap.clear();
                               c.moveToFirst();
                               while (c.isAfterLast() == false) {
                                   double longitude=c.getDouble(4);
                                   double latitude=c.getDouble(3);
                                   Log.e(""+mapsActivity.mLastLocation.getLongitude(),"  "+mapsActivity.mLastLocation.getLatitude());

                                   double dis=Distance(mapsActivity.mLastLocation.getLongitude(),mapsActivity.mLastLocation.getLatitude(),longitude,latitude);
                                   if(dis<5000)
                                   {
                                       Log.d("DISTANCE: ",""+dis);
                                       LatLng place = new LatLng(c.getDouble(3), c.getDouble(4));
                                       moveMap(place, c);

                                   }
                                   c.moveToNext();
                               }
                               currect();
                           }
                           break;
                       case 6:
                           temp = DH.getWritableDatabase();
                           c=temp.rawQuery("SELECT * FROM " + DBHelper.TABLE_SLEEP_JP, null);

                           if (c != null)
                           {
                               mapsActivity.mMap.clear();
                               c.moveToFirst();
                               while (c.isAfterLast() == false) {
                                   double longitude=c.getDouble(4);
                                   double latitude=c.getDouble(3);
                                   Log.e(""+mapsActivity.mLastLocation.getLongitude(),"  "+mapsActivity.mLastLocation.getLatitude());

                                   double dis=Distance(mapsActivity.mLastLocation.getLongitude(),mapsActivity.mLastLocation.getLatitude(),longitude,latitude);
                                   if(dis<5000)
                                   {
                                       Log.d("DISTANCE: ",""+dis);
                                       LatLng place = new LatLng(c.getDouble(3), c.getDouble(4));
                                       moveMap(place, c);

                                   }
                                   c.moveToNext();
                               }
                               currect();
                           }
                           break;
                   }
                }


            }
        });
        builder.show();
    }

    public void moveMap(LatLng place, Cursor c) {
        // 建立地圖攝影機的位置物件
        CameraPosition cameraPosition =
                new CameraPosition.Builder()
                        .target(place)
                        .zoom(15)
                        .build();

        // 使用動畫的效果移動地圖
        MapsActivity.mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(place);

        switch (fromwhichOption)
        {

            case 7://chinese store
                markerOptions.title(c.getString(1));
                markerOptions.snippet(c.getString(6));
                mapsActivity.mMap.setOnMarkerClickListener(gmapbuttonListener);
                break;
            case 6://travel
                markerOptions.title(c.getString(1));
                markerOptions.snippet(c.getString(8));
                choosed=c;
                mapsActivity.mMap.setOnMarkerClickListener(gmapbuttonListener);
                break;
            case 1://hotel
                markerOptions.title(c.getString(1));
                markerOptions.snippet(c.getString(7));
                mapsActivity.mMap.setOnMarkerClickListener(gmapbuttonListener);
                break;
            case 2://police
                markerOptions.title(c.getString(1));
                markerOptions.snippet(c.getString(2));
                mapsActivity.mMap.setOnMarkerClickListener(gmapTextListener);
                break;

            case 4://parking
                markerOptions.title(c.getString(1));
                int id=c.getInt(0)-1;
                String str=c.getString(4)+"\n"+"全部車位："+c.getString(6)+"\n"+"剩餘車位：" +opentime[id]+"\n"+c.getString(5);
                markerOptions.snippet(str);
                mapsActivity.mMap.setOnMarkerClickListener(gmapTextListener);
                break;
            case 5://stolen
                Log.e("into bike","ohoh");
                markerOptions.title("自行車竊盜");
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));

                break;
            case 3:
                markerOptions.title("機車竊盜");
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
                break;

        }
        if(fromwhichOption!=3)markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
        mLocationMarker = MapsActivity.mMap.addMarker(markerOptions);

        MapsActivity.mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            // Defines the contents of the InfoWindow
            @Override
            public View getInfoContents(Marker arg0) {
                String urlString = arg0.getSnippet();
                Log.e("urlString= " + urlString, "");
                View v = getLayoutInflater().inflate(R.layout.show_on_map, null);
                TextView tvLat = (TextView) v.findViewById(R.id.TITLE);
                tvLat.setText(arg0.getTitle());

                return v;

            }
        });
    }

    public void getJson(String name,int type) {
        Log.e("into Json"+name,"type="+type);

        try {
            InputStreamReader inputStreamReader = new InputStreamReader(getAssets().open(name),"UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            inputStreamReader.close();
            String result = stringBuilder.toString();
            arr = new JSONArray(result);

            try {
                name_Ch=new String[arr.length()];
                latitude=new double[arr.length()];
                longitude=new double[arr.length()];
                web=new String[arr.length()];
                address=new String[arr.length()];
                tel=new String[arr.length()];
                opentime=new String[arr.length()];
                told=new String[arr.length()];

                if(type==0)
                {
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject lib = arr.getJSONObject(i);
                        name_Ch[i]=lib.getString("_Name");
                        latitude[i]=lib.getDouble("_Py");
                        longitude[i]=lib.getDouble("_Px");
                        web[i]=lib.getString("_TYWebsite");
                        address [i]=lib.getString("_Add");
                        tel[i]=lib.getString("_Tel");
                        opentime[i]=lib.getString("_Opentime");
                        told [i]=lib.getString("_Toldescribe");
                        Log.e(""+name_Ch[i],"");
                    }
                }
                else if(type==1)//警察局
                {
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject lib = arr.getJSONObject(i);
                        // name_Ch[i]=lib.getString("服務據點");
                        latitude[i]=lib.getDouble("lat");
                        longitude[i]=lib.getDouble("lon");
                        // address [i]=lib.getString("地址");
                        // tel[i]=lib.getString("電話");
                    }
                }
                else if(type==2)//停車的
                {

                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject lib = arr.getJSONObject(i);
                        name_Ch[i]=lib.getString("parkName");
                        latitude[i]=lib.getDouble("wgsY");
                        longitude[i]=lib.getDouble("wgsX");
                        address [i]=lib.getString("address");
                        //tel->totalspace
                        tel[i]=lib.getString("totalSpace");
                        //opentime->surplusSpace
                        opentime[i]=lib.getString("surplusSpace");
                        told [i]=lib.getString("payGuide");
                    }
                }
                else if(type==3)//被偷的
                {
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject lib = arr.getJSONObject(i);
                        latitude[i]=lib.getDouble("WGS84經緯度座標N");
                        longitude[i]=lib.getDouble("WGS84經緯度座標E");
                    }
                }
                else if(type==4)//中文住宿
                {
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject lib = arr.getJSONObject(i);
                        name_Ch[i]=lib.getString("Name");
                        latitude[i]=lib.getDouble("Py");
                        longitude[i]=lib.getDouble("Px");
                        web[i]=lib.getString("TYWebsite");
                        address [i]=lib.getString("Add");
                        tel[i]=lib.getString("Tel");
                        told [i]=lib.getString("Toldescribe");

                    }
                }
                else if(type==5)
                {
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject lib = arr.getJSONObject(i);
                        name_Ch[i]=lib.getString("Name");
                        latitude[i]=lib.getDouble("Py");
                        longitude[i]=lib.getDouble("Px");
                        web[i]=lib.getString("TYWebsite");
                        address [i]=lib.getString("Add");
                        tel[i]=lib.getString("Tel");
                        opentime[i]=lib.getString("Opentime");
                        told [i]=lib.getString("Toldescribe");
                        Log.e(""+name_Ch[i],"");
                    }
                }




            } catch (JSONException e) {
                e.printStackTrace();

            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private class JsonTask extends AsyncTask<String, String, String> {
        ProgressDialog pd;
        protected void onPreExecute() {
            super.onPreExecute();

            pd = new ProgressDialog(MainActivity.this);
            pd.setMessage("Please wait");
            pd.setCancelable(false);
            pd.show();
        }

        protected String doInBackground(String... params) {
            Log.e("inot back","");

            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                    Log.e("into back","yaaaa");
                }
                String result = buffer.toString();
                JSONObject jObj = new JSONObject(result);
                JSONObject response = jObj.getJSONObject("result");
                JSONArray arr = response.getJSONArray("records");
                //arr = new JSONArray(result);

                name_Ch=new String[arr.length()];
                latitude=new double[arr.length()];
                longitude=new double[arr.length()];
                web=new String[arr.length()];
                address=new String[arr.length()];
                tel=new String[arr.length()];
                opentime=new String[arr.length()];
                told=new String[arr.length()];

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject lib = arr.getJSONObject(i);
                    name_Ch[i]=lib.getString("parkName");
                    latitude[i]=lib.getDouble("wgsY");
                    longitude[i]=lib.getDouble("wgsX");
                    address [i]=lib.getString("address");
                    //tel->totalspace
                    tel[i]=lib.getString("totalSpace");
                    //opentime->surplusSpace
                    opentime[i]=lib.getString("surplusSpace");
                    told [i]=lib.getString("payGuide");
                    Log.e("parkname="+name_Ch[i]+"  surplus","  "+opentime[i]);
                }

                for(int i=0;i<arr.length();i++)
                {
                    Log.e("surplus=",opentime[i]);
                }

               // SQLiteDatabase db=DH.getWritableDatabase();
              //  db.execSQL("DROP TABLE IF EXISTS "+DBHelper.TABLE_PARK);

               // ContentValues values=new ContentValues();
                int id;
                /*for (int i = 0; i <arr.length(); i++) {
                   // values.put(DBHelper.NAME_CHINESE, name_Ch[i]);
                   // values.put(DBHelper.LATITUDE_,latitude[i]);
                   // values.put(DBHelper.LONGITUDE_,longitude[i]);
                    //values.put(DBHelper.totalSpace,tel[i]);

                    values.put(DBHelper.SurplusSpace,opentime[i]);
                  //  values.put(DBHelper.Toldescribe,told[i]);
                    id=i+1;
                    db.update(DBHelper.TABLE_PARK, values, "_ID" + "=" + id, null);
                }*/
/*
                Cursor c=db.rawQuery("SELECT * FROM "+DBHelper.TABLE_PARK, null);
                if( c != null ) {
                    c.moveToFirst();
                    while (c.isAfterLast() == false) {
                        Log.e("CP"," field0:"+c.getString(1)+" surplus: "+c.getString(7));
                        c.moveToNext();
                    }
                    c.close();
                }*/


                return buffer.toString();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;

        }
            @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (pd.isShowing()){
                pd.dismiss();
            }

        }
    }



}
