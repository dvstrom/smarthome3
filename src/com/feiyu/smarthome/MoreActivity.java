package com.feiyu.smarthome;

import java.util.ArrayList;
import java.util.HashMap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class MoreActivity extends Activity {
	private ListView list;
	//private SimpleAdapter adapter;
	
	private int[] imageId=new int[]
			{R.drawable.device_wallbtn_close,R.drawable.device_dimen_normal,R.drawable.device_default_light_close,
			 R.drawable.device_default_jack,R.drawable.device_jake_off,R.drawable.device_default_tv_1,
			 R.drawable.device_ir_button_normal,R.drawable.device_default_music,R.drawable.device_control_pscreen,R.drawable.device_window_close,R.drawable.device_control_shade_off,
			 R.drawable.device_shade_close,R.drawable.device_default_door_close,R.drawable.device_default_gas,
			 R.drawable.device_default_smoke,R.drawable.device_temp,R.drawable.device_default_themp,R.drawable.device_default_co2,R.drawable.device_default_lightsensor
			 };
	private String[] textId1={"��ͨ��","������","��ɫ��",
			                 "����","����","����","�յ�","����","ͶӰ��","����","��ͨ����","��ﴰ��","����",
			                 "��ȼ��","����","�¶�","ʪ��","PMֵ","����ǿ��"};
	private String[] textId2={"����","����","����","����","����",
            "����","����","����","����","����","����","����",
            "����","����","����","����","����","����","����"};
	private ImageView home=null;
	private Button button=null;
	private Handler handler;
	private Thread clockThread;

	private boolean isRunning=true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.more);
		
		home=(ImageView)this.findViewById(R.id.home);
        home.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent (MoreActivity.this,HomeActivity.class);	
				startActivity(intent);
				MoreActivity.this.finish();
			}
		});
		
		list=(ListView)this.findViewById(R.id.list);
		//���ɶ�̬���飬����ת������   
        ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();  
        for(int i=0;i<imageId.length;i++)  
        {  
          HashMap<String, Object> map = new HashMap<String, Object>();  
          map.put("imageLeft",imageId[i]);//���ͼ����Դ��ID   
          map.put("text1",textId1[i]);//�������ItemText  
          map.put("text2",textId2[i]);
          map.put("imageRight",R.drawable.stat_sys_signal_null);
          map.put("text3", "0");
          lstImageItem.add(map);  
        }  
        //������������ImageItem <====> ��̬�����Ԫ�أ�����һһ��Ӧ   
        SimpleAdapter saImageItems = new SimpleAdapter(this, //ûʲô����   
                                                  lstImageItem,//������Դ    
                                                  R.layout.more_style,//night_item��XMLʵ��   
                                                    
                                                  //��̬������ImageItem��Ӧ������           
                                                  new String[] {"imageLeft","text1","text2","imageRight","text3"},   
                                                    
                                                  //ImageItem��XML�ļ������һ��ImageView,����TextView ID   
                                                  new int[] {R.id.imageLeft,R.id.text1,R.id.text2,R.id.imageRight,R.id.text3});  
        //��Ӳ�����ʾ   
        list.setAdapter(saImageItems); 
        list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
				//��ͨ��
				if(arg2==0)
				{
					Intent intent = new Intent (MoreActivity.this,Lamp3Activity.class);			
					startActivity(intent);
				}
				if(arg2==1)
				{
					Intent intent = new Intent (MoreActivity.this,Lamp2Activity.class);			
					startActivity(intent);
				}
				if(arg2==2)
				{
					Intent intent = new Intent (MoreActivity.this,Lamp1Activity.class);			
					startActivity(intent);
				}
				if(arg2==3)
				{
					Intent intent = new Intent (MoreActivity.this,JackActivity.class);			
					startActivity(intent);
				}
				if(arg2==4)
				{
					Intent intent = new Intent (MoreActivity.this,JakeActivity.class);			
					startActivity(intent);
				}
				if(arg2==5)
				{
					Intent intent = new Intent (MoreActivity.this,TelevisionActivity.class);			
					startActivity(intent);
				}
				//�յ�
				if(arg2==6)
				{
					Intent intent = new Intent (MoreActivity.this,ContainerActivity.class);			
					startActivity(intent);
				}
				//����
				if(arg2==7)
				{
					Intent intent = new Intent (MoreActivity.this,AudioActivity.class);			
					startActivity(intent);
				}
				if(arg2==8)
				{
					Intent intent = new Intent (MoreActivity.this,ProjectorActivity.class);			
					startActivity(intent);
				}
				if(arg2==9)
				{
					Intent intent = new Intent (MoreActivity.this,WindowActivity.class);			
					startActivity(intent);
				}
				if(arg2==10)
				{
					Intent intent = new Intent (MoreActivity.this,ShadeActivity.class);			
					startActivity(intent);
				}
				if(arg2==11)
				{
					Intent intent = new Intent (MoreActivity.this,ShadeCartoonActivity.class);			
					startActivity(intent);
				}
				if(arg2==12)
				{
					Intent intent = new Intent (MoreActivity.this,DoorActivity.class);			
					startActivity(intent);
				}
			}
			
			});
        button=(Button)this.findViewById(R.id.fresh);
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				clockThread.start();
			}
		});
        handler=new Handler(){
			@Override
			public void handleMessage(Message msg){
				switch(msg.what){
				case 0:button.setText("����ˢ��......");
				}
			}
		};
		clockThread=new Thread(){
			@Override
			public void run(){
				int timer=0;
				while(isRunning){
					try{
						Thread.currentThread();
						Thread.sleep(500);
						timer++;
						
						if(timer==2){
							Intent intent = new Intent (MoreActivity.this,MoreActivity.class);
							startActivity(intent);			
							MoreActivity.this.finish();							
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
	
	}


}
