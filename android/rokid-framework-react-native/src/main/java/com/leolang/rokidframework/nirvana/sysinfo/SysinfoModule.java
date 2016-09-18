package com.leolang.rokidframework.nirvana.sysinfo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.RemoteException;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import rokid.content.RokidContext;
import rokid.os.IRKAccountManager;
import rokid.os.RKConnectionUtil;
import rokid.os.RKSystemProperties;

/**
 * Created by langneng on 8/26/16.
 */
public class SysinfoModule extends ReactContextBaseJavaModule {
    private static final String MODULE_NAME = "SysinfoService";
    //private RokidContext mRokidContext;
    private ReactApplicationContext mReactContext;
    private static final String TAG = "SysinfoModule";

    private String rokidId = null;
    private String sessionId = null;
    private IRKAccountManager mRKAccountManager = null;
    private String cachedMac;



    public SysinfoModule(ReactApplicationContext reactContext) {
        super(reactContext);
        mReactContext = reactContext;
        init();
    }

    @Override
    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void getRokidSystemInfo(Callback errorCallback, Callback successCallback){
        String sysinfo = null;
        try{
            Log.i(TAG,"laoliang, start getting sys info");
            sysinfo = getSystemInfo();
            successCallback.invoke(sysinfo);
            return;
        }catch(Exception e){
            e.printStackTrace();
        }
        errorCallback.invoke("Error: get system info failed.");
        return;
    }

    private void init() {
        Log.e(TAG, "Sysinfo service ready.");

    }

    private boolean getRokidInfo()
    {
        if(mRKAccountManager == null)
            return false;

        if(rokidId == null || rokidId.equals(""))
        {
            try{
                sessionId = mRKAccountManager.getRokidLoginSessionId();
                rokidId = mRKAccountManager.getR2ID();
                Log.i(TAG, "getRokidInfo: sessionId:" + sessionId + "\trokidId:" + rokidId);
            } catch (RemoteException e) {
                sessionId = null;
                rokidId = null;
                e.printStackTrace();
                return false;
            }
        }
        if(sessionId == null || sessionId.equals(""))
            return false;
        return true;
    }


    private String getSystemInfo(){
        String sn = RKSystemProperties.getProperties("ro.boot.serialno");
        //String cmiit = mSettingsServices.getContext().getResources().getString(R.string.cmiit_id);
        String version = RKSystemProperties.getProperties("ro.build.version.release");
        String mac = getWifiMac();
        WifiManager wifiManager = (WifiManager)mReactContext.getSystemService(mReactContext.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        String ip = intToIp(wifiInfo.getIpAddress());
        String ssid = wifiInfo.getSSID();
        if (ssid != null) {
            String[] sp = ssid.split("\"");
            if (sp.length != 2) {
                ssid = null;
            } else {
                ssid = sp[1];
            }
        }

        String sysinfo = null;
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("event", "get_basic_info");
            JSONObject valJsonObject =new JSONObject();
            valJsonObject.put("sn", sn);
            valJsonObject.put("master_id", getMaster());
            valJsonObject.put("ip", ip);
            valJsonObject.put("ssid", ssid);
            valJsonObject.put("mac", mac);
            //valJsonObject.put("cmiit_id", cmiit);
            valJsonObject.put("version", version);
            jsonObject.put("value", valJsonObject);
            sysinfo = jsonObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sysinfo;
    }


    private String getMaster(){
        IRKAccountManager accountProxy;
        accountProxy = (IRKAccountManager) RokidContext.getInstance().getSystemRemoteService(mReactContext, RokidContext.RK_ACCOUNT);
        JSONObject json =null;
        String masterId = "unknown";
        if (accountProxy != null) {
            try {
                String rokidInfo = accountProxy.getRokidInfo();
                json = JSONObject.parseObject(rokidInfo);
                masterId = json.getString("masterId");
            } catch (RemoteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return masterId;
    }

    private String getWifiMac() {
        if (cachedMac == null) {
            File file = new File("/sys/class/net/wlan0/address");
            byte[] bs = new byte[128];
            String mac = null;
            if (file.exists()) {
                try {
                    FileInputStream fo = new FileInputStream(file);
                    fo.read(bs);
                    fo.close();
                    cachedMac = new String(bs, StandardCharsets.US_ASCII).trim();
                    return cachedMac;
                } catch (Exception e) {
                    e.printStackTrace();
                    cachedMac = "00:00:00:00:00:00";
                    return cachedMac;
                }
            } else {
                cachedMac = "00:00:00:00:00:00";
                return cachedMac;
            }
        } else {
            return cachedMac;
        }
    }

    private String intToIp(int i) {
        return (i & 0xFF ) + "." +
                ((i >> 8 ) & 0xFF) + "." +
                ((i >> 16 ) & 0xFF) + "." +
                ( i >> 24 & 0xFF) ;
    }


}


