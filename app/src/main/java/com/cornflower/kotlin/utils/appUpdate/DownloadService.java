package com.cornflower.kotlin.utils.appUpdate;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.content.FileProvider;

import com.cornflower.kotlin.utils.IOUtils;
import com.cornflower.kotlin.utils.LogManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import kotlin.cornflower.com.R;

public class DownloadService extends IntentService {
    // 10-10 19:14:32.618: D/DownloadService(1926): 测试缓存：41234 32kb
    // 10-10 19:16:10.892: D/DownloadService(2069): 测试缓存：41170 1kb
    // 10-10 19:18:21.352: D/DownloadService(2253): 测试缓存：39899 10kb
    private static final int BUFFER_SIZE = 10 * 1024; // 8k ~ 32K
    private static final String TAG = "DownloadService";
    private String id = "ztzqzg";

    private static final int NOTIFICATION_ID = 0;
    private static final int SUCCESS = 0;
    private static final int CHECK_MD5 = 2;
    private static final int FAIL = 1;
//    private static final int UPDATE_PROGRESS = 2;
    private NotificationManager mNotifyManager;
    private Builder mBuilder;
    private File apkFile;

    public DownloadService() {
        super("DownloadService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if(handler!=null)
        {
            handler.removeCallbacksAndMessages(null);
        }

        mNotifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(id, "中泰资管", NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableVibration(false);
            channel.setVibrationPattern(new long[]{0});
            channel.setSound(null, null);
            if (mNotifyManager != null) {
                mNotifyManager.createNotificationChannel(channel);
            }
            mBuilder = new NotificationCompat.Builder(this,id);
        }else {
            mBuilder = new Builder(this);
        }

        String appName = getString(getApplicationInfo().labelRes);
        int icon = getApplicationInfo().icon;

        mBuilder.setContentTitle(appName).setSmallIcon(icon);

        String urlStr = intent.getStringExtra("key");
        InputStream in = null;
        FileOutputStream out = null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(false);
            urlConnection.setConnectTimeout(10 * 1000);
            urlConnection.setReadTimeout(10 * 1000);
            urlConnection.setRequestProperty("Connection", "Keep-Alive");
            urlConnection.setRequestProperty("Charset", "UTF-8");
            urlConnection.setRequestProperty("Accept-Encoding", "gzip, deflate");

            urlConnection.connect();
            long bytetotal = urlConnection.getContentLength();
            long bytesum = 0;
            int byteread = 0;
            in = urlConnection.getInputStream();
            File dir = StorageUtils.getCacheDirectory(this);
            String apkName = urlStr.substring(urlStr.lastIndexOf("/") + 1, urlStr.length());
            apkFile = new File(dir, apkName);
            out = new FileOutputStream(apkFile);
            byte[] buffer = new byte[BUFFER_SIZE];

            int oldProgress = 0;
//            Message msgUpdate = Message.obtain();
            while ((byteread = in.read(buffer)) != -1) {
                bytesum += byteread;
                out.write(buffer, 0, byteread);

                int progress = (int) (bytesum * 100L / bytetotal);
                // 如果进度与之前进度相等，则不更新，如果更新太频繁，否则会造成界面卡顿
                if (progress != oldProgress) {
                    updateProgress(progress);
//                    handler.sendMessage(msgUpdate);
                }
                oldProgress = progress;
            }
            // 下载完成
            out.flush();
            out.getFD().sync();

            IOUtils.close(out);
            IOUtils.close(in);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                //关闭通知通道
                mNotifyManager.deleteNotificationChannel(id);
            }else{
                mNotifyManager.cancel(NOTIFICATION_ID);
            }
            String md5 = intent.getStringExtra("MD5");

            String cmd5 =  MD5Utils.getFileMD5String(apkFile);
            LogManager.i("md5:"+cmd5);
            Message msgSuccess = Message.obtain();
           if(cmd5.equals(md5)){
               msgSuccess.what = SUCCESS;
           }else {
               msgSuccess.what = CHECK_MD5;
           }

            handler.sendMessage(msgSuccess);


        }
        catch (Exception e) {
            LogManager.e(TAG, "download apk file error"+e.toString());
            Message msgFail = Message.obtain();
            msgFail.what = FAIL;
            handler.sendMessage(msgFail);
        }  finally {
            IOUtils.close(out);
            IOUtils.close(in);
        }



    }

    private void updateProgress(int progress) {
        //"正在下载:" + progress + "%"
        if(mBuilder==null||mNotifyManager==null)
            return;
        mBuilder.setContentText(this.getString(R.string.android_auto_update_download_progress, progress)).setProgress(100, progress, false);
        //setContentInent如果不设置在4.0+上没有问题，在4.0以下会报异常
        PendingIntent pendingintent = PendingIntent.getActivity(this, 0, new Intent(), PendingIntent.FLAG_CANCEL_CURRENT);
        mBuilder.setContentIntent(pendingintent);
        mNotifyManager.notify(NOTIFICATION_ID, mBuilder.build());
    }


    private void installAPk() {
        if(apkFile==null||!apkFile.exists())
            return;

        Intent intent = new Intent(Intent.ACTION_VIEW);
        //这里必须给权限777否则部分手机安装会出现解析包失败
        try {
            String[] command = {"chmod", "777", apkFile.toString()};
            ProcessBuilder builder = new ProcessBuilder(command);
            builder.start();
        } catch (IOException ignored) {
            LogManager.e("error",ignored);
        }
        Uri uri = null;
        if(Build.VERSION.SDK_INT>=24){//7.0以上适配
             uri = FileProvider.getUriForFile(this, "com.qlzgzg.nettrade.android.fileprovider", apkFile);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }else
            uri = Uri.fromFile(apkFile);

        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    private  Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case SUCCESS:
                    LogManager.i("app下载成功");
                    installAPk();

                    break;
                case CHECK_MD5:
//                    EventBus.getDefault().post(new MessageEvent(true,"app下载不完整，请重新下载"));
                    break;
                case FAIL:
//                    EventBus.getDefault().post(new MessageEvent(true,"下载失败，请重新下载"));
                    break;
//                case UPDATE_PROGRESS:
//                    updateProgress( msg.arg1);
//                    break;
            }

        }
    };


}
