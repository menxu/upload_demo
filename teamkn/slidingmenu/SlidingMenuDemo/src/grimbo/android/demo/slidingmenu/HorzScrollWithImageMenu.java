package grimbo.android.demo.slidingmenu;

import com.layout.FootNode;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This example uses a FrameLayout to display a menu View and a HorizontalScrollView (HSV).
 * 
 * The HSV has a transparent View as the first child, which means the menu will show through when the HSV is scrolled.
 */
public class HorzScrollWithImageMenu extends Activity implements OnClickListener{
	public static int view_show_layout ;
	LayoutInflater inflater;
	
    MyHorizontalScrollView scrollView;
    View foot_view;  //底层  图层 隐形部分
    RelativeLayout foot_rl_node;
    RelativeLayout foot_rl_chat;
    RelativeLayout foot_rl_search;
    RelativeLayout foot_rl_setting;
    
    boolean menuOut = false;
    Handler handler = new Handler();
    int btnWidth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inflater= LayoutInflater.from(this);
        setContentView(inflater.inflate(R.layout.horz_scroll_with_image_menu, null));

        scrollView = (MyHorizontalScrollView) findViewById(R.id.myScrollView);
        foot_view = findViewById(R.id.menu);
        
        foot_rl_node = (RelativeLayout)findViewById(R.id.foot_rl_node);
        foot_rl_chat = (RelativeLayout)findViewById(R.id.foot_rl_chat);
        foot_rl_search = (RelativeLayout)findViewById(R.id.foot_rl_search);
        foot_rl_setting = (RelativeLayout)findViewById(R.id.foot_rl_setting);
        
        
        FootNode.setView(
				HorzScrollWithImageMenu.this,
				inflater,
				scrollView, 
				foot_view  ); 
        
        foot_rl_node.setOnClickListener(this);
        foot_rl_chat.setOnClickListener(this);
        foot_rl_search.setOnClickListener(this);
        foot_rl_setting.setOnClickListener(this);
        
    }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.foot_rl_node:
			Toast.makeText(HorzScrollWithImageMenu.this, " node " +v.getId(), 200).show();
			if(!(view_show_layout == R.id.foot_rl_node) ){
				FootNode.setView(
						HorzScrollWithImageMenu.this,
						inflater,
						scrollView, 
						foot_view  ); 
			}
			break;
		case R.id.foot_rl_chat:
			Toast.makeText(HorzScrollWithImageMenu.this, " chat " +v.getId(), 200).show();
			if(!(view_show_layout == R.id.foot_rl_node) ){
				FootNode.setView_1(
						HorzScrollWithImageMenu.this,
						inflater,
						scrollView, 
						foot_view  ); 
			}
			break;
		case R.id.foot_rl_search:
			Toast.makeText(HorzScrollWithImageMenu.this, " search " +v.getId(), 200).show();
			break;
		case R.id.foot_rl_setting:
			Toast.makeText(HorzScrollWithImageMenu.this, " setting " +v.getId(), 200).show();
			break;

		default:
			break;
		}
		view_show_layout  = v.getId();
	}	
}
