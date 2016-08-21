package com.leolang.rokidframework.react;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.facebook.react.ReactActivity;
import com.facebook.react.ReactPackage;
import com.leolang.rokidframework.nirvana.RKReactEventListener;
import com.leolang.rokidframework.nirvana.TTS.TTSModule;
import com.leolang.rokidframework.nirvana.cv.RKGestureEventListener;
import com.leolang.rokidframework.nirvana.cv.RedqueenModule;
import com.leolang.rokidframework.nirvana.eventvo.NewRokidEventVO;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class RKReactActivity extends ReactActivity {
    private final String TAG = "RKReactActivity";
    private final String RN_INTENT = "RNIntent";
    private final int THREAD_NUM = 8;
    private static RKReactEventListener mRKReactEventListener;
    private static RKGestureEventListener mRKGestureEventListener;
    public static boolean RKReactEventChannelReady = false;
    private ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(THREAD_NUM);
    private ImageView sysLoadView;
    private View rkrootview;


    @Override
    protected String getMainComponentName() {
        return null;
    }

    @Override
    protected boolean getUseDeveloperSupport() {
        return false;
    }

    @Override
    protected List<ReactPackage> getPackages() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.layout_rkreact);
//        final LayoutInflater inflater = LayoutInflater.from(this);
//        LinearLayout lin = (LinearLayout) inflater.inflate(
//                R.layout.processloading, null);
//        ImageView image = (ImageView)lin.findViewById(R.id.im_load);
//        lin.removeView(image);
//        ((FrameLayout) rkrootview).addView(image);
//
//        setContentView(rkrootview);
////        sysLoadView = (ImageView) lin.findViewById(R.id.im_load);
//        ImageView test = (ImageView)findViewById(R.id.im_load);
//        Log.d(TAG, "onCreate test:"+test);
//        ViewLibrary.getInstance().getLoadingViewUtil().showLoadingAnimView(test);
        sendEventToRN();
    }

//    @Override
//    public void setContentView(View view) {
//        super.setContentView(view);
//        rkrootview = view;
//    }

    @Override
    protected void onNewIntent(Intent intent) {

        super.onNewIntent(intent);
        setIntent(intent);
        sendEventToRN();
    }


    public final boolean onNewRokidEvent(String event) {
        Log.d(TAG, "onNewRokidEvent event:" + event);
        NewRokidEventVO newRokidEventVO = JSON.parseObject(event, NewRokidEventVO.class);

        if(RedqueenModule.TAG_GESTURE.equals(newRokidEventVO.getDevice())){
               gestureEventHandler(newRokidEventVO.getEvent());
        }

        return false;
    }

    private void sendEventToRN() {
        Intent intent = getIntent();
        Log.e(TAG, "Rokid intent:" + intent);

        threadPoolExecutor.execute(new EventSendTask(intent));
    }

    private class EventSendTask implements Runnable {
        private Intent mIntent;

        public EventSendTask(Intent intent) {
            mIntent = intent;
        }

        @Override
        public void run() {
            while (true) {
                //Log.d(TAG, " langneng mRKReactEventListener:" + mRKReactEventListener + " RKReactEventChannelReady:" + RKReactEventChannelReady);
                if (mRKReactEventListener != null && RKReactEventChannelReady) {
                    String event_nlp = mIntent.getStringExtra("nlp");
                    Log.d(TAG, "langneng sendEvent intent:" + event_nlp);

                    if (event_nlp == null || event_nlp.length() <= 0) {
                        return;
                    }
                    mRKReactEventListener.sendEvent(RN_INTENT, event_nlp);
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void setRKReactEventListener(RKReactEventListener rkReactEventListener) {
        mRKReactEventListener = rkReactEventListener;
    }

    public static void setRKGestureEventListener(RKGestureEventListener rkGestureEventListener) {
        mRKGestureEventListener = rkGestureEventListener;
    }

    @Override
    protected void onPause() {
        super.onPause();
        TTSModule.ttsStop();
        Log.d(TAG, "langneng onPause RKReactEventChannelReady false");
        RKReactEventChannelReady = false;
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "langneng onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "langneng onDestroy");
    }

    private void gestureEventHandler(String event){
         if(mRKGestureEventListener!=null){
             mRKGestureEventListener.onEvent(event);
         }
    }

}


