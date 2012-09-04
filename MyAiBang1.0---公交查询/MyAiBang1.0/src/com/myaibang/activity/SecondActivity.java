package com.myaibang.activity;




import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost.TabSpec;

public class SecondActivity extends TabActivity implements OnCheckedChangeListener {
	
	private RadioGroup group;
	private TabHost tabHost;
	public static final String TAB_ITEM_1 = "tabItem1";
	public static final String TAB_ITEM_2 = "tabItem2";
	public static final String TAB_ITEM_3 = "tabItem3";
	public static final String TAB_ITEM_4 = "tabItem4";
	public static final String TAB_ITEM_5 = "tabItem5";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
                WindowManager.LayoutParams.FLAG_FULLSCREEN);  
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		
		group = (RadioGroup) findViewById(R.id.main_radio);
		group.setOnCheckedChangeListener(this);
		
		tabHost = this.getTabHost();
		
		TabSpec tab1 = tabHost.newTabSpec(TAB_ITEM_1);
		TabSpec tab2 = tabHost.newTabSpec(TAB_ITEM_2);
		TabSpec tab3 = tabHost.newTabSpec(TAB_ITEM_3);
		TabSpec tab4 = tabHost.newTabSpec(TAB_ITEM_4);
		TabSpec tab5 = tabHost.newTabSpec(TAB_ITEM_5);
		
		tab1.setIndicator(TAB_ITEM_1).setContent(new Intent(this, RouteActivity.class));
		tab2.setIndicator(TAB_ITEM_2).setContent(new Intent(this, TransferActivity.class));
		tab3.setIndicator(TAB_ITEM_3).setContent(new Intent(this, StationActivity.class));
		tab4.setIndicator(TAB_ITEM_4).setContent(new Intent(this, EatingActivity.class));
		tab5.setIndicator(TAB_ITEM_5).setContent(new Intent(this, MoreActivity.class));
		
		tabHost.addTab(tab1);
		tabHost.addTab(tab2);
		tabHost.addTab(tab3);
		tabHost.addTab(tab4);
		tabHost.addTab(tab5);
	}
	
	public void onCheckedChanged(RadioGroup group, int checkedId) 
	{
		switch (checkedId) 
		{
		case R.id.radio_button1:
			tabHost.setCurrentTabByTag(TAB_ITEM_1);
			break;
		case R.id.radio_button2:
			tabHost.setCurrentTabByTag(TAB_ITEM_2);
			break;
		case R.id.radio_button3:
			tabHost.setCurrentTabByTag(TAB_ITEM_3);
			break;
		case R.id.radio_button4:
			tabHost.setCurrentTabByTag(TAB_ITEM_4);
			break;
		case R.id.radio_button5:
			tabHost.setCurrentTabByTag(TAB_ITEM_5);
			break;
		default:
			break;
		}
	}

	
}
