package com.myaibang.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class RouteResultActivity extends Activity {
	
	private static final String TAG="MyResult";
	
	private ListView listView2;
	
	List<String> data = new ArrayList<String>();
	private String infoData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
                WindowManager.LayoutParams.FLAG_FULLSCREEN);  
        
        super.onCreate(savedInstanceState);
		setContentView(R.layout.route_result); 
		
		
		
		Intent intent=getIntent();
		String value=intent.getStringExtra("resultData");
//		TextView et=(TextView)findViewById(R.id.route_result);
//		et.setText(value);
		
		Log.v(TAG, "返回的value的长度值为"+value.length());
		int j=0;
//		char infoData=value.charAt(3);
//		Log.v(TAG, "返回的infoData值为"+infoData);
//		data.add("测试数据1");
//      data.add("测试数据2");
//      data.add("测试数据3");
//      data.add("测试数据4");
		
		for(int i=0;i<value.length();i++){
			if(value.charAt(i)==';'){
				infoData=value.substring(j,i);
				Log.v(TAG, "返回的infoData值为"+infoData);
				j=i+1;
				data.add(infoData);
			}
			if(i==value.length()-1){
				infoData=value.substring(j,i+1);
				Log.v(TAG, "返回的infoData值为"+infoData);
				data.add(infoData);
			}
			
			
			
		}
		
		Log.v(TAG, "返回data的值为"+data);
		Log.v(TAG, "返回的值为"+value);
		
		
		
		listView2=(ListView)findViewById(R.id.listView2);
    	listView2.setBackgroundColor(R.color.red);
		listView2.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,data));
		
		
	}

}



























