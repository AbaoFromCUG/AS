package com.ruisi.rcode.rcode;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.abao.rcode.bitmap.CodeUtils;


/**
 * Created by Abao on 2017/5/26.
 */

public class GetBitMapActivity extends Activity {
    private static final String TAG = "GetBitMapActivity";

    protected Button button1;
    protected Button button2;
    protected EditText editText;
    protected ImageView imageView;
    protected Bitmap mBitmap;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getbitmap);
        button1= (Button) findViewById(R.id.button1_content);
        button2= (Button) findViewById(R.id.button2_content);
        editText= (EditText) findViewById(R.id.edit_content);
        imageView= (ImageView) findViewById(R.id.image_content);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textContent = editText.getText().toString();
                if (TextUtils.isEmpty(textContent)) {
                    Toast.makeText(GetBitMapActivity.this, "您的输入为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.d(TAG, "onClick: button1click");
                editText.setText("");
                mBitmap = CodeUtils.stringToBitmapWithLogo(textContent, 400, 400, null);
                imageView.setImageBitmap(mBitmap);
            }
        });
    }
}
