package com.feiyu.smarthome;

import java.util.ArrayList;
import java.util.HashMap;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SafeControlActivity extends Fragment {
	private ListView list;
	//private SimpleAdapter adapter;
	ArrayList<HashMap<String, Object>> lstImageItem;
	SimpleAdapter saImageItems ;
	private int[] imageId=new int[]
			{R.drawable.device_default_gas,R.drawable.device_default_smoke,
			R.drawable.device_default_gas,R.drawable.device_default_smoke,
			R.drawable.device_default_gas,R.drawable.device_default_smoke};
	private String[] textId1={"��ȼ��","����","��ȼ��","����","��ȼ��","����",};
	private String[] textId2={"����","����","����",
            "����","����","����"};
	private String[] textId3={"����","����","����",
            "����","����","����"};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View v= inflater.inflate(R.layout.lamp, container, false);
		list=(ListView)v.findViewById(R.id.list);
		//���ɶ�̬���飬����ת������   
        lstImageItem = new ArrayList<HashMap<String, Object>>();  
        for(int i=0;i<imageId.length;i++)  
        {  
          HashMap<String, Object> map = new HashMap<String, Object>();  
          map.put("ItemImage", imageId[i]);//���ͼ����Դ��ID   
          map.put("ItemText1", textId1[i]);//�������ItemText  
          map.put("ItemText2", textId2[i]);
          map.put("ItemText3", textId3[i]);
          lstImageItem.add(map);  
        }  
        //������������ImageItem <====> ��̬�����Ԫ�أ�����һһ��Ӧ   
        saImageItems = new SimpleAdapter(this.getActivity(), //ûʲô����   
                                                  lstImageItem,//������Դ    
                                                  R.layout.lamp_style,//night_item��XMLʵ��   
                                                    
                                                  //��̬������ImageItem��Ӧ������           
                                                  new String[] {"ItemImage","ItemText1","ItemText2","ItemText3"},   
                                                    
                                                  //ImageItem��XML�ļ������һ��ImageView,����TextView ID   
                                                  new int[] {R.id.image,R.id.text1,R.id.text2,R.id.text3});  
        //��Ӳ�����ʾ   
        list.setAdapter(saImageItems); 
        list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "�ҵ�"+lstImageItem.get(arg2).get("ItemText2").toString(), Toast.LENGTH_SHORT).show();
				//lstImageItem.get(arg2).put("ItemText2", "��");
				if(lstImageItem.get(arg2).get("ItemText1").toString().equals("��ȼ��"))
				{
					//Intent intent = new Intent (getActivity(),JackActivity.class);			
					//startActivity(intent);
					
				}
				if(lstImageItem.get(arg2).get("ItemText1").toString().equals("����"))
				{
					//Intent intent = new Intent (getActivity(),JakeActivity.class);			
					//startActivity(intent);
				}

				//list.setAdapter(saImageItems);
			}
		});
        return v;
	}
	

}


