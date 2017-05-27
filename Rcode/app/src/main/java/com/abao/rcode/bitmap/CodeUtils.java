package com.abao.rcode.bitmap;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.text.TextUtils;
import android.util.Log;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.Hashtable;


/**
 * Created by Abao on 2017/5/26.
 */

public class CodeUtils {
    private static final String TAG = "CodeUtils";
    public static Bitmap stringToBitmapWithLogo(String string,int w,int h,Bitmap bitmap){
        if(TextUtils.isEmpty(string)){
            return  null;
        }
        try{
            Bitmap scaleLogo=getScaleLogo(bitmap,w,h);
            int offsetX=w/2;
            int offsetY=h/2;

            int scaleWidth=0;
            int scaleHeight=0;

            if(scaleLogo!=null){
                scaleWidth=scaleLogo.getWidth();
                scaleHeight=scaleLogo.getHeight();
                offsetX=(w-scaleWidth)/2;
                offsetY=(h-scaleHeight)/2;
            }
            Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            //容错级别
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            //设置空白边距的宽度
            hints.put(EncodeHintType.MARGIN, 0);
            BitMatrix bitMatrix = new QRCodeWriter().encode(string, BarcodeFormat.QR_CODE, w, h, hints);
            int[] pixels = new int[w * h];
            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    if (x >= offsetX && x < offsetX + scaleWidth && y >= offsetY && y < offsetY + scaleHeight) {
                        int pixel = scaleLogo.getPixel(x - offsetX, y - offsetY);
                        if (pixel == 0) {
                            if (bitMatrix.get(x, y)) {
                                pixel = 0xff000000;
                            } else {
                                pixel = 0xffffffff;
                            }
                        }
                        pixels[y * w + x] = pixel;
                    } else {
                        if (bitMatrix.get(x, y)) {
                            pixels[y * w + x] = 0xff000000;
                        } else {
                            pixels[y * w + x] = 0xffffffff;
                        }
                    }
                }
            }
            Bitmap mbitmap = Bitmap.createBitmap(w, h,
                    Bitmap.Config.ARGB_8888);
            mbitmap.setPixels(pixels, 0, w, 0, 0, w, h);
            return mbitmap;
        }
        catch (Exception e){
            Log.e(TAG, "stringToBitmapWithLogo: "+e.getMessage() );
        }
        return  null;
    }

    private static Bitmap getScaleLogo(Bitmap logo,int w,int h){
        if(logo == null)return null;
        Matrix matrix = new Matrix();
        float scaleFactor = Math.min(w * 1.0f / 5 / logo.getWidth(), h * 1.0f / 5 /logo.getHeight());
        matrix.postScale(scaleFactor,scaleFactor);
        Bitmap result = Bitmap.createBitmap(logo, 0, 0, logo.getWidth(),   logo.getHeight(), matrix, true);
        return result;
    }
}