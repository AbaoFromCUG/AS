package com.cctv.video.videotest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;


/**
 * Created by Abao on 2017/5/23.
 */

public class MainActivity extends Activity{
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);

        this.setContentView(R.layout.activity_main);
        Log.d("log","here");
        CameraPreview mPreview=new CameraPreview(this);
        Log.d("log","hjh");
        FrameLayout preview= (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);
    }
}
