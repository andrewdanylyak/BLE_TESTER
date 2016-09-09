package com.andriidanyliak.ble_tester;

import android.app.Activity;
import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView statusText;
    private TextView bleAvailableText;
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothManager bluetoothManager;
    private BleTools bleTools;

    private void InitUI(){
        statusText = (TextView)findViewById(R.id.StatusText);
        bleAvailableText = (TextView)findViewById(R.id.BleAvailableText);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitUI();

        bleTools = new BleTools(this, mBleToolsHandler);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        Process.killProcess(Process.myPid());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void updateStatus(final String msg){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                statusText.setText("Status:" + msg);
                Log.d(TAG, statusText.getText().toString());
            }
        });
    }

    private Handler mBleToolsHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case BleTools.CBleEvents.Created:
                    updateStatus((String)msg.obj);
                    break;
                case BleTools.CBleEvents.BleAdapterTurnedOn:
                    updateStatus((String)msg.obj);
                    break;
                case BleTools.CBleEvents.BleFeatureEnabled:
                    bleAvailableText.setText((String)msg.obj);
                    break;
            }
        }
    };
}
