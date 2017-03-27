package com.example.igroup.broadcasrreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver broadcastReceiver;
    IntentFilter filter = new IntentFilter(Intent.ACTION_TIME_TICK);
    Button btn_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_send = (Button)findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent("Send");
                sendBroadcast(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(broadcastReceiver == null)
        {
            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {

                    if (intent.getAction().equals(Intent.ACTION_TIME_TICK))
                    {
                        Toast.makeText(getApplicationContext(),"ACTION_TIME_TICK has been received",Toast.LENGTH_LONG).show();
                    }
                    else if (intent.getAction().equals("Send"))
                    {
                        Toast.makeText(getApplicationContext(),"Send broadcast received",Toast.LENGTH_LONG).show();
                    }

                }
            };
        }
        else
        {
            registerReceiver(broadcastReceiver,filter);
            registerReceiver(broadcastReceiver,new IntentFilter("Send"));
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }
}
