package com.feiyu.smarthome;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends Activity {

	Button login=null;
	Button demon=null;
	ImageView home=null;
	private Handler handler;
	private Thread clockThread;
	private boolean isRunning=true;
	private int[] bg=new int[]{
			R.drawable.home_title,R.drawable.home_title_midle,R.drawable.home_title_less,
			R.drawable.home_title_none
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		login=(Button)this.findViewById(R.id.button_login);
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(true)
				{
					isRunning=false;
					Toast.makeText(LoginActivity.this, "正常登陆", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent (LoginActivity.this,LoadingActivity.class);
					startActivity(intent);			
					LoginActivity.this.finish();
				}
			}
		});
		demon=(Button)this.findViewById(R.id.button_demo);
		demon.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(true)
				{
					isRunning=false;
					Toast.makeText(LoginActivity.this, "进行演示", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent (LoginActivity.this,HomeActivity.class);
					startActivity(intent);			
					LoginActivity.this.finish();
				}
			}
		});
		home=(ImageView)this.findViewById(R.id.imageView_home_title);
		handler=new Handler(){
			@Override
			public void handleMessage(Message msg){
				switch(msg.what){
				case 0:home.setBackgroundResource(bg[(Integer) msg.obj]);
				}
			}
		};
		clockThread=new Thread(){
			@Override
			public void run(){
				int timer=0;
				while(isRunning){
					try{
						Thread.currentThread().sleep(1000);
						timer++;
						if(timer==4){
							timer=0;
						}
							
						Message msg=new Message();
						msg.obj=timer;
						msg.what=0;
						handler.sendMessage(msg);
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}
			}
		};
		clockThread.start();
	}

}
