package com.feiyu.smarthome;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SystemActivity extends Activity {

	private ListView list;
	private SimpleAdapter adapter;
	private GridView gv;
	private int[] imageIds=new int[]
			{
				R.drawable.system_logout,R.drawable.system_gw_pwd,
				R.drawable.system_about
						};
	private String[] text={
			"ע��","��������","����"
	};
	private ImageView home=null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.system);    
        
        home=(ImageView)this.findViewById(R.id.home);
        home.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(SystemActivity.this, "fan", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent (SystemActivity.this,HomeActivity.class);	
				startActivity(intent);
				SystemActivity.this.finish();
			}
		});
        
        gv = (GridView) findViewById(R.id.gridview);  
        
        //���ɶ�̬���飬����ת������   
        ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();  
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
				Toast.makeText(SystemActivity.this, "����������", Toast.LENGTH_SHORT).show();
				if(arg2==0)
				{
					Toast.makeText(SystemActivity.this, "����������2", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent (SystemActivity.this,Exit.class);	
					startActivity(intent);
				}
				if(arg2==1)
				{
					Toast.makeText(SystemActivity.this, "����������2", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent (SystemActivity.this,SetMessageActivity.class);	
					startActivity(intent);
				}
				if(arg2==2)
				{
					Toast.makeText(SystemActivity.this, "����������2", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent (SystemActivity.this,AboutActivity.class);	
					startActivity(intent);
				}
			}
        });

    }

    
}
