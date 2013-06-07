package com.feiyu.smarthome;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class WindowActivity extends Activity {

	private Button close;
	private Button open;
	private Button mid;
	private ImageView window;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.window);
		close=(Button)findViewById(R.id.close);
		open=(Button)findViewById(R.id.open);
		mid=(Button)findViewById(R.id.mid);
		window=(ImageView)findViewById(R.id.window);
		window.setBackgroundResource(R.drawable.device_window_close);
		close.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
				window.setBackgroundResource(R.drawable.device_window_close);
			}
		});
		open.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
				window.setBackgroundResource(R.drawable.device_window_open);
			}
		});
		mid.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
				window.setBackgroundResource(R.drawable.device_window_mid);
			}
		});
	}


}