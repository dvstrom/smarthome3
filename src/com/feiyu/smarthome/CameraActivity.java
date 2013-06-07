package com.feiyu.smarthome;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CameraActivity  extends FragmentActivity {

	private ImageView setting=null;
	private TextView settingText=null;
	private ViewPager middle=null;
	private ArrayList<Fragment> fragmentsList;
	private int index=0;
	private ImageView home=null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera);

		home=(ImageView)this.findViewById(R.id.home);
        home.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent (CameraActivity.this,HomeActivity.class);	
				startActivity(intent);
				CameraActivity.this.finish();
			}
		});
		init();
		setting=(ImageView)this.findViewById(R.id.settings);
		setting.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent (CameraActivity.this,MainTopRightDialog.class);			
				startActivity(intent);	
			}
		});
		
		
	}
	
	public void init()
	{
		middle=(ViewPager)this.findViewById(R.id.tabpager);
		middle.setOnPageChangeListener(new MyOnPageChangeListener());
		fragmentsList= new ArrayList<Fragment>();
		Fragment f1 = new SetCamera1Activity();
		Fragment f2 = new SetCamera2Activity();
		fragmentsList.add(f1);
	    fragmentsList.add(f2);
	    middle.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentsList));
	    middle.setCurrentItem(index); 
	}
	
	public class MyOnPageChangeListener implements OnPageChangeListener {
		@Override
		public void onPageSelected(int arg0) {
			Toast.makeText(CameraActivity.this,"这是第" + index+ "张图片",3).show();
				index=arg0;
				middle.setCurrentItem(index); 
			
  }

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}
  }
}