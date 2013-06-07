package com.feiyu.smarthome;

import android.R.drawable;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.feiyu.connect.CommandMessage;
import com.feiyu.util.Const;


public class Lamp3Activity extends Activity{
	private ImageView imageView;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lamp3);   
        imageView=(ImageView)this.findViewById(R.id.lamp1);
        imageView.setOnClickListener(new OnClickListener() {
        	int i=0;
        	@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(i%2==0)
				{
					imageView.setImageResource(R.drawable.device_light_normal_on);
                    CommandMessage commmessage=new CommandMessage(2, Const.LAMP_REQUEST, Const.STRENGTHLAMP_UP, 27);
                    LogoActivity.mService.sendMessage(commmessage);
					i++;
				}else
				{
					imageView.setImageResource(R.drawable.device_light_normal_off);
					i++;
				}
			}
		});
	}
}