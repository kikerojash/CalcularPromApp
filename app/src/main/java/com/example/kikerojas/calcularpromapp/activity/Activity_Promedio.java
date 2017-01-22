package com.example.kikerojas.calcularpromapp.activity;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.kikerojas.calcularpromapp.R;
import com.example.kikerojas.calcularpromapp.Utils.Constans;
import com.example.kikerojas.calcularpromapp.Utils.CustomTabsAdapter;
import com.example.kikerojas.calcularpromapp.entidades.Entities_Promedio;
import com.example.kikerojas.calcularpromapp.fragments.Fragment_Promedio;
import com.example.kikerojas.calcularpromapp.fragments.Fragment_SegundoPromedio;
import com.example.kikerojas.calcularpromapp.fragments.Fragment_Tercer_Promedio;
import com.example.kikerojas.calcularpromapp.holders.Holder_Promedio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kikerojas on 30/09/2016.
 */

public class Activity_Promedio extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener, Fragment_Promedio.OnPromedioClickListener, Fragment_SegundoPromedio.OnPromedioClickListener,Fragment_Tercer_Promedio.OnPromedioClickListener, View.OnClickListener {

    private static final String TAG = "Activity_Promedio";
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.viewCharges)
    ViewPager mainViewPager;
    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    @BindView(R.id.tv_total)
    TextView txtotal;
    @BindView(R.id.tv_date)
    TextView txtdate;
    @BindView(R.id.btndialog)
    FloatingActionButton btndialog;


    private CustomTabsAdapter tabsAdapter;

    AlertDialog alertDialog;


    Entities_Promedio addpromedio;

    Spinner SptipoUnidad;
    Button btnsave;
    Button btn_cancel;
    EditText et_promedio1;
    EditText et_promedio2;
    EditText et_promedio3;
    TextView btndate;
    /*Operations*/

    Double mPromed1;
    Double mPromed2;
    Double mPromed3;
    Double mTotal;
    /*Fin Operations*/


    final Calendar calendar = Calendar.getInstance();
    int yy = calendar.get(Calendar.YEAR);
    int mm = calendar.get(Calendar.MONTH);
    int dd = calendar.get(Calendar.DAY_OF_MONTH);

    //varaible Globales


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_promedio);
        ButterKnife.bind(this);
        setTabsAdapterFragment();
        //mAinposition();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        setToolbar();
        btndialog.setOnClickListener(this);
        setupCollapsingToolbar();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
       // getSupportActionBar().setIcon(R.drawable.ic_menu_camera);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        int titleicon = 1;
        //setTitle("TESTING");
       // getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_camera);
       // getActionBar().setIcon(R.drawable.ic_menu_camera);

       // getSupportActionBar().getTitle();
//        getActionBar().setHomeButtonEnabled(true);
//        getActionBar().setDisplayHomeAsUpEnabled(true);



        //getSupportActionBar().setLogo(R.mipmap.ic_launcher);

       // getSupportActionBar().setIcon(R.drawable.ic_menu_camera);



    }

    private void setupCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(
                R.id.collapse_toolbar2);

        collapsingToolbar.setTitleEnabled(false);

    }

    int getImage(int tipocarga) {
        if (tipocarga == 1) {
            return R.drawable.ic_menu_camera;
        } else {
            return R.drawable.ic_menu_camera;
        }
    }


    private void setTabsAdapterFragment() {
        tabsAdapter = new CustomTabsAdapter(getSupportFragmentManager());
        tabsAdapter.addFragment(new Fragment_Promedio(), getString(R.string.first_second));
        tabsAdapter.addFragment(new Fragment_SegundoPromedio(), getString(R.string.three));
        tabsAdapter.addFragment(new Fragment_Tercer_Promedio(), getString(R.string.third));
        mainViewPager.setAdapter(tabsAdapter);
        tabLayout.setupWithViewPager(mainViewPager);
    }

    private void setToolbar() {
        // Añadir la Toolbar
        setSupportActionBar(toolbar);
        //toolbar.setLogo(R.drawable.ic_menu_camera);
        if (getSupportActionBar() != null) //
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        }
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
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    public Fragment getFragment(int pos) {
        return tabsAdapter.getItem(pos);
    }

    private List<Fragment> getFragments() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        List<Fragment> fragmentsClean = new ArrayList<>();
        if (fragments != null) {
            Log.d(TAG, "getSupportFragmentManager().getFragments().size(): " + fragments.size());
            for (int i = 0; i < fragments.size(); i++) {
                Log.d(TAG, "FRAGMENT: " + i);
                if ((fragments.get(i) instanceof Fragment_Promedio || fragments.get(i) instanceof Fragment_SegundoPromedio || fragments.get(i) instanceof Fragment_Tercer_Promedio)) {
                    fragmentsClean.add(fragments.get(i));
                }

                if (fragments.get(i) != null) {
                    Log.d(TAG, "TAG : " + fragments.get(i).getTag());
                }
            }
        }
        return fragmentsClean;
    }


    @Override
    public void onPromedioClickListener(int position, Entities_Promedio entitiesPromedio, View view) {
        switch (position) {
            case Constans.CLICK_EDITAR_PROMEDIO:
                break;
            case Constans.CLICK_ELIMINAR_PROMEDIO:
                break;
            default:
                break;
        }
    }


    @Override
    public void onAddNewPromedio(Double Total) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btndialog:
                alertDialog = null;
                mAinposition();
                //inflate_dialog();
                break;
            case R.id.btn_save:
                alertDialog = null;

                //inflate_dialog();
                break;
            case R.id.btn_cancel:
                alertDialog.dismiss();
                break;
        }
    }


    String date = "";


    public void inflate_dialog() {

        View layout_dialog_promedios = View.inflate(this, R.layout.activity_dialog_nuevo_promedio, null);

        final Button btnsave = (Button) layout_dialog_promedios.findViewById(R.id.btn_save);
        final Button btn_cancel = (Button) layout_dialog_promedios.findViewById(R.id.btn_cancel);
        final EditText et_promedio1 = (EditText) layout_dialog_promedios.findViewById(R.id.et_promedio1);
        final EditText et_promedio2 = (EditText) layout_dialog_promedios.findViewById(R.id.et_promedio2);
        final EditText et_promedio3 = (EditText) layout_dialog_promedios.findViewById(R.id.et_promedio3);
        final TextView btndate = (TextView) layout_dialog_promedios.findViewById(R.id.txt_date);
        SptipoUnidad = (Spinner) layout_dialog_promedios.findViewById(R.id.spinnerTipo);


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set title
        alertDialogBuilder.setTitle("Nuevo Promedio");

        // set dialog message
        final AlertDialog.Builder builder = alertDialogBuilder
                .setView(layout_dialog_promedios)
                .setCancelable(true);
        btnsave.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
        //btnsave.setOnClickListener(this);



            /*DATEPICKER*/
        btndate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DatePickerDialog datePicker = new DatePickerDialog(Activity_Promedio.this, new DatePickerDialog.OnDateSetListener() {
                    @Override

                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        date = String.valueOf(dayOfMonth) + "/" + String.valueOf(monthOfYear + 1) + "/" + String.valueOf(year);


                        btndate.setText(date);
                    }
                }, yy, mm, dd);
                datePicker.show();
            }
        });


        ArrayAdapter<String> spinnerCountShoesArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_TipoUnidad));
        SptipoUnidad.setAdapter(spinnerCountShoesArrayAdapter);


        /*DATEPICKER*/


        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String captureItem = SptipoUnidad.getSelectedItem().toString();
                if (et_promedio1.getText().toString().equals("")) {
                    et_promedio1.setError("Ingrese Promedio");
                }
                if (et_promedio2.getText().toString().equals("")) {
                    et_promedio2.setError("Ingrese Promedio");
                } else {
                    Double mPromed1 = Double.valueOf(et_promedio1.getText().toString());
                    Double mPromed2 = Double.valueOf(et_promedio2.getText().toString());
                    Double mPromed3 = Double.valueOf(et_promedio3.getText().toString());
                    Double mTotal = 0.30 * mPromed1 + 0.30 * mPromed2 + 0.40 * mPromed3;
                    addpromedio = new Entities_Promedio(captureItem, date,
                            mPromed1,
                            mPromed2,
                            mPromed3,
                            mTotal);

                    Log.d(TAG, "SAVEITEM :" + addpromedio.getmTipoUnidad());
                    Fragment_Promedio firstfragment = Fragments_Promedio();
                    Fragment_SegundoPromedio secondFragment = Fragments_Second();
                    switch (mainViewPager.getCurrentItem()) {
                        //SEARCH FRAGMENT
                        case 0:
                            Log.d(TAG, "mainViewPager" + mainViewPager.getCurrentItem());
                            ((Fragment_Promedio) firstfragment).addnewPromedio(addpromedio); //obtenemos la instancia del fragmento
                            // mTiUnidad.setText(mTipoUnidad);
                            break;
                        case 1:

                            ((Fragment_SegundoPromedio) secondFragment).addnewPromedio(addpromedio); //obtenemos la instancia del fragmento
                            Log.d(TAG, "mainViewPager" + mainViewPager.getCurrentItem());
                            break;
                    }

                    alertDialog.dismiss();
                }
            }


        });
        alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();

    }




        /*Primer Fragmento*/

    private Fragment_Promedio Fragments_Promedio() {

        List<Fragment> fragments = getFragments();
        if (fragments != null) {
            if (fragments.size() < 2) {
                return null;
            }
            if (fragments.get(0) instanceof Fragment_Promedio) {
                Log.d(TAG, "fragments.get(0) instanceof Fragment_Promedio");
                return (Fragment_Promedio) fragments.get(0);
            } else {
                Log.d(TAG, "fragments.get(0) instanceof Fragment_Promedio: " + null);
                return null;
            }
        } else {
            return null;
        }

    }
    /*Segundo Fragmento*/


    private Fragment_SegundoPromedio Fragments_Second() {

        List<Fragment> fragments = getFragments();
        if (fragments != null) {

            if (fragments.get(1) instanceof Fragment_SegundoPromedio) {
                Log.d(TAG, "fragments.get(1) instanceof Fragment_SegundoPromedio");
                return (Fragment_SegundoPromedio) fragments.get(1);
            } else {
                Log.d(TAG, "fragments.get(1) instanceof Fragment_SegundoPromedio: " + null);
                return null;
            }
        } else {
            return null;
        }

    }

    private Fragment_Tercer_Promedio Fragments_third() {

        List<Fragment> fragments = getFragments();
        if (fragments != null) {
            if (fragments.size() > 2) {
                if (fragments.get(2) instanceof Fragment_Tercer_Promedio) {
                    Log.d(TAG, "fragments.get(2) instanceof Fragment_Tercer_Promedio");
                    return (Fragment_Tercer_Promedio) fragments.get(2);
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
       /* int id = item.getItemId();
        Fragment fragment = null;
        Class fragmentClass;
        if (id == R.id.nav_camera) {
            fragmentClass = Fragment_Promedio.class;
        } else if (id == R.id.nav_gallery) {
            fragmentClass = Fragment_SegundoPromedio.class;
        } else if (id == R.id.nav_slideshow) {
            fragmentClass = Fragment_Tercer_Promedio.class;
        } else if (id == R.id.nav_share) {

        }*/

        Fragment fragment = null;
        Class fragmentClass = null;

        switch(item.getItemId()) {
            case R.id.nav_camera:
                fragmentClass = Fragment_Promedio.class;
                break;
            case R.id.nav_gallery:
                fragmentClass = Fragment_SegundoPromedio.class;
                break;
            case R.id.nav_slideshow:
                fragmentClass = Fragment_Tercer_Promedio.class;
                break;
            case R.id.nav_share:
               // fragmentClass = ThirdFragment.class;
                break;
            default:
                fragmentClass = Fragment_Promedio.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    public void mAinposition() {
        switch (mainViewPager.getCurrentItem()) {
            case 0:
                inflateView();
                //inflate_dialog();
                SptipoUnidad.setVisibility(View.VISIBLE);
                mDateView();
               // guardarPromedio();
                break;
            case 1:
                inflateView();
                //inflate_dialog();
                mDateView();
               // guardarPromedio();
                break;
            case 2 :
                inflateView();
                mDateView();
                break;

            default:
                break;
        }

    }

    private void guardarPromedio(View layout_dialog_promedios) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set title
        alertDialogBuilder.setTitle("Nuevo Promedio");

        // set dialog message
        alertDialogBuilder
                .setView(layout_dialog_promedios)
                .setCancelable(true);
        alertDialog = alertDialogBuilder.create();
        btnsave.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
        ArrayAdapter<String> spinnerCountShoesArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.array_TipoUnidad));
        SptipoUnidad.setAdapter(spinnerCountShoesArrayAdapter);


        /*DATEPICKER*/


        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String captureItem = SptipoUnidad.getSelectedItem().toString();
                if (et_promedio1.getText().toString().equals("")) {
                    et_promedio1.setError("Ingrese Promedio");
                }
                if (et_promedio2.getText().toString().equals("")) {
                    et_promedio2.setError("Ingrese Promedio");
                } else {

                    Fragment_Promedio firstfragment = Fragments_Promedio();
                    Fragment_SegundoPromedio secondFragment = Fragments_Second();
                    Fragment_Tercer_Promedio thirdFragment = Fragments_third();
                    switch (mainViewPager.getCurrentItem()) {
                        //SEARCH FRAGMENT
                        case 0:
                            mPromed1 = Double.valueOf(et_promedio1.getText().toString());
                            mPromed2 = Double.valueOf(et_promedio2.getText().toString());
                            mPromed3 = Double.valueOf(et_promedio3.getText().toString());
                            mTotal = 0.30 * mPromed1 + 0.30 * mPromed2 + 0.40 * mPromed3;
                            addpromedio = new Entities_Promedio(captureItem, date,
                                    mPromed1,
                                    mPromed2,
                                    mPromed3,
                                    mTotal);

                            Log.d(TAG, "SAVEITEM :" + addpromedio.getmTipoUnidad());
                            Log.d(TAG, "mainViewPager" + mainViewPager.getCurrentItem());
                            ((Fragment_Promedio) firstfragment).addnewPromedio(addpromedio); //obtenemos la instancia del fragmento
                            // mTiUnidad.setText(mTipoUnidad);
                            break;
                        case 1:
                            mPromed1 = Double.valueOf(et_promedio1.getText().toString());
                            mPromed2 = Double.valueOf(et_promedio2.getText().toString());
                            mPromed3 = Double.valueOf(et_promedio3.getText().toString());
                            mTotal = 0.50 * mPromed1 + 0.25 * mPromed2 + 0.25 * mPromed3;
                            addpromedio = new Entities_Promedio("III Unidad", date,
                                    mPromed1,
                                    mPromed2,
                                    mPromed3,
                                    mTotal);

                            Log.d(TAG, "SAVEITEM :" + addpromedio.getmTipoUnidad());

                            ((Fragment_SegundoPromedio) secondFragment).addnewPromedio(addpromedio); //obtenemos la instancia del fragmento
                            Log.d(TAG, "mainViewPager" + mainViewPager.getCurrentItem());
                            break;
                        case 2:
                            mPromed1 = Double.valueOf(et_promedio1.getText().toString());
                            mPromed2 = Double.valueOf(et_promedio2.getText().toString());
                            mPromed3 = Double.valueOf(et_promedio3.getText().toString());
                            mTotal = 0.20 * mPromed1 + 0.30 * mPromed2 + 0.50 * mPromed3;
                            addpromedio = new Entities_Promedio("Unidad Final", date,
                                    mPromed1,
                                    mPromed2,
                                    mPromed3,
                                    mTotal);

                            Log.d(TAG, "SAVEITEM :" + addpromedio.getmTipoUnidad());

                            ((Fragment_Tercer_Promedio) thirdFragment).addnewPromedio(addpromedio); //obtenemos la instancia del fragmento
                            Log.d(TAG, "mainViewPager" + mainViewPager.getCurrentItem());
                            break;
                    }

                    alertDialog.dismiss();
                }
            }


        });
        alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }


    private void inflateView() {

        View layout_dialog_promedios = View.inflate(this, R.layout.activity_dialog_nuevo_promedio, null);

        btnsave = (Button) layout_dialog_promedios.findViewById(R.id.btn_save);
        btn_cancel = (Button) layout_dialog_promedios.findViewById(R.id.btn_cancel);
        et_promedio1 = (EditText) layout_dialog_promedios.findViewById(R.id.et_promedio1);
        et_promedio2 = (EditText) layout_dialog_promedios.findViewById(R.id.et_promedio2);
        et_promedio3 = (EditText) layout_dialog_promedios.findViewById(R.id.et_promedio3);
        btndate = (TextView) layout_dialog_promedios.findViewById(R.id.txt_date);
        SptipoUnidad = (Spinner) layout_dialog_promedios.findViewById(R.id.spinnerTipo);

        // show it
       /* alertDialog.show();*/
        guardarPromedio(layout_dialog_promedios);

    }

    private void mDateView() {
            /*DATEPICKER*/
        btndate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DatePickerDialog datePicker = new DatePickerDialog(Activity_Promedio.this, new DatePickerDialog.OnDateSetListener() {
                    @Override

                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        date = String.valueOf(dayOfMonth) + "/" + String.valueOf(monthOfYear + 1) + "/" + String.valueOf(year);


                        btndate.setText(date);
                    }
                }, yy, mm, dd);
                datePicker.show();
            }
        });

    }

   /* public void showCredits(){
        VersionInfoDialogFragment
                .newInstance(
                        getString(R.string.app_name),
                        BuildConfig.VERSION_NAME,
                        getString(R.string.app_credits_content),
                        R.mipmap.ic_launcher)
                .show(getFragmentManager(), "version_info");
    }*/

/*
    @Override
    public void onClick(View view) {

*/
}


