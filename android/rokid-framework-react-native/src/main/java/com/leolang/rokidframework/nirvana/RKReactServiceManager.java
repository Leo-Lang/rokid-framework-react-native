package com.leolang.rokidframework.nirvana;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.leolang.rokidframework.nirvana.TTS.TTSModule;

/**
 * Created by langneng on 4/22/16.
 */
public class RKReactServiceManager extends ReactContextBaseJavaModule implements LifecycleEventListener {
    private static final String TAG = "RKReactServiceManager";
    private TTSModule mTTSModule;

    public RKReactServiceManager(ReactApplicationContext reactContext) {
        super(reactContext);

//        mTTSModule = new TTSModule(reactContext);
    }

    @Override
    public String getName() {
        return "ReactServiceManager";
    }


    @ReactMethod
    public void tts(String content,Callback errorCallback,Callback successCallback) {

          mTTSModule.tts(content,errorCallback,successCallback);
    }

    @ReactMethod
    public void ttsStop(Callback errorCallback,Callback successCallback){
          mTTSModule.ttsStop(errorCallback,successCallback);
    }


    @Override
    public void onHostResume() {

    }

    @Override
    public void onHostPause() {

    }

    @Override
    public void onHostDestroy() {

    }

}
