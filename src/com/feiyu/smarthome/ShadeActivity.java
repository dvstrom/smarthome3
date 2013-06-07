package com.feiyu.smarthome;

import android.R.drawable;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class ShadeActivity extends Activity {
	private Button stop;
	private Button start;
	private Button half;
	private ImageView shade;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shade);
		stop=(Button)findViewById(R.id.stop);
		start=(Button)findViewById(R.id.start);
		half=(Button)findViewById(R.id.start_half);
		shade=(ImageView)findViewById(R.id.shade);
		shade.setBackgroundResource(R.drawable.device_control_shade_off);
		stop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
				shade.setBackgroundResource(R.drawable.device_control_shade_off);
			}
		});
		start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
				shade.setBackgroundResource(R.drawable.device_control_shade_on);
			}
		});
		half.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
				shade.setBackgroundResource(R.drawable.device_control_shade_mid);
			}
		});
	}

}
