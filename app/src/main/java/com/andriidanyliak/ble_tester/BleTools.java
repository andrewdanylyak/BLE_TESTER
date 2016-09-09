package com.andriidanyliak.ble_tester;

import android.content.Context;
import android.os.Handler;

/**
 * Created by Andrii Danyliak on 09.09.2016.
 */
public class BleTools {
    private Context mMianActivityContext;
    private Handler mMainActivityHandler;

    public final class CBleEvents{
        public static final int Created = 1;
    }

    BleTools(Context context, Handler handler){
        mMianActivityContext = context;
        mMainActivityHandler = handler;
        //mMainActivityHandler.obtainMessage(CBleEvents.Created,"Created").sendToTarget();
    }


}
