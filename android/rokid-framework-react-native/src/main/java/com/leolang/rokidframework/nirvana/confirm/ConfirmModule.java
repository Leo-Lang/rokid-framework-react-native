package com.leolang.rokidframework.nirvana.confirm;

import android.os.RemoteException;
import android.util.Log;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableNativeMap;
import com.google.gson.Gson;

import java.util.Map;

import rokid.content.RokidContext;
import rokid.entities.RKConfirmObject;
import rokid.entities.RKIntentObject;
import rokid.os.RKConnectionUtil;
import rokid.service.util.RemoteServiceHelper;

/**
 * Created by langneng on 8/26/16.
 */
public class ConfirmModule extends ReactContextBaseJavaModule {
    private static final String MODULE_NAME = "ConfirmService";
    private RokidContext mRokidContext;
    private RKConnectionUtil mRKConnectionUtil;
    private ReactApplicationContext mReactContext;
    private static final String TAG = "ConfirmModule";

    public ConfirmModule(ReactApplicationContext reactContext) {
        super(reactContext);
        mReactContext = reactContext;
        rkConnectionUtilInit();
    }

    @Override
    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void confirmContent(String domain, String intent, String ttsContent, String slot, Callback errorCallback, Callback successCallback) {
        rkConnectionUtilInit();

        if (mRKConnectionUtil == null) {
            errorCallback.invoke();
            return;
        }

        RKConfirmObject confirmObject = new RKConfirmObject();
        confirmObject.setType(RKConfirmObject.type_slot);
        confirmObject.setMethod(RKConfirmObject.method_voice);

        RKIntentObject intentObject = new RKIntentObject();
        intentObject.setDomain(domain);
        intentObject.setIntent(intent);

        confirmObject.setIntent(intentObject);
        confirmObject.setTts(ttsContent);
        confirmObject.setSlot(slot);

        try {
            Log.e(TAG, "langneng confirmContent confirmObject json:"+new Gson().toJson(confirmObject));
            mRKConnectionUtil.requestConfirm(new Gson().toJson(confirmObject));
            successCallback.invoke("confirmContent start");
            return;
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        errorCallback.invoke();
        return;
    }

    @ReactMethod
    public void confirmIf(String domain, String intent, String ttsContent, ReadableMap slots, Callback errorCallback, Callback successCallback) {
        rkConnectionUtilInit();

        if(mRKConnectionUtil==null){
            errorCallback.invoke();
            return;
        }

        RKConfirmObject confirmObject = new RKConfirmObject();
        confirmObject.setType(RKConfirmObject.type_intent);
        confirmObject.setMethod(RKConfirmObject.method_voice);

        RKIntentObject intentObject = new RKIntentObject();
        intentObject.setDomain(domain);
        intentObject.setIntent(intent);
        Log.e(TAG, "langneng slots:" + slots);
        ReadableNativeMap slots_Native = (ReadableNativeMap)slots;
        Log.e(TAG, "langneng native slots:" + slots_Native);
        if(slots!=null){
            intentObject.setSlots((Map)slots_Native.toHashMap());
        }

        confirmObject.setIntent(intentObject);
        confirmObject.setTts(ttsContent);

        try {
            Log.e(TAG, "langneng confirmIf confirmObject json:"+new Gson().toJson(confirmObject));
            mRKConnectionUtil.requestConfirm(new Gson().toJson(confirmObject));
            successCallback.invoke("confirmIf start");
            return;
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        errorCallback.invoke();
        return;
    }

    public void rkConnectionUtilInit() {
        if (mRKConnectionUtil == null) {
            /*
            if (mRokidContext == null) {
                mRokidContext = RokidContext.getInstance();
            }
            mRKConnectionUtil = (RKConnectionUtil) mRokidContext.getSystemRemoteService(mReactContext, RokidContext.CONNECTION_SERVICE);
            */
            mRKConnectionUtil = (RKConnectionUtil)  RemoteServiceHelper.instance().getService("rk_connection");
        }
    }
}


