package com.example.danielkadrnozka.budik_kadrnozka;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.widget.Toast;
import android.os.Handler;


/**
 * Created by danielkadrnozka on 29/06/2020.
 */


public class AlarmReceiver extends BroadcastReceiver{

    public Ringtone ringtone;

    //změna času u Toast.LENGHT_LONG z jedné vteřiny na 5
    private static final int LONG_DELAY = 5000;


    @Override
    public void onReceive(Context context, Intent intent) {


        Toast.makeText(context, "Budík! Vstávej!", Toast.LENGTH_LONG).show();
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null)
        {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }

        this.ringtone = RingtoneManager.getRingtone(context, alarmUri);
        ringtone.play();

        //automatické vypnutí budíku po jedné minutě
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
              ringtone.stop();
            }
        }, 1000 * 60);

    }
}
