package com.andriidanyliak.ble_tester;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.widget.Toast;

/**
 * Created by Andrii Danyliak on 09.09.2016.
 */
public class BleTools {

    private BluetoothAdapter bluetoothAdapter;

    private Context mMianActivityContext;
    private Handler mMainActivityHandler;

    public final class CBleEvents{
        public static final int Created = 1;
        public static final int BleStackNotAvailable = 2;
    }

    BleTools(Context context, Handler handler){
        mMianActivityContext = context;
        mMainActivityHandler = handler;
        mMainActivityHandler.obtainMessage(CBleEvents.Created,"Created").sendToTarget();//just for debug
    }

    private void InitBluetooth(){
        if(!mMianActivityContext.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)){
            mMainActivityHandler.obtainMessage(CBleEvents.BleStackNotAvailable,"Feature Bluetooth Le not available").sendToTarget();
        }
    }

}
