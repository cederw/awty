package edu.washington.cederw.arewethereyet;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

    private static Button control;
    private static AlarmManager alarmMgr;
    private static PendingIntent alarmIntent;
    private static MainActivity instance;


    public MainActivity() {
        if (instance == null) {
            instance = this;
        } else {
            Log.e("MainActivity", "There is an error beep boop. You tried to create more than 1 MainActivity");
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        control = (Button)findViewById(R.id.button);

        control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check that everything is filled out
                if(!((EditText)findViewById(R.id.editText)).getText().toString().equals("")&&!((EditText)findViewById(R.id.editText2)).getText().toString().equals("")&&!((EditText)findViewById(R.id.editText3)).getText().toString().equals("")){
                    if(control.getText().equals("Start")){
                        control.setText("Stop");
                        Long time = Long.decode(((EditText)findViewById(R.id.editText3)).getText().toString());
                        if(time <0){
                            throw new IllegalArgumentException("Negative number entered");
                        }
                        Intent intent = new Intent(instance, MyReceiver.class);
                        intent.putExtra("msg",((EditText)findViewById(R.id.editText)).getText().toString());
                        intent.putExtra("num",((EditText)findViewById(R.id.editText2)).getText().toString());
                        Log.i("msg",((EditText)findViewById(R.id.editText)).getText().toString()+Long.decode(((EditText)findViewById(R.id.editText3)).getText().toString()));
                        alarmMgr = (AlarmManager)instance.getSystemService(Context.ALARM_SERVICE);
                        alarmIntent = PendingIntent.getBroadcast(instance, 0, intent, 0);
                        alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                                1000,
                                time*60000, alarmIntent);

                    } else{
                        control.setText("Start");
                        alarmMgr.cancel(alarmIntent);
                    }







                }

            }

        });
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
