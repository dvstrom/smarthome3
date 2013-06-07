package com.feiyu.smarthome;

import java.util.ArrayList;
import java.util.HashMap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class HomeActivity extends Activity {

	public static HomeActivity instance = null;
	private PopupWindow menuWindow;
	private boolean menu_display = false;
	
	private ListView list;
	private SimpleAdapter adapter;
	private GridView gv;
	private ArrayList<HashMap<String, Object>> lstImageItem ;
	private int[] imageIds=new int[]
			{
				R.drawable.main_home_scene,R.drawable.main_home_room,
				R.drawable.main_home_devices,R.drawable.main_home_monitor,
				R.drawable.main_home_system,R.drawable.main_home_more,
						};
	private String[] text={
			"����","����","����","���","ϵͳ","����"
	};
	private ImageView imageView=null;
	private Handler handler;
	private Thread clockThread;
	private boolean isRunning=true;
	private int[] bg=new int[]{R.drawable.device_baojing_pressed,R.drawable.device_baojing_normal};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);       
        instance=this;
        //��������
/*        imageView=(ImageView)this.findViewById(R.id.baojing);
        imageView.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(HomeActivity.this, "�ҵ�", Toast.LENGTH_SHORT).show();
				//Intent intent = new Intent (HomeActivity.this,DeviceActivity.class);
				Intent intent = new Intent (HomeActivity.this,MoreActivity.class);	
				startActivity(intent);
			}
		});
        
        handler=new Handler(){
			@Override
			public void handleMessage(Message msg){
				switch(msg.what){
				case 0:imageView.setBackgroundResource(bg[(Integer) msg.obj]);Toast.makeText(HomeActivity.this, "������Σ��", Toast.LENGTH_SHORT).show();
				}
			}
		};
		clockThread=new Thread(){
			@Override
			public void run(){
				int timer=0;
				while(isRunning){
					try{
						if(timer==0)
						{
						Thread.currentThread().sleep(500);	
						Message msg=new Message();
						msg.obj=timer;
						msg.what=0;
						handler.sendMessage(msg);
						timer=1;
						}else
						{
							Thread.currentThread().sleep(500);	
							Message msg=new Message();
							msg.obj=timer;
							msg.what=0;
							handler.sendMessage(msg);
							timer=0;
						}
						
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}
			}
		};
		clockThread.start();*/
	
    
        gv = (GridView) findViewById(R.id.gv_button);  
        //���ɶ�̬���飬����ת������   
        lstImageItem = new ArrayList<HashMap<String, Object>>();  
        for(int i=0;i<imageIds.length;i++)  
        {  
          HashMap<String, Object> map = new HashMap<String, Object>();  
          map.put("ItemImage", imageIds[i]);//���ͼ����Դ��ID   
          map.put("ItemText", text[i]);//�������ItemText   
          lstImageItem.add(map);  
        }  
        //������������ImageItem <====> ��̬�����Ԫ�أ�����һһ��Ӧ   
        SimpleAdapter saImageItems = new SimpleAdapter(this, //ûʲô����   
                                                  lstImageItem,//������Դ    
                                                  R.layout.image_text,//night_item��XMLʵ��   
                                                    
                                                  //��̬������ImageItem��Ӧ������           
                                                  new String[] {"ItemImage","ItemText"},   
                                                    
                                                  //ImageItem��XML�ļ������һ��ImageView,����TextView ID   
                                                  new int[] {R.id.ItemImage,R.id.ItemText});  
        //��Ӳ�����ʾ   
        gv.setAdapter(saImageItems); 
        gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				//����
				if(arg2==0)
				{
					Intent intent = new Intent (HomeActivity.this,SettingsActivity.class);	
					startActivity(intent);
				}
				//����
				if(arg2==1)
				{
					Toast.makeText(HomeActivity.this, "�ҵ�"+lstImageItem.get(arg2).get("ItemText").toString(), Toast.LENGTH_SHORT).show();
					Intent intent = new Intent (HomeActivity.this,AeraActivity.class);	
					startActivity(intent);
				}
				//����
				if(arg2==2)
				{
					Toast.makeText(HomeActivity.this, "�ҵ�"+lstImageItem.get(arg2).get("ItemText").toString(), Toast.LENGTH_SHORT).show();
					//Intent intent = new Intent (HomeActivity.this,DeviceActivity.class);
					Intent intent = new Intent (HomeActivity.this,DeviceActivity.class);	
					startActivity(intent);
				}
				//����ͷ
				if(arg2==3)
				{
					Toast.makeText(HomeActivity.this, "�ҵ�"+lstImageItem.get(arg2).get("ItemText").toString(), Toast.LENGTH_SHORT).show();
					//Intent intent = new Intent (HomeActivity.this,DeviceActivity.class);
					Intent intent = new Intent (HomeActivity.this,CameraActivity.class);	
					startActivity(intent);
				}
				//ϵͳ
				if(arg2==4)
				{
					Toast.makeText(HomeActivity.this, "�ҵ�"+lstImageItem.get(arg2).get("ItemText").toString(), Toast.LENGTH_SHORT).show();
					//Intent intent = new Intent (HomeActivity.this,DeviceActivity.class);
					Intent intent = new Intent (HomeActivity.this,SystemActivity.class);	
					startActivity(intent);
				}
				//����
				if(arg2==5)
				{
					Toast.makeText(HomeActivity.this, "�ҵ�"+lstImageItem.get(arg2).get("ItemText").toString(), Toast.LENGTH_SHORT).show();
					//Intent intent = new Intent (HomeActivity.this,DeviceActivity.class);
					Intent intent = new Intent (HomeActivity.this,MoreActivity.class);	
					startActivity(intent);
				}
			}
		});
    }
    
    
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {  //��ȡ back��

    		Toast.makeText(HomeActivity.this, "����������2", Toast.LENGTH_SHORT).show();
    		Intent intent = new Intent (HomeActivity.this,Exit.class);	
			startActivity(intent);
    	}
    	return false;
    }
    
   
    
}
