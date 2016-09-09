package com.andriidanyliak.ble_tester;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import android.support.v7.app.AppCompatActivity;

//import android.support.v4.app.ActivityCompat;

/**
 * Created by Andrii Danyliak on 09.09.2016.
 */
public class BleTools {

    private BluetoothAdapter bluetoothAdapter;
    private BluetoothManager bluetoothManager;

    private Context mMianActivityContext;
    private Handler mMainActivityHandler;
    private Timer mStartTimer;
    private static final int REQUEST_ENABLE_BT = 1;

    public final class CBleEvents{
        public static final int Created = 1;
        public static final int BleAdapterTurnedOn = 2;
        public static final int BleFeatureEnabled = 3;

    }

    BleTools(Context context, Handler handler ){
        mMianActivityContext = context;
        mMainActivityHandler = handler;
        mMainActivityHandler.obtainMessage(CBleEvents.Created,"Buetooth initialization...").sendToTarget();//just for debug
        mStartTimer = new Timer();
        StartInitalization();
    }

    private void StartInitalization() {
        if(mStartTimer != null)
            mStartTimer.cancel();
        mStartTimer = new Timer();
        mStartTimer.schedule(new TimerTask() {
            @Override
            public void run() {
            }
        }, 1000);
        InitBluetooth();
        mStartTimer.cancel();
    }

    private void InitBluetooth(){

        if(!((Activity)mMianActivityContext).getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)){
            Toast.makeText(((Activity)mMianActivityContext), "Feature Bluetooth Le not available", Toast.LENGTH_SHORT).show();
            ((Activity)mMianActivityContext).finish();
            return;
        }

        mMainActivityHandler.obtainMessage(CBleEvents.BleFeatureEnabled,"BuetoothLE feature enabled").sendToTarget();

        bluetoothManager = (BluetoothManager)((Activity)mMianActivityContext).getSystemService(Context.BLUETOOTH_SERVICE);
        bluetoothAdapter = bluetoothManager.getAdapter();
        if(bluetoothAdapter == null){
            Toast.makeText(((Activity)mMianActivityContext).getApplicationContext(), "Bluetooth adapter not found", Toast.LENGTH_SHORT).show();
            ((Activity)mMianActivityContext).finish();
            return;
        }
        if(!bluetoothAdapter.isEnabled()){
            bluetoothAdapter.enable();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(!bluetoothAdapter.isEnabled()) {
            ((Activity)mMianActivityContext).finish();
            return;
        }
        else {
            mMainActivityHandler.obtainMessage(CBleEvents.BleAdapterTurnedOn,"Bluetooth adapter is turned on").sendToTarget();;
        }
    }

}
