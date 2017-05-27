package com.ruisi.rcode.rcode;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener{
    Button button;
    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        button= (Button) findViewById(R.id.button_first );
        imageView= (ImageView) findViewById(R.id.iv_result);
        textView= (TextView) findViewById(R.id.tv_result);
    }
    @Override
    public void onClick(View v) {
        startActivityForResult(new Intent(FirstActivity.this, CaptureActivity.class), 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK) {
            textView.setText(data.getStringExtra(CaptureActivity.EXTRA_RESULT));
            imageView.setImageBitmap((Bitmap)data.getParcelableExtra(CaptureActivity.EXTRA_BITMAP));
        } else {
            textView.setText("");
            imageView.setImageDrawable(null);
        }
    }

}
