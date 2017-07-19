# 下载的应用

* [引用](#引用)
* [参考地址](#参考地址)
* [单个下载任务的使用](#单个下载任务的使用)


## 引用
build.gradle里面添加：
```
dependencies {
  compile 'com.arialyy.aria:aria-core:3.1.8'
  annotationProcessor 'com.arialyy.aria:aria-compiler:3.1.8'
}
```
AndroidManifest.xml中添加：
```
<uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS"/>
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
```


## 单个下载任务的使用
设置下载地址和保存路径
```
downloadTarget = Aria.download(this).load(downloadUrl).setDownloadPath(savePath);
```
将对象注册到Aria中
```
Aria.download(this).register();
```
获取下载速度
```
Aria.get(this).getDownloadConfig().setConvertSpeed(true);
```
监听下载状态
```
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
            Log.e("dawn", "progress " + progress);
            progressBar.setProgress(progress);
        }
        tvSpeed.setText(task.getConvertSpeed());
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
```
任务开始，暂停和删除
```
downloadTarget.start();
downloadTarget.pause();
downloadTarget.cancel();
```



## 参考地址

[https://github.com/AriaLyy/Aria](https://github.com/AriaLyy/Aria "参考地址")