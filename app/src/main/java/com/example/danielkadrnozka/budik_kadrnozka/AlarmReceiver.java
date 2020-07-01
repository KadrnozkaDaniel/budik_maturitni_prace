package com.example.danielkadrnozka.budik_kadrnozka;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by danielkadrnozka on 29/06/2020.
 */

public class AlarmReceiver extends BroadcastReceiver{


    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e("jestli toto vidíš tak vše funguje jak má"); //test funkčnosti
    }



}
