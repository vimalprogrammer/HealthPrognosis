package com.huawei.healthprognosis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void callnext(View view) {
       // new Handler().postDelayed(new Runnable()
       // {
           // @Override
         //   public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(MainActivity.this,MenuActivity.class);
                MainActivity.this.startActivity(mainIntent);
                MainActivity.this.finish();
          //  }
       // }, SPLASH_DISPLAY_LENGTH);
    }
}