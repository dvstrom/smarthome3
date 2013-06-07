package com.feiyu.smarthome;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class ShadeCartoonActivity extends Activity {
	
	private ImageView shade;
	private Button open;
	private Button close;
	private Handler handler;
	private Thread clockThread1;
	private Thread clockThread2;

	private boolean isRunning=true;
	private boolean flag1=true;
	private boolean flag2=false;
	private int[] bg=new int[]{
			R.drawable.device_shade_close,R.drawable.device_shade_mid,
			R.drawable.device_shade_open
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shade_cartoon);
		shade=(ImageView)findViewById(R.id.shade);
		open=(Button)findViewById(R.id.open);
		close=(Button)findViewById(R.id.close);
		shade.setBackgroundResource(bg[0]);
		
		handler=new Handler(){
			@Override
			public void handleMessage(Message msg){
				switch(msg.what){
				case 0:shade.setBackgroundResource(bg[(Integer) msg.obj]);
				}
			}
		};
		clockThread1=new Thread(){
			@Override
			public void run(){
				int timer=0;
				while(isRunning){
					try{
						Thread.currentThread();
						Thread.sleep(500);
						timer++;
						
						if(timer==3){
							timer=2;
							
							break;
							
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
		clockThread2=new Thread(){
			@Override
			public void run(){
				int timer=2;
				while(isRunning){
					try{
						Thread.currentThread();
						Thread.sleep(500);
						timer--;
						
						if(timer==-1){
							timer=0;
							
							break;
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
		open.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(flag1&&!flag2){
					
					clockThread1.start();
					flag1=false;
					flag2=true;
					
				}
			}
		});
		close.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(flag2&&!flag1){
					
					clockThread2.start();
					flag2=false;
					flag1=true;
					
				}
			}
		});
	}

}
