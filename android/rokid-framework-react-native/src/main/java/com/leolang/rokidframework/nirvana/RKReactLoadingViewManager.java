package com.leolang.rokidframework.nirvana;

import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.leolang.rokidframework.R;
import com.rokid.rokidviewlibrary.ViewLibrary;

/**
 * Created by langneng on 5/16/16.
 */
public class RKReactLoadingViewManager extends SimpleViewManager<ImageView> {
    private static final String REACT_CLASS = "RKLoadingView";
    private static final String TAG = "LoadingViewManager";
    private LayoutInflater mInflater;

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected ImageView createViewInstance(ThemedReactContext reactContext) {
        Log.d(TAG,"langneng createViewInstance");
        mInflater = LayoutInflater.from(reactContext);
        LinearLayout lin = (LinearLayout) mInflater.inflate(R.layout.processloading,null);
        ImageView imageView = (ImageView) lin.findViewById(R.id.im_load);
        lin.removeView(imageView);
        return imageView;
    }

    @ReactProp(name = "loadingStatus")
    public void loadingControl(ImageView imageView,String loadingStatus) {
        Log.e("TAG", "langnengloading loadingStatus:"+loadingStatus);
        if(loadingStatus.equals("play")){
            ViewLibrary.getInstance().getLoadingViewUtil().showLoadingAnimView(imageView);
        }else if(loadingStatus.equals("stop")){
            ViewLibrary.getInstance().getLoadingViewUtil().stopLoadingAnim();
        }
    }
}
