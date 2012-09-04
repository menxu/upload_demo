package com.layout;

import grimbo.android.demo.slidingmenu.HorzScrollWithListMenu;
import grimbo.android.demo.slidingmenu.MyHorizontalScrollView;
import grimbo.android.demo.slidingmenu.R;
import grimbo.android.demo.slidingmenu.ViewUtils;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class FootNode {
    public static void setView(Context context,LayoutInflater inflater,MyHorizontalScrollView scrollView,View foot_view){
    	View layout_app = inflater.inflate(R.layout.horz_scroll_app, null);
        ViewGroup tabBar = (ViewGroup) layout_app.findViewById(R.id.tabBar);
        ListView listView = (ListView) layout_app.findViewById(R.id.list);
        
        ViewUtils.initListView(context, listView, "Item ", 30, android.R.layout.simple_list_item_1);
        
        ImageView btnSlide = (ImageView) tabBar.findViewById(R.id.BtnSlide);
        
        btnSlide.setOnClickListener(new HorzScrollWithListMenu.ClickListenerForScrolling(scrollView, foot_view));
        
        // Create a transparent view that pushes the other views in the HSV to the right.
        // This transparent view allows the menu to be shown when the HSV is scrolled.        
        View transparent = new TextView(context);
        transparent.setBackgroundColor(android.R.color.black);
        final View[] children = new View[] { transparent, layout_app };
        // Scroll to app (view[1]) when layout finished.
        int scrollToViewIdx = 1;
        
        
        scrollView.initViews(children, scrollToViewIdx, new HorzScrollWithListMenu.SizeCallbackForMenu(btnSlide));
	
    }
    
    
    
    
    
    
    
    
    
    
    
    public static void setView_1(Context context,LayoutInflater inflater,MyHorizontalScrollView scrollView,View foot_view){
    	View layout_app = inflater.inflate(R.layout.horz_scroll_app, null);
        ViewGroup tabBar = (ViewGroup) layout_app.findViewById(R.id.tabBar);
        ListView listView = (ListView) layout_app.findViewById(R.id.list);
        ViewUtils.initListView(context, listView, "Item ", 30, android.R.layout.simple_list_item_1);
        
        ImageView btnSlide = (ImageView) tabBar.findViewById(R.id.BtnSlide);
        btnSlide.setOnClickListener(new HorzScrollWithListMenu.ClickListenerForScrolling(scrollView, foot_view));
        // Create a transparent view that pushes the other views in the HSV to the right.
        // This transparent view allows the menu to be shown when the HSV is scrolled.        
        View transparent = new TextView(context);
        transparent.setBackgroundColor(android.R.color.black);
        final View[] children = new View[] { transparent, layout_app };
        // Scroll to app (view[1]) when layout finished.
        int scrollToViewIdx = 1;
        scrollView.initViews(children, scrollToViewIdx, new HorzScrollWithListMenu.SizeCallbackForMenu(btnSlide));
	
    }
}
