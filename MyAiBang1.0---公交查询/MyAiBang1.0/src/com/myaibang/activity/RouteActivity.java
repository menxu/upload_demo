package com.myaibang.activity;



import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.aibang.open.client.AibangApi;
import com.aibang.open.client.exception.AibangIOException;
import com.aibang.open.client.exception.AibangServerException;
import com.myaibang.domain.Line;



public class RouteActivity extends Activity {
	
	private static final String TAG="MyResult";

	private static final String API_KEY="766cb8dbbdb7fba92de17f0b2d88745b";
	private AibangApi mAibang;
    private AibangAsyncTask mAsyncTask;
	
    private Button button1;
	private EditText editText;
	private ListView listView;
	
	private String routeData;
	
	List<String> data = new ArrayList<String>();
	
	List<Line> list=new ArrayList<Line>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.route);  
		
		mAibang=new AibangApi(API_KEY);
		button1=(Button)findViewById(R.id.route_bt);
		editText=(EditText)findViewById(R.id.route_et);
		
		listView=(ListView)findViewById(R.id.listView1);
    	listView.setBackgroundColor(R.color.red);
    	
    	listView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Log.v(TAG, "返回的arg1为"+arg1);
				Log.v(TAG, "返回的arg2为"+arg2);
				Log.v(TAG, "返回的arg3为"+arg3);
				Log.v(TAG, "位置为的arg2返回的数据为"+list.get(arg2).getStats());
				
				//intent将数据传到RouteResultActivity
		        Intent intent=new Intent(RouteActivity.this,RouteResultActivity.class);
		        intent.putExtra("resultData", list.get(arg2).getStats());
		        startActivity(intent);
		        overridePendingTransition(R.anim.push_left_in,
						R.anim.push_left_out);
			}
    		
		});
		
		button1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				if (mAsyncTask != null) {
		            mAsyncTask.cancel(true);
		        }
		        switch (v.getId()) {
		        case R.id.route_bt:
		            mAsyncTask = new AibangAsyncTask("search");
		            break;
		        
		        }
		        if (mAsyncTask != null) {
		            mAsyncTask.execute();
		        }

			}
		});
	
	}
	

	private class AibangAsyncTask extends AsyncTask<Void, Void, JSONObject> {
        public AibangAsyncTask(String action) {
            mAction = action;
        }

        protected void onPreExecute() {
            Toast.makeText(RouteActivity.this, "正在请求...", Toast.LENGTH_SHORT)
                    .show();
        }

        protected void onPostExecute(JSONObject result) {
            if (result == null) {
                String err = "位置错误";
                if (mException != null) {
                    if (mException instanceof AibangServerException) {
                        AibangServerException e = (AibangServerException) mException;
                        err = e.getStatusCode() + "\n" + e.getMessage() + "\n "
                                + e.getStackTrace();
                    } else if (mException instanceof AibangIOException) {
                        err = "网络错误\n" + mException.getStackTrace();
                    } else {
                        err = "" + mException.getStackTrace();
                    }
                }

                Toast.makeText(RouteActivity.this, "出错了", Toast.LENGTH_SHORT)
                        .show();
                
                
            } else {
            	//将json格式的结果数据解析到listview
		        listView.setAdapter(new ArrayAdapter<String>(RouteActivity.this, android.R.layout.simple_expandable_list_item_1,data));

            }
        }

        
        
        @Override
        protected JSONObject doInBackground(Void... params) {
            String result = null;
            
            mAibang.setProxy(getProxy());
            try {
                if ("search".equals(mAction)) {
                	routeData=editText.getText().toString();
                	result=mAibang.busLines("广州", routeData, 0);
                	
                	JSONObject jsonObject=new JSONObject(result);
                	
                	String result_num=jsonObject.getString("result_num");
                	String web_url=jsonObject.getString("web_url");
                	String wap_url=jsonObject.getString("wap_url");
                	String lines=jsonObject.getString("lines");
                	
                	JSONObject jsonObject2=new JSONObject(lines);
                	String line=jsonObject2.getString("line");
                	Log.v(TAG, "返回的line为"+line);
                	
                	
                	JSONArray jsonArray=new JSONArray(line);
                	

                	Log.v(TAG, "返回的line数组长度为"+jsonArray.length());

                	for(int i=0;i<jsonArray.length();i++){
                		jsonObject2=jsonArray.getJSONObject(i);
                		String name=jsonObject2.getString("name");
                		String info=jsonObject2.getString("info");
                		String stats=jsonObject2.getString("stats");
                		list.add(new Line(name,info,stats));
                		
                		data.add(name);
                		
//                		Log.v(TAG, "返回的name为"+name);
//                    	Log.v(TAG, "返回的info为"+info);
//                    	Log.v(TAG, "返回的stats为"+stats);
                	}

                	
                	Log.v(TAG, "返回的result_num为"+result_num);
                	Log.v(TAG, "返回的web_url为"+web_url);
                	Log.v(TAG, "返回的wap_url为"+wap_url);
                	Log.v(TAG, "返回的lines为"+lines);
                	Log.v(TAG, "返回的json为"+result);
                } 
            } catch (Exception e) {
                mException = e;
            }
            try {
                return new JSONObject(result);
            } catch (Exception e) {
                mException = e;
            }
            return null;
        }

        private String mAction;
        private Exception mException;
    }

    private HttpHost getProxy() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(
                Context.CONNECTIVITY_SERVICE);
        HttpHost none_host = null;
        if (cm == null) {
            return none_host;
        }

        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            return none_host;
        }

        if (ni.getType() == ConnectivityManager.TYPE_WIFI) {
            return null;
        } else if (ni.getType() == ConnectivityManager.TYPE_MOBILE) {
            String extra = ni.getExtraInfo();
            if (TextUtils.isEmpty(extra)) {
                return none_host;
            }

            extra = extra.toLowerCase();
            if (extra.contains("cmnet") || extra.contains("ctnet")
                    || extra.contains("uninet") || extra.contains("3gnet")) {
                return none_host;
            } else if (extra.contains("cmwap") || extra.contains("uniwap")
                    || extra.contains("3gwap")) {
                return new HttpHost("10.0.0.172");
            } else if (extra.contains("ctwap")) {
                return new HttpHost("10.0.0.200");
            }
        }

        return none_host;
    }

}
