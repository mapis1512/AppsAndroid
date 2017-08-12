package com.example.a71.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.a71.myapplication.service.MyService;

/**
 * Created by 71 on 8/12/2017.
 */

public class MyBroadcats extends BroadcastReceiver {
private BroadCastListener listener;
    public interface BroadCastListener{
        void onMemommoryResult(String mem);
    }

    public MyBroadcats(BroadCastListener listener) {
        this.listener= listener;
    }
    public MyBroadcats() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action=intent.getAction();
        if(action.equals(MyService.MY_ACTION)){
            String mem=intent.getStringExtra("mem");
            //text.setText(mem);
            if(listener!=null){
                listener.onMemommoryResult(mem);
            }
        }
    }
}
