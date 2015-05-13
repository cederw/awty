package edu.washington.cederw.arewethereyet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.i("uhh", "got it");

        String number = intent.getStringExtra("num");
        String message = intent.getStringExtra("msg");
        Toast.makeText(context, number + ": " + message, Toast.LENGTH_LONG).show();

    }
}
