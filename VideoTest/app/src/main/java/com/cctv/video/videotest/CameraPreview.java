package com.cctv.video.videotest;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by Abao on 2017/5/23.
 */

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG="CameraPreView";
    private SurfaceHolder mHolder;
    private Camera mCamera;

    public CameraPreview(Context context){
        super(context);
        mHolder=this.getHolder();
        mHolder.addCallback( this);


    }
    private static Camera getCameraInstance(){
        Camera c=null;
        try{
            c=Camera.open();
        }catch (Exception e){
            Log.d(TAG,"open fail"+e.getMessage());
        }
        return c;
    }
    public void surfaceCreated(SurfaceHolder holder){
        mCamera=getCameraInstance();
        try{
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
        }catch (IOException e){
            Log.d(TAG,"fail setCameraPreview:"+e.getMessage());

        }


    }
    public void surfaceChanged(SurfaceHolder holder,int format,int w, int h){

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        mHolder.removeCallback( this);
        mCamera.setPreviewCallback(null);
        mCamera.stopPreview();;
        mCamera.release();
        mCamera=null;
    }

}

