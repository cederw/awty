package edu.washington.cederw.arewethereyet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    private static SmsManager manager;
    public MyReceiver() {
        manager =  SmsManager.getDefault();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.i("uhh", "got it");
        String number = intent.getStringExtra("num");
        String message = intent.getStringExtra("msg");
        manager.sendTextMessage(number,null,message, null,null);

       // Toast.makeText(context, number + ": " + message, Toast.LENGTH_LONG).show();

    }
}
