package com.leolang.rokidframework.nirvana.lumen;

import android.os.RemoteException;

import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import rokid.os.IRKLumen;
import rokid.service.util.RemoteServiceHelper;

/**
 * Created by lujnan on 5/29/16.
 */
public class LumenModule extends ReactContextBaseJavaModule implements LifecycleEventListener {

    private IRKLumen mLumenService;

    public LumenModule(ReactApplicationContext reactContext) {
        super(reactContext);
        //mLumenService = (IRKLumen) RokidContext.getInstance().getSystemRemoteService(reactContext, RokidContext.LUMEN_SERVICE);
        mLumenService = (IRKLumen) RemoteServiceHelper.instance().getService("lumen");
    }

    @Override
    public void onHostResume() {

    }

    @Override
    public void onHostPause() {

    }

    @Override
    public void onHostDestroy() {
        //mLumenService = null;
    }

    @Override
    public String getName() {
        return "LumenService";
    }

    /////////////////////////////////////////////////////

    @ReactMethod
    public int getRgb(int colorIndex, int colorType) {
        return mLumenService.getRgb(colorIndex, colorType);
    }

    @ReactMethod
    public void attention(float angle, int flags) {
        try {
            mLumenService.attention(angle, flags);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @ReactMethod
    public void theme(int color, int type, int mode, int availFlag)  {
        try {
            mLumenService.theme(color, type, mode, availFlag);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @ReactMethod
    public void brightness(int mode, int brightness) {
        try {
            mLumenService.brightness(mode, brightness);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @ReactMethod
    public void setBrightness(int mode, int value, boolean showMe) {
        try {
            mLumenService.setBrightness(mode, value, showMe);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @ReactMethod
    public void hibernate() {
        try {
            mLumenService.hibernate();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @ReactMethod
    public int fetchTheme(int mode) {
        try {
            return mLumenService.fetchTheme(mode);
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @ReactMethod
    public int fetchSystemBrightness() {
        try {
            return mLumenService.fetchSystemBrightness();
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /*
    // for lumen ide only
    void debugSwitcher(boolean stat)
    void debugTransact(String json)

    // for app lumen
    public void discardCanvas() {

    }
    */

    /*
     *
     * @param color SPECIFIED RGB
     * @param br FLOAT OF SYS BR
     * @param layout EXEC | COMMUNICATION
     * @param durationMillisec
     * @throws RemoteException
     */
    @ReactMethod
    public void drawLumen(int color, float br, int layout, long durationMillisec) {
        /*
        try {
            mLumenService.drawLumen(color, br, layout, durationMillisec);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        */
    }

}
