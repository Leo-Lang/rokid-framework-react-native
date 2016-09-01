package com.leolang.rokidframework.nirvana.mobile;

import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import rokid.content.RokidContext;
import rokid.service.interfaces.RKRemoteChannelInterface;

/**
 * Created by langneng on 8/11/16.
 */
public class MobileModule extends ReactContextBaseJavaModule {
    private RKRemoteChannelInterface mMobileProxy;
    private ReactApplicationContext mReactContext;
    private static final String TAG = "MobileModule";

    public MobileModule(ReactApplicationContext reactContext) {
        super(reactContext);
        mMobileProxy = (RKRemoteChannelInterface) RokidContext.getInstance().getSystemRemoteService(reactContext, RokidContext.RK_REMOTE_CHANNEL);
        mReactContext = reactContext;
    }

    @Override
    public String getName() {
        return "MobileService";
    }

    @ReactMethod
    public void sendMessageToMobile(String msg){
        Log.e(TAG, "langneng sendMessage msg:"+msg);
         if(mMobileProxy == null){
             mMobileProxy = (RKRemoteChannelInterface) RokidContext.getInstance().getSystemRemoteService(mReactContext, RokidContext.RK_REMOTE_CHANNEL);
         }

         if(mMobileProxy!=null){
             Log.e(TAG, "langneng sendMessageToMobile");
             mMobileProxy.reportToPhone(msg);
         }
    }

}
