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

public class TelevisionActivity extends Activity {
	
	private ImageView tv;
	private Button open;
	private Button close;
	private Handler handler;
	private Thread clockThread1;
	private Thread clockThread2;

	private boolean isRunning=true;
	private boolean flag1=true;
	private boolean flag2=false;
	private int[] bg=new int[]{
			R.drawable.device_tv_close,
			R.drawable.device_tv_open
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.television);
		
		tv=(ImageView)findViewById(R.id.tv);
		open=(Button)findViewById(R.id.open);
		open.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tv.setBackgroundResource(bg[1]);
			}
		});
		close=(Button)findViewById(R.id.close);
		close.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tv.setBackgroundResource(bg[0]);
			}
		});
		
	}
}
