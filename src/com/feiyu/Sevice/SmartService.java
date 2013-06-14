package com.feiyu.Sevice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import com.feiyu.connect.AbstractMessage;
import com.feiyu.connect.LongConnClient;

import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-6-3
 * Time: 上午10:50
 * To change this template use File | Settings | File Templates.
 */
public class SmartService extends Service {
    private LongConnClient longsocket = null;
    private String ip = "192.168.1.178";
    private int port = 1819;
    private  final LocalBinder mBinder=new LocalBinder();

    /** 内部类返回service
     *
     */
     public class LocalBinder extends Binder {
        /*public SmartService getService(){
            return SmartService.this;
        }*/
        public Boolean sMessage(AbstractMessage message){
            Boolean bsend=longsocket.Send(longsocket.getSocket(), message);
            return  bsend;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void onCreate() {
        super.onCreate();    //To change body of overridden methods use File | Settings | File Templates.
        longsocket=new LongConnClient(ip, port);
        android.os.Debug.waitForDebugger();
        longsocket.start();
    }
    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        System.out.println("Service destroy");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        android.os.Debug.waitForDebugger();
        return Service.START_CONTINUATION_MASK;
    }



}
