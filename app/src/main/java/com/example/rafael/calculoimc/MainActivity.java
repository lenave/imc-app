package com.example.rafael.calculoimc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextView lblMsg;
    TextView lblIMC;
    TextView lblUserName;
    EditText txtHeight;
    EditText txtWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Isso aqui muda o texto da sideBar, mas nao vou mais usar
        /*View header=navigationView.getHeaderView(0);
        lblUserName = (TextView)header.findViewById(R.id.lbl_userName);

        lblUserName.setText(userNameS.toString());*/


        // Calc Button -----------------

        // Variables
        txtHeight = (EditText) findViewById(R.id.txt_height);
        txtWeight = (EditText) findViewById(R.id.txt_weight);
        lblMsg = (TextView) findViewById(R.id.lbl_msg);
        lblIMC = (TextView) findViewById(R.id.lbl_imc);

        // Calc onclick listener
        Button btnCalc = (Button) findViewById(R.id.btn_calc);
        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtHeightS = txtHeight.getText().toString();
                String txtWeightS = txtWeight.getText().toString();

                if (txtHeightS.indexOf(",") != -1) {
                    txtHeightS = txtHeightS.replace(',', '.');
                }
                if (txtWeightS.indexOf(",") != -1) {
                    txtWeightS = txtWeightS.replace(',', '.');
                }

                double txtHeightC = Double.parseDouble(txtHeightS);
                double txtWeightC = Double.parseDouble(txtWeightS);

                String userNameS = getUserName();
                String userAgeS = getUserAge();
                ImcCalc imc = new ImcCalc(txtHeightC, txtWeightC, userNameS, userAgeS);
                double imcResult = imc.getImc();
                String imcMsg = imc.getMsg();

                // Let's show the results
                lblMsg.setText(imcMsg);
                BigDecimal bd = new BigDecimal(imcResult).setScale(2, RoundingMode.HALF_EVEN);
                lblIMC.setText(String.valueOf(bd));

                // removing focus of keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
            }
        });

        // End Calc Button


    }

    private String getUserName() {
        SharedPreferences mSharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
        String toReturn = mSharedPreferences.getString("userName", "Usuário");

        return toReturn;
    }

    private String getUserAge() {
        SharedPreferences mSharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
        String toReturn = mSharedPreferences.getString("userAge", "Sem idade");

        return toReturn;
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

        if (id == R.id.nav_change) {
            // go to page
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
