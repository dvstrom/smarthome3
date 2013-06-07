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
	private String[] textId1={"可燃气","烟雾","可燃气","烟雾","可燃气","烟雾",};
	private String[] textId2={"正常","正常","正常",
            "正常","正常","正常"};
	private String[] textId3={"大厅","大厅","卧室",
            "卧室","厨房","厨房"};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View v= inflater.inflate(R.layout.lamp, container, false);
		list=(ListView)v.findViewById(R.id.list);
		//生成动态数组，并且转入数据   
        lstImageItem = new ArrayList<HashMap<String, Object>>();  
        for(int i=0;i<imageId.length;i++)  
        {  
          HashMap<String, Object> map = new HashMap<String, Object>();  
          map.put("ItemImage", imageId[i]);//添加图像资源的ID   
          map.put("ItemText1", textId1[i]);//按序号做ItemText  
          map.put("ItemText2", textId2[i]);
          map.put("ItemText3", textId3[i]);
          lstImageItem.add(map);  
        }  
        //生成适配器的ImageItem <====> 动态数组的元素，两者一一对应   
        saImageItems = new SimpleAdapter(this.getActivity(), //没什么解释   
                                                  lstImageItem,//数据来源    
                                                  R.layout.lamp_style,//night_item的XML实现   
                                                    
                                                  //动态数组与ImageItem对应的子项           
                                                  new String[] {"ItemImage","ItemText1","ItemText2","ItemText3"},   
                                                    
                                                  //ImageItem的XML文件里面的一个ImageView,两个TextView ID   
                                                  new int[] {R.id.image,R.id.text1,R.id.text2,R.id.text3});  
        //添加并且显示   
        list.setAdapter(saImageItems); 
        list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "我的"+lstImageItem.get(arg2).get("ItemText2").toString(), Toast.LENGTH_SHORT).show();
				//lstImageItem.get(arg2).put("ItemText2", "开");
				if(lstImageItem.get(arg2).get("ItemText1").toString().equals("可燃气"))
				{
					//Intent intent = new Intent (getActivity(),JackActivity.class);			
					//startActivity(intent);
					
				}
				if(lstImageItem.get(arg2).get("ItemText1").toString().equals("烟雾"))
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


