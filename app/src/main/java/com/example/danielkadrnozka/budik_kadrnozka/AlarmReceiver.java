package com.example.danielkadrnozka.budik_kadrnozka;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.widget.Toast;
import android.util.Log;

/**
 * Created by danielkadrnozka on 29/06/2020.
 */

public class AlarmReceiver extends BroadcastReceiver{


    @Override
    public void onReceive(Context context, Intent intent) {


        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null)
        {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
        ringtone.play();
    }



}
