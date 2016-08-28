package com.leolang.rokidframework.nirvana;

import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.leolang.rokidframework.react.RKReactActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by langneng on 4/13/16.
 */
public class RKReactEventManager extends ReactContextBaseJavaModule implements RKReactEventListener {
    private static final String TAG = "RKReactEventManager";
    private static RKReactEventManager mRKReactEventManager;

    public RKReactEventManager(ReactApplicationContext reactContext) {
        super(reactContext);

        Log.e(TAG,"RKReactEventManager setRKReactEventListener");
        RKReactActivity.setRKReactEventListener(this);
    }

    public static RKReactEventManager getInstance(ReactApplicationContext reactContext){
        if(mRKReactEventManager ==null){
            mRKReactEventManager = new RKReactEventManager(reactContext);
        }
        return mRKReactEventManager;
    }


    @Override
    public String getName() {
        return "ReactEventManager";
    }

    @Override
    public void sendEvent(String eventName, String event) {
        Log.e(TAG, "sendEvent eventName:"+eventName+" event:"+event);
        getReactApplicationContext()
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, event);
    }

    @ReactMethod
    public void notifyEventChannelReady(boolean eventChannelReady){
        //Log.e(TAG,"langneng eventChannelReady:"+eventChannelReady);
        RKReactActivity.RKReactEventChannelReady = eventChannelReady;
    }

    @Override
    public Map<String, Object> getConstants() {
        Map<String,Object> constants = new HashMap<>();
        constants.put(RKReactActivity.RK_INTENT,RKReactActivity.RK_INTENT);
        return constants;
    }
}

