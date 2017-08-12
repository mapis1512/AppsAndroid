package com.example.a71.myapplication.service;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = MyService.class.getSimpleName();
    private Handler handler;
    private Runnable runnable;
    private int count;
    public  static MyService instance;

    public MyService() {
    }
public static  boolean isInstance(){
    return instance !=null;
}
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        launchThread();
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        count = 0;
        handler = new Handler();
        instance=this;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
        instance=null;
    }

    private void launchThread() {
        runnable = new Runnable() {
            @Override
            public void run() {
                new MemmoryAsyncTask().execute();
                handler.postDelayed(this, 1000);
            }
        };
        runnable.run();
    }


    class MemmoryAsyncTask extends AsyncTask<Void, Void, String> {

        private ActivityManager activityManager;
        private ActivityManager.MemoryInfo memoryInfo;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            memoryInfo = new ActivityManager.MemoryInfo();
            activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        }

        @Override
        protected String doInBackground(Void... voids) {
            activityManager.getMemoryInfo(memoryInfo);
            return memoryInfo.availMem / 1024 + " KB";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d(TAG, "Memoria disponible: " + s);
            sendMemomory(s);
        }


    }
    public static String MY_ACTION="my_service";
    private void sendMemomory(String mem) {
        Intent intent=new Intent();
        intent.setAction(MY_ACTION);
        intent.putExtra("mem",mem);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
    }
}
