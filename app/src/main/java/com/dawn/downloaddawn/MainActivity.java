package com.dawn.downloaddawn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    protected void jumpToSingleDownload(View view){
        startActivity(new Intent(this, SingleDownloadActivity.class));
    }
}
