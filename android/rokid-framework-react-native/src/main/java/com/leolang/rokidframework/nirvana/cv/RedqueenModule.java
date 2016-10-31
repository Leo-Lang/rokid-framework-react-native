package com.leolang.rokidframework.nirvana.cv;

import android.os.RemoteException;
import android.util.Log;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.leolang.rokidframework.react.RKReactActivity;

import org.json.JSONObject;

import rokid.service.RKRedqueen;
import rokid.service.util.RemoteServiceHelper;

/**
 * Created by laoliang on 5/28/16.
 */
public class RedqueenModule extends ReactContextBaseJavaModule implements LifecycleEventListener,RKGestureEventListener {
    private static RKRedqueen mRedqueen;
    private static ReactApplicationContext mReactContext;
    private static final String TAG = "ReactRedqueenModule";
    private Callback mSuccessCallback;
    private Callback mErrorCallback;

    private static final int FUNC_EXIT        = 0; // 1: Exit.

    public static final String TAG_GESTURE = "cv-gesture";

    //gesture private variables
    private boolean isMultiPattern = false;
    private int patternIdSingle = 0;
    private int[] patternIdList= new int[1];
    private static final int GES_ALL = 2000;
    private static final int GES_WAVEHAND = 2144;
    private static final int GES_PALM = 2111;
    private static final int GES_PALMSLIDE = 2120;
    private static final int GES_PALMSLIDELEFT = 2121;
    private static final int GES_PALMSLIDERIGHT = 2122;
    private static final int GES_PALMSLIDEUP = 2123;
    private static final int GES_PALMSLIDEDOWN = 2124;
    private static final int GES_PALM2FIST = 2132;
    private static final int GES_OVERTIME = 2901;


    public RedqueenModule(ReactApplicationContext reactContext) {
        super(reactContext);
        mReactContext = reactContext;
        initRedqueen();
    }

    @Override
    public String getName() {
        return "RedqueenModule";
    }


    // initialize native cv redqueen service
    private void initRedqueen() {
        //mRedqueen = (RKRedqueen) RokidContext.getInstance().getSystemRemoteService(mReactContext, RokidContext.CV_SERVICE);
        mRedqueen = (RKRedqueen) RemoteServiceHelper.instance().getService("cv");
        if(mRedqueen != null){
            //gesture results will be caught by this listener and handled by override interface onEvent of RKGestureEventListener
            RKReactActivity.setRKGestureEventListener(this);
            Log.e(TAG, "cv service ready.");
        }else{
            String message = "getting cv service failed";
            mErrorCallback.invoke(message);
            Log.e(TAG, message);
        }
    }

    /*
    @ReactMethod
    public void detectFaceId(String callingName, Callback errorCallback, Callback successCallback) {
        doRedqueenCall("faceId", callingName, errorCallback, successCallback);
    }
    */

    @ReactMethod
    public void detectGestureWaveHand(String callingName, Callback errorCallback, Callback successCallback) {
        Log.e(TAG, "GES_WAVEHAND");
        doRedqueenCall(GES_WAVEHAND, callingName, errorCallback, successCallback);
    }

    @ReactMethod
    public void detectGesturePalm(String callingName, Callback errorCallback, Callback successCallback) {
        Log.e(TAG, "GES_PALM");
        doRedqueenCall(GES_PALM, callingName, errorCallback, successCallback);
    }

    @ReactMethod
    public void detectGesturePalmSlide(String callingName, Callback errorCallback, Callback successCallback){
        Log.e(TAG, "GES_PALMSLIDE");
        doRedqueenCall(GES_PALMSLIDE,callingName,errorCallback,successCallback);
    }

    @ReactMethod
    public void detectGesturePalmSlideLeft(String callingName, Callback errorCallback, Callback successCallback){
        Log.e(TAG, "GES_PALMSLIDELEFT");
        doRedqueenCall(GES_PALMSLIDELEFT,callingName,errorCallback,successCallback);
    }

    @ReactMethod
    public void detectGesturePalmSlideRight(String callingName, Callback errorCallback, Callback successCallback){
        Log.e(TAG, "GES_PALMSLIDERIGHT");
        doRedqueenCall(GES_PALMSLIDERIGHT,callingName,errorCallback,successCallback);
    }

    @ReactMethod
    public void detectGesturePalm2Fist(String callingName, Callback errorCallback, Callback successCallback){
        Log.e(TAG, "GES_PALM2FIST");
        doRedqueenCall(GES_PALM2FIST,callingName,errorCallback,successCallback);
    }

    private void doRedqueenCall(int actionType, String callingName, Callback errorCallback, Callback successCallback){
        Log.d(TAG, actionType +":" + callingName);
        //setting callbacks
        mErrorCallback = errorCallback;
        mSuccessCallback = successCallback;
        try {
            switch (actionType){
                //case "faceId":
                    //patternId = mRedqueen.getFuncIdVassassin();
                //    break;
                case GES_ALL:
                    isMultiPattern = true;
                    patternIdList = new int[9];
                    patternIdList[0] = mRedqueen.getGesIdWavehand();
                    patternIdList[1] = mRedqueen.getGesIdPalm();
                    patternIdList[2] = mRedqueen.getGesIdPalm2Fist();
                    patternIdList[3] = mRedqueen.getGesIdPalmSlide();
                    patternIdList[4] = mRedqueen.getGesIdPalmSlideDown();
                    patternIdList[5] = mRedqueen.getGesIdPalmSlideLeft();
                    patternIdList[6] = mRedqueen.getGesIdPalmSlideRight();
                    patternIdList[7] = mRedqueen.getGesIdPalmSlideUp();
                    patternIdList[8] = mRedqueen.getGesIdOverTime();
                    for(int i =0; i<patternIdList.length;i++)
                        mRedqueen.addGesture(patternIdList[i]);
                    mRedqueen.changeFunction(mRedqueen.getFuncIdGesture());
                    break;
                case GES_WAVEHAND:
                    patternIdSingle = mRedqueen.getGesIdWavehand();
                    mRedqueen.addGesture(patternIdSingle);
                    mRedqueen.changeFunction(mRedqueen.getFuncIdGesture());
                    break;
                case GES_PALM:
                    patternIdSingle = mRedqueen.getGesIdPalm();
                    mRedqueen.addGesture(patternIdSingle);
                    mRedqueen.changeFunction(mRedqueen.getFuncIdGesture());
                    break;
                case GES_PALM2FIST:
                    patternIdSingle = mRedqueen.getGesIdPalm2Fist();
                    mRedqueen.addGesture(patternIdSingle);
                    mRedqueen.changeFunction(mRedqueen.getFuncIdGesture());
                    break;
                case GES_PALMSLIDE:
                    patternIdSingle = mRedqueen.getGesIdPalmSlide();
                    mRedqueen.addGesture(patternIdSingle);
                    mRedqueen.changeFunction(mRedqueen.getFuncIdGesture());
                    break;
                case GES_PALMSLIDEDOWN:
                    patternIdSingle = mRedqueen.getGesIdPalmSlideDown();
                    mRedqueen.addGesture(patternIdSingle);
                    mRedqueen.changeFunction(mRedqueen.getFuncIdGesture());
                    break;
                case GES_PALMSLIDELEFT:
                    patternIdSingle = mRedqueen.getGesIdPalmSlideLeft();
                    mRedqueen.addGesture(patternIdSingle);
                    mRedqueen.changeFunction(mRedqueen.getFuncIdGesture());
                    break;
                case GES_PALMSLIDERIGHT:
                    patternIdSingle = mRedqueen.getGesIdPalmSlideRight();
                    mRedqueen.addGesture(patternIdSingle);
                    mRedqueen.changeFunction(mRedqueen.getFuncIdGesture());
                    break;
                case GES_PALMSLIDEUP:
                    patternIdSingle = mRedqueen.getGesIdPalmSlideUp();
                    mRedqueen.addGesture(patternIdSingle);
                    mRedqueen.changeFunction(mRedqueen.getFuncIdGesture());
                    break;
                default:
                    break;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            mErrorCallback.invoke("TYPE="+actionType + "/n" + e.getMessage());
        }finally {
            //finalize if failed to set cv service
            try {
                mRedqueen.changeFunction(RedqueenModule.FUNC_EXIT);
            }catch(RemoteException e) {
                Log.e(TAG,"failed closing the detection mode,"+e.getMessage());
            }
        }
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


    @Override
    public void onEvent(String event) {
        int code=-1;
        int value = -1;
        try {
            JSONObject je = new JSONObject(event);
            code = je.getInt("code");
            value = je.getInt("value");
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        if(isMultiPattern){
            Log.d(TAG, "############## detecting all gestures");
            for(int i =0; i<patternIdList.length;i++){
                if(code== patternIdList[i]) {
                    mSuccessCallback.invoke(code);
                    Log.d(TAG, "############## received ges " + code);
                    break;
                }
            }
        }else if(patternIdSingle ==code){
            mSuccessCallback.invoke(code);
            switch (code) {
                case GES_PALM: // palm
                    Log.d(TAG, "############## received palm");
                    break;
                case GES_PALMSLIDE: // palm
                    Log.d(TAG, "############## received slide");
                    break;
                case GES_PALMSLIDELEFT: // slide left
                    Log.d(TAG, "############## received slide left");
                    break;
                case GES_PALM2FIST: // palm to fist
                    Log.d(TAG, "############## received palm to fist");
                    break;
                case GES_PALMSLIDERIGHT: // slide right
                    Log.d(TAG, "############## received slide right");
                    break;
                case GES_WAVEHAND:
                    Log.d(TAG, "############## received wave hand");
                    break;
                case GES_PALMSLIDEUP: // reset
                    Log.d(TAG, "############## received slide up");
                    break;
                case GES_PALMSLIDEDOWN: // reset
                    Log.d(TAG, "############## received slide down");
                    break;
                case GES_OVERTIME: // timeout
                    Log.d(TAG, "############## received overtime");
                    break;
            }
        }
        //standard process to end gesture service
        try{
            if(!isMultiPattern){
                mRedqueen.delGesture(patternIdSingle);
            }else{
                for(int i =0; i<patternIdList.length;i++)
                    mRedqueen.delGesture(patternIdList[i]);
            }
            mRedqueen.changeFunction(RedqueenModule.FUNC_EXIT);
        }catch (RemoteException e){
            e.printStackTrace();
        }
        //return true;

    }
}
