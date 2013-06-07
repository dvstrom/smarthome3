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

public class AeraActivity  extends FragmentActivity {

	private TextView tvTitle=null; 	
	private GalleryView gallery=null; 	
	private AeraImageAdapter adapter=null;
	private ViewPager middle=null;
	private ArrayList<Fragment> fragmentsList;
	private int index=0;
	private ImageView home=null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.device);

		home=(ImageView)this.findViewById(R.id.home);
        home.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent (AeraActivity.this,HomeActivity.class);	
				startActivity(intent);
				AeraActivity.this.finish();
			}
		});
		
		initRes();
		init();
	}
	
	private void initRes(){
		tvTitle = (TextView) findViewById(R.id.tv);
		gallery = (GalleryView) findViewById(R.id.mygallery);
		adapter = new AeraImageAdapter(this); 	
		adapter.createReflectedImages();	// 创建倒影效果
		gallery.setAdapter(adapter);
		
		gallery.setOnItemSelectedListener(new OnItemSelectedListener() {	// 设置选择事件监听
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				tvTitle.setText(adapter.titles[position]);
				index=position;
				middle.setCurrentItem(index);
			    
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		gallery.setOnItemClickListener(new OnItemClickListener() {			// 设置点击事件监听
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(AeraActivity.this, "img " + (position+1) + " selected", Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	public void init()
	{
		middle=(ViewPager)this.findViewById(R.id.tabpager);
		middle.setOnPageChangeListener(new MyOnPageChangeListener());
		fragmentsList= new ArrayList<Fragment>();
		Fragment f1 = new Aera1Activity();
		Fragment f2 = new HouseAppliActivity();
		Fragment f3 = new DoorWindowActivity();
		Fragment f4 = new EnvironmentActivity();
		Fragment f5 = new SafeControlActivity();
		fragmentsList.add(f1);
	    fragmentsList.add(f2);
	    fragmentsList.add(f3);
	    fragmentsList.add(f4);
	    fragmentsList.add(f5);
	    middle.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentsList));
	    middle.setCurrentItem(index); 
	}
	
	public class MyOnPageChangeListener implements OnPageChangeListener {
		@Override
		public void onPageSelected(int arg0) {
			Toast.makeText(AeraActivity.this,"这是第" + index+ "张图片",3).show();
				gallery.setSelection(arg0);
				index=arg0;
			
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