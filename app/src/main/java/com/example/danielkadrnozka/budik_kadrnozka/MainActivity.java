package com.example.danielkadrnozka.budik_kadrnozka;

//version 1.0


import android.app.AlarmManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    //import tříd AlarmManager, TimePicker a TextView
    AlarmManager alarm_manager;
    TimePicker alarmTimePicker;
    TextView infoZapVyp;

    Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.context = this;

        //inicializace AlarmManageru
        alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);

        //inicializace TimePickeru
        alarmTimePicker = (TimePicker) findViewById(R.id.timePicker);

        //inicializace okna s informací zda je alarm zapnut či vypnut
        infoZapVyp = (TextView) findViewById(R.id.infoZapVyp);

        //vytvoření instance kalendáře
        Calendar calendar = Calendar.getInstance();

        //inicializace tlačítka pro zapnutí budíku
        Button zapnoutBudik = (Button) findViewById(R.id.zapnoutBudik);
        //vytvoření onClick listeneru pro zapnutí budíku

        zapnoutBudik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //metoda která změní text u TextViewu infoZapVyp
                nastavInfoZapVyp("alarm je nastaven");
            }
        });




        //inicializace tlačítka pro vypnutí budíku
        Button vypnoutBudik = (Button) findViewById(R.id.vypnoutBudik);
        //vytvoření onClick pro vypnutí budíku

        vypnoutBudik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //metoda která změní text u TextViewu infoZapVyp
                nastavInfoZapVyp("alarm není nastaven");
            }
        });







    }

    private void nastavInfoZapVyp(String vystup) {
        infoZapVyp.setText(vystup); //změní text u infoZapVyp na "alarm je/není nastaven"
                                    //podle toho jestli klikneme na zapnout nebo vypnout budík
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
