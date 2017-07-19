package com.dawn.downloaddawn;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arialyy.annotations.Download;
import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.download.DownloadTarget;
import com.arialyy.aria.core.download.DownloadTask;

/**
 * Created by 90449 on 2017/7/19.
 */

public class SingleDownloadActivity extends AppCompatActivity {
    private String downloadUrl = "http://downloadtest.189cube.com/update/sdk/upload/attachment/sdk/201707/041613255dyf.zip";
    private String savePath = Environment.getExternalStorageDirectory().getPath() + "/download_dawn" + "/test.apk";
    private DownloadTarget downloadTarget;
    private ProgressBar progressBar;
    private TextView tvSize;
    private TextView tvSpeed;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_download);
        downloadTarget = Aria.download(this).load(downloadUrl).setDownloadPath(savePath);
        Aria.download(this).register();
        Aria.get(this).getDownloadConfig().setConvertSpeed(true);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        tvSize = (TextView) findViewById(R.id.size);
        tvSpeed = (TextView) findViewById(R.id.speed);
    }

    @Download.onPre
    void taskPre(DownloadTask downloadTask){
        Log.e("dawn", "task pre");
        tvSize.setText(MathUtil.formatFileSize(downloadTask.getDownloadEntity().getFileSize()));
    }
    @Download.onTaskStart
    void taskStart(DownloadTask downloadTask){
        Log.e("dawn", "task start");
    }
    @Download.onTaskRunning
    void taskRunning(DownloadTask task) {
        Log.e("dawn", "task running");
        long current = task.getCurrentProgress();
        long len = task.getFileSize();
        if (len == 0) {
            progressBar.setProgress(0);
        } else {
            int progress = (int) ((current * 100) / len);
            Log.e("dawn", "task running progress " + progress);
            progressBar.setProgress(progress);
        }
        String speed = task.getConvertSpeed();
        Log.e("dawn", "task running speed " + speed);
        tvSpeed.setText(speed);
    }
    @Download.onTaskResume
    void taskResume(DownloadTask task) {
        Log.e("dawn", "task resume");
    }
    @Download.onTaskStop
    void taskStop(DownloadTask task) {
        Log.e("dawn", "task stop");
    }
    @Download.onTaskCancel
    void taskCancel(DownloadTask task) {
        Log.e("dawn", "task cancel");
        progressBar.setProgress(0);
    }
    @Download.onTaskFail
    void taskFail(DownloadTask task) {
        Log.e("dawn", "task fail");
    }
    @Download.onTaskComplete
    void taskComplete(DownloadTask task) {
        Log.e("dawn", "task complete");
        progressBar.setProgress(100);
    }

    protected void start(View view){
        downloadTarget.start();
    }
    protected void pause(View view){
        downloadTarget.pause();
    }
    protected void cancel(View view){
        downloadTarget.cancel();
    }
}
