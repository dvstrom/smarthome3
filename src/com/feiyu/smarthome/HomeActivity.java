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
			"场景","区域","功能","监控","系统","更多"
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
        //警报响起
/*        imageView=(ImageView)this.findViewById(R.id.baojing);
        imageView.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(HomeActivity.this, "我的", Toast.LENGTH_SHORT).show();
				//Intent intent = new Intent (HomeActivity.this,DeviceActivity.class);
				Intent intent = new Intent (HomeActivity.this,MoreActivity.class);	
				startActivity(intent);
			}
		});
        
        handler=new Handler(){
			@Override
			public void handleMessage(Message msg){
				switch(msg.what){
				case 0:imageView.setBackgroundResource(bg[(Integer) msg.obj]);Toast.makeText(HomeActivity.this, "这里有危险", Toast.LENGTH_SHORT).show();
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
        //生成动态数组，并且转入数据   
        lstImageItem = new ArrayList<HashMap<String, Object>>();  
        for(int i=0;i<imageIds.length;i++)  
        {  
          HashMap<String, Object> map = new HashMap<String, Object>();  
          map.put("ItemImage", imageIds[i]);//添加图像资源的ID   
          map.put("ItemText", text[i]);//按序号做ItemText   
          lstImageItem.add(map);  
        }  
        //生成适配器的ImageItem <====> 动态数组的元素，两者一一对应   
        SimpleAdapter saImageItems = new SimpleAdapter(this, //没什么解释   
                                                  lstImageItem,//数据来源    
                                                  R.layout.image_text,//night_item的XML实现   
                                                    
                                                  //动态数组与ImageItem对应的子项           
                                                  new String[] {"ItemImage","ItemText"},   
                                                    
                                                  //ImageItem的XML文件里面的一个ImageView,两个TextView ID   
                                                  new int[] {R.id.ItemImage,R.id.ItemText});  
        //添加并且显示   
        gv.setAdapter(saImageItems); 
        gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				//场景
				if(arg2==0)
				{
					Intent intent = new Intent (HomeActivity.this,SettingsActivity.class);	
					startActivity(intent);
				}
				//区域
				if(arg2==1)
				{
					Toast.makeText(HomeActivity.this, "我的"+lstImageItem.get(arg2).get("ItemText").toString(), Toast.LENGTH_SHORT).show();
					Intent intent = new Intent (HomeActivity.this,AeraActivity.class);	
					startActivity(intent);
				}
				//功能
				if(arg2==2)
				{
					Toast.makeText(HomeActivity.this, "我的"+lstImageItem.get(arg2).get("ItemText").toString(), Toast.LENGTH_SHORT).show();
					//Intent intent = new Intent (HomeActivity.this,DeviceActivity.class);
					Intent intent = new Intent (HomeActivity.this,DeviceActivity.class);	
					startActivity(intent);
				}
				//摄像头
				if(arg2==3)
				{
					Toast.makeText(HomeActivity.this, "我的"+lstImageItem.get(arg2).get("ItemText").toString(), Toast.LENGTH_SHORT).show();
					//Intent intent = new Intent (HomeActivity.this,DeviceActivity.class);
					Intent intent = new Intent (HomeActivity.this,CameraActivity.class);	
					startActivity(intent);
				}
				//系统
				if(arg2==4)
				{
					Toast.makeText(HomeActivity.this, "我的"+lstImageItem.get(arg2).get("ItemText").toString(), Toast.LENGTH_SHORT).show();
					//Intent intent = new Intent (HomeActivity.this,DeviceActivity.class);
					Intent intent = new Intent (HomeActivity.this,SystemActivity.class);	
					startActivity(intent);
				}
				//更多
				if(arg2==5)
				{
					Toast.makeText(HomeActivity.this, "我的"+lstImageItem.get(arg2).get("ItemText").toString(), Toast.LENGTH_SHORT).show();
					//Intent intent = new Intent (HomeActivity.this,DeviceActivity.class);
					Intent intent = new Intent (HomeActivity.this,MoreActivity.class);	
					startActivity(intent);
				}
			}
		});
    }
    
    
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {  //获取 back键

    		Toast.makeText(HomeActivity.this, "点击这个场景2", Toast.LENGTH_SHORT).show();
    		Intent intent = new Intent (HomeActivity.this,Exit.class);	
			startActivity(intent);
    	}
    	return false;
    }
    
   
    
}
