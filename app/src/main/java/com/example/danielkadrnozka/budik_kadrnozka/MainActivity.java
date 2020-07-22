package com.example.danielkadrnozka.budik_kadrnozka;

//version 1.0


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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

    AlarmManager alarm_manager;
    TimePicker alarmTimePicker;
    TextView infoZapVyp;
    Context context;
    PendingIntent pendingIntent;





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

        //vytvoření instance třídy Calendar
        final Calendar calendar = Calendar.getInstance();

        //nastavení timePickeru na 24 hodinový formát času
        alarmTimePicker.setIs24HourView(true);
        alarmTimePicker.setCurrentHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));




        //vytvoření Intentu do třídy AlarmReceiver
        final Intent intent = new Intent(this, AlarmReceiver.class);





        //inicializace tlačítka pro zapnutí budíku
        Button zapnoutBudik = (Button) findViewById(R.id.zapnoutBudik);

        //vytvoření onClick listeneru pro zapnutí budíku
        zapnoutBudik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                long time;
                //nastavení instance z třídy Calendar na hodiny a minuty
                //co jsme vybrali v timepickeru
                calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());

                //získání int hodnot hodin a minut
                int hodiny = alarmTimePicker.getHour();
                int minuty = alarmTimePicker.getMinute();

                //převod hodin/minut z int na String
                String hodinyString = String.valueOf(hodiny);
                String minutyString = String.valueOf(minuty);

                //přidání nuly při výpisu méně jak 10 minut
                //např při 14:7 na 14:07
                if (minuty < 10){
                    minutyString = "0" + String.valueOf(minuty);
                }

                time=(calendar.getTimeInMillis()-(calendar.getTimeInMillis()%60000));
                if(System.currentTimeMillis()>time)
                {
                    if (calendar.AM_PM == 0)
                        time = time + (1000*60*60*12);
                    else
                        time = time + (1000*60*60*24);
                }


                //metoda která změní text u TextViewu infoZapVyp na "alarm je nastaven"
                nastavInfoZapVyp("alarm je nastaven na: " + hodinyString + ":" + minutyString);

                //
                pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                //tady je někde problém, nutno vyřešit
                //nastavení alarm_manageru
                alarm_manager.setRepeating(AlarmManager.RTC_WAKEUP, time, 10000 ,pendingIntent);

            }
        });








        //inicializace tlačítka pro vypnutí budíku
        Button vypnoutBudik = (Button) findViewById(R.id.vypnoutBudik);

        //vytvoření onClick listeneru pro vypnutí budíku
        vypnoutBudik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //metoda která změní text u TextViewu infoZapVyp na "alarm není nastaven"
                nastavInfoZapVyp("alarm není nastaven");

                //storno alarmu
                alarm_manager.cancel(pendingIntent);


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
