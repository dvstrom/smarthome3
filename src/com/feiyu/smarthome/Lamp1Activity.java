package com.feiyu.smarthome;

import android.R.drawable;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.feiyu.Sevice.SmartService;
import com.feiyu.connect.LongConnClient;
import com.feiyu.connect.AbstractMessage;
import com.feiyu.connect.CommandMessage;
import com.feiyu.util.Const;
import com.feiyu.Sevice.SmartService;

/*public class Lamp1Activity extends Fragment{

	private ImageView imageView;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View v= inflater.inflate(R.layout.lamp1, container, false);
		imageView=(ImageView)v.findViewById(R.id.lamp1);
        imageView.setOnClickListener(new OnClickListener() {
        	int i=0;
        	@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(i%2==0)
				{
					imageView.setImageResource(R.drawable.device_default_light_open);
					i++;
				}else
				{
					imageView.setImageResource(R.drawable.device_default_light_close);
					i++;
				}
			}
		});
		return v;
	}
}*/
public class Lamp1Activity extends Activity{
	private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lamp1);   
        imageView=(ImageView)this.findViewById(R.id.lamp1);
       /* Intent intent = new Intent(this, SmartService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);*/
        imageView.setOnClickListener(new OnClickListener() {
        	int i=0;
        	@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(i%2==0)
				{
					imageView.setImageResource(R.drawable.device_default_light_open);
                    CommandMessage commmessage=new CommandMessage(2, Const.TV_REQUEST, Const.SELECTTV_CHANNAL, 27);
                    LogoActivity.mService.sendMessage(commmessage);
					i++;
				}else
				{
					imageView.setImageResource(R.drawable.device_default_light_close);
					i++;
				}
			}
		});
	}

 //   @Override
   /* protected void onStop() {
        super.onStop();    //To change body of overridden methods use File | Settings | File Templates.
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();    //To change body of overridden methods use File | Settings | File Templates.

    }
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // 已经绑定了LocalService，强转IBinder对象，调用方法得到LocalService对象
            binder = (SmartService.LocalBinder)service;
            mService=binder.getService();
            mBound=true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    };*/
}