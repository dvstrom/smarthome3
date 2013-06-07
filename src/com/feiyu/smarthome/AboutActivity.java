package com.feiyu.smarthome;

import android.R.drawable;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
public class AboutActivity extends Activity{
	private ImageView imageView;
	private ImageView home=null;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);   
        home=(ImageView)this.findViewById(R.id.home);
        home.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(AboutActivity.this, "OOOKKK", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent (AboutActivity.this,HomeActivity.class);	
				startActivity(intent);
				AboutActivity.this.finish();
			}
		});
	}
}