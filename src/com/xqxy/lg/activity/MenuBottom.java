package com.xqxy.lg.activity;

import cn.jpush.android.api.JPushInterface;

import com.xqxy.hrht.R;
import com.xqxy.lg.activity.person.Person;
import com.xqxy.lg.activity.product.Home;
import com.xqxy.lg.activity.product.ProductList;
import com.xqxy.lg.activity.product.Search;
import com.xqxy.lg.activity.product.ShopCart;
import com.xqxy.lg.base.MyApplication;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.TextView;

public class MenuBottom extends TabActivity {
	/** Called when the activity is first created. */
	public static TabHost tabHost; // 底部菜单栏
	public static RadioGroup radioGroup;
	private Resources resources; //获取资源文件

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		resources = getResources();
		initData();
	}

	
	private void initData() {
		tabHost = this.getTabHost();
		TabHost.TabSpec spec;
		Intent intent;
		// 首页菜单
		intent = new Intent().setClass(this, Home.class);
		spec = tabHost.newTabSpec(resources.getString(R.string.main_menu_home))
				.setIndicator(resources.getString(R.string.main_menu_home))
				.setContent(intent);
		tabHost.addTab(spec);
		// 搜索菜单
		intent = new Intent().setClass(this, Search.class);
		spec = tabHost
				.newTabSpec(resources.getString(R.string.main_menu_search))
				.setIndicator(resources.getString(R.string.main_menu_search))
				.setContent(intent);
		tabHost.addTab(spec);

		// 列表菜单
		intent = new Intent().setClass(this, ProductList.class);
		spec = tabHost.newTabSpec(resources.getString(R.string.main_menu_list))
				.setIndicator(resources.getString(R.string.main_menu_list))
				.setContent(intent);
		tabHost.addTab(spec);

		// 购物车菜单
		intent = new Intent().setClass(this, ShopCart.class);
		spec = tabHost
				.newTabSpec(resources.getString(R.string.main_menu_shopcart))
				.setIndicator(resources.getString(R.string.main_menu_shopcart))
				.setContent(intent);
		tabHost.addTab(spec);

		// 个人中心菜单
		intent = new Intent().setClass(this, Person.class);
		spec = tabHost
				.newTabSpec(
						resources.getString(R.string.main_menu_personcenter))
				.setIndicator(
						resources.getString(R.string.main_menu_personcenter))
				.setContent(intent);
		tabHost.addTab(spec);

//		// 登录界面
//					intent = new Intent().setClass(this, PersonLogin.class);
//					spec = tabHost
//							.newTabSpec(resources.getString(R.string.person_login))
//							.setIndicator(resources.getString(R.string.person_login))
//							.setContent(intent);
//					tabHost.addTab(spec);

		tabHost.setCurrentTab(0);
		radioGroup = (RadioGroup) this
				.findViewById(R.id.main_tab_group);
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {

				switch (checkedId) {
				case R.id.main_tab_home:
					tabHost.setCurrentTabByTag(resources
							.getString(R.string.main_menu_home));
					break;
				case R.id.main_tab_search:
					tabHost.setCurrentTabByTag(resources
							.getString(R.string.main_menu_search));
					break;
				case R.id.main_tab_list:
					tabHost.setCurrentTabByTag(resources
							.getString(R.string.main_menu_list));
					break;
				case R.id.main_tab_shopcart:
					tabHost.setCurrentTabByTag(resources
							.getString(R.string.main_menu_shopcart));
					break;
				case R.id.main_tab_personcenter:
					tabHost.setCurrentTabByTag(resources
							.getString(R.string.main_menu_personcenter));
					break;
				default:
					break;
				}
			}
		});
	}
	@Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }
    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }


}