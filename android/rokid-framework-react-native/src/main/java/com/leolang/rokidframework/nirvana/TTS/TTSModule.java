package com.leolang.rokidframework.nirvana.TTS;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import rokid.os.IR2TTS;
import rokid.os.ITTSCallback;

/**
 * Created by langneng on 5/12/16.
 */
public class TTSModule extends ReactContextBaseJavaModule {
    public static IR2TTS mTTS;
    private static ReactApplicationContext mReactContext;
    private static final String TTS_INTENT = "com.rokid.server.rktts.IR2TTS";
    private static final String TAG = "TTSModule";
    private Callback mSuccessCallback;
    private Callback mErrorCallback;
    private static TTSModule mTTSModule;

    @Override
    public String getName() {
        return "ReactTTSService";
    }

    public TTSModule(ReactApplicationContext reactContext) {
        super(reactContext);
//        Log.d(TAG, "new TTSModule mSuccessCallback: "+mSuccessCallback.hashCode());
        Log.d(TAG, "new TTSModule pid:" + android.os.Process.myPid());
        mReactContext = reactContext;
        initTTS();
    }

    public static TTSModule getInstance(ReactApplicationContext reactContext) {
        mReactContext = reactContext;
        if (mTTSModule == null) {
            mTTSModule = new TTSModule(reactContext);
        }
        return mTTSModule;
    }

    private void initTTS() {
//        Log.d(TAG, "initTTS: getCurrentActivity:"+this.getCurrentActivity());
//        mTTS = (IR2TTS)RokidContext.getInstance().getSystemRemoteService(getCurrentActivity().getApplicationContext(),RokidContext.TTS_SERVICE);
//        try {
//            mTTS.registerTTSCallback(mReactContext.getPackageName(), new TTSCallbackListener() {
//                @Override
//                public int ttsEvent(int eventId, String s) throws RemoteException {
//                    Log.d(TAG, "eventId:" + eventId + " s:" + s + " mSuccessCallback:" + mSuccessCallback);
//                    if (mSuccessCallback != null) {
//                        mSuccessCallback.invoke(eventId);
//                    }
//                    return 0;
//                }
//            });
//        } catch (RemoteException e) {
//            e.printStackTrace();
//            mErrorCallback.invoke(e.getMessage());
//        }
        Intent intent = new Intent(TTS_INTENT);
        mReactContext.bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                mTTS = IR2TTS.Stub.asInterface(iBinder);
                Log.d(TAG,"mTTS:"+mTTS.hashCode());
                try {
                    mTTS.registerTTSCallback(mReactContext.getPackageName(), new TTSCallbackListener() {
                        @Override
                        public int ttsEvent(int eventId, String s) throws RemoteException {
                            Log.d(TAG, "eventId:" + eventId + " s:" + s + " mSuccessCallback:" + mSuccessCallback);
                            if (mSuccessCallback != null) {
                                mSuccessCallback.invoke(eventId);
                            }
                            return 0;
                        }
                    });
                } catch (RemoteException e) {
                    e.printStackTrace();
                    mErrorCallback.invoke(e.getMessage());
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                mTTS = null;
            }
        }, Context.BIND_AUTO_CREATE);
    }

    @ReactMethod
    public void tts(String content, Callback errorCallback, Callback successCallback) {
        Log.d(TAG, "tts:" + content+" successCallback:"+successCallback);
        mErrorCallback = errorCallback;
        mSuccessCallback = successCallback;
        Log.d(TAG, "tts:mSuccessCallback:"+mSuccessCallback.hashCode());
        try {
            mTTS.speakIt(content);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @ReactMethod
    public void ttsStop(Callback errorCallback, Callback successCallback) {
        Log.d(TAG, "android ttsStop");
        mErrorCallback = errorCallback;
        mSuccessCallback = successCallback;
        try {
            mTTS.stop();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void ttsStop() {
        Log.d(TAG, "android ttsStop from android onpause");
        try {
            mTTS.stop();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @ReactMethod
    public void isSpeaking(Callback resultCallback) {
        Log.d(TAG, "android tts isSpeaking judge");
        boolean isSpeaking = false;
        try {
            isSpeaking = mTTS.isSpeaking();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (resultCallback != null) {
            resultCallback.invoke(isSpeaking);
        }
    }

    @ReactMethod
    public void playLastTTS() {
        Log.d(TAG, "android playLastTTS");
        try {
            mTTS.playLastTTS();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @ReactMethod
    public void volumeUp() {
        Log.d(TAG,"android tts volumeUp");
        try {
            mTTS.volumeUp();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @ReactMethod
    public void volumeDown() {
        Log.d(TAG,"android tts volumeDown");
        try {
            mTTS.volumeDown();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    public abstract class TTSCallbackListener extends ITTSCallback.Stub {

    }
}
