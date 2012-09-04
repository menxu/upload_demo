package org.lxh.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.SlidingDrawer.OnDrawerScrollListener;
import android.widget.Toast;

public class MySlidingDrawerDemo extends Activity {
	private String data[] = { "北京魔乐科技", "www.mldnjava.cn", "讲师：李兴华",
			"中国高校讲课联盟", "www.jiangker.com"};					// 定义显示的数据
	private ListView listView;  									// 定义ListView组件
	private SlidingDrawer slidingDrawer ;							// 定义SlidingDrawer
	private ImageView handle ;										// 定义图片显示

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
		LinearLayout layout = (LinearLayout) super.findViewById(R.id.content) ;
		this.listView = new ListView(this) ;						// 实例化组件
		listView.setAdapter(new ArrayAdapter<String>(this,			// 将数据包装
				android.R.layout.simple_expandable_list_item_1, 	// 每行显示一条数据
				this.data));										// 设置组件内容
		layout.addView(this.listView) ;								// 增加组件
		this.slidingDrawer = (SlidingDrawer) super.findViewById(R.id.slidingdrawer) ;
		this.handle = (ImageView) super.findViewById(R.id.handle) ;// 取得组件
		this.slidingDrawer.setOnDrawerOpenListener(new OnDrawerOpenListenerImpl()) ;	// 设置监听										
		this.slidingDrawer.setOnDrawerCloseListener(new OnDrawerCloseListenerImpl()) ;	// 设置监听													
		this.slidingDrawer.setOnDrawerScrollListener(new OnDrawerScrollListenerImpl()) ;// 设置监听
	}
	private class OnDrawerOpenListenerImpl implements OnDrawerOpenListener {
		@Override
		public void onDrawerOpened() {
			handle.setImageResource(R.drawable.ico_right) ;			// 窗口打开监听
		}
	}
	private class OnDrawerCloseListenerImpl implements OnDrawerCloseListener {
		@Override
		public void onDrawerClosed() {
			handle.setImageResource(R.drawable.ico_left) ;			// 窗口关闭监听
		}
	}
	private class OnDrawerScrollListenerImpl implements OnDrawerScrollListener {
		@Override
		public void onScrollEnded() {								// 拖动结束
			Toast.makeText(MySlidingDrawerDemo.this, "窗口拖动结束。", Toast.LENGTH_SHORT).show();
		}
		@Override
		public void onScrollStarted() {								// 拖动开始
			Toast.makeText(MySlidingDrawerDemo.this, "正在拖动窗口。", Toast.LENGTH_SHORT).show();
		}
	}
}