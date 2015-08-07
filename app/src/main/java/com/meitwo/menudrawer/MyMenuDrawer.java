package com.meitwo.menudrawer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class MyMenuDrawer extends Activity {

	MyListAdapter myListAdapter;
	ArrayList<String> arrListData;
	DrawerLayout drawLayout;
	ImageButton imgBtn;
	TextView tvContent;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.mymenudrawer);
		ListView lv = (ListView)this.findViewById(R.id.left_drawer);
		drawLayout= (DrawerLayout) this.findViewById(R.id.drawer_layout);
		imgBtn=(ImageButton) findViewById(R.id.img_btn);
		tvContent =(TextView)findViewById(R.id.view_content);

		arrListData=new ArrayList<String>();

		//给Item赋值
		arrListData.add("扫描歌曲");
		arrListData.add("重置密码");
		arrListData.add("退出");

		ImageButton btn1 = (ImageButton) findViewById(R.id.imageButton1);

		btn1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent1 = new Intent(MyMenuDrawer.this, GeQu.class);
				startActivity(intent1);
			}
		});

		imgBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (drawLayout.isDrawerVisible(Gravity.START)) {//START是左滑，END是右滑
					drawLayout.closeDrawer(Gravity.START);
				} else {
					drawLayout.openDrawer(Gravity.START);
				}

			}
		});

		myListAdapter = new MyListAdapter(arrListData, this, tvContent);


		lv.setAdapter(myListAdapter);
		//给lv设置一个点击事件，使之被点击时显示名字
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				if (arrListData.get(arg2).equals("扫描歌曲")) {
					Intent intent = new Intent(MyMenuDrawer.this, SaoMiaoActivity.class);
					startActivity(intent);
				}
				if (arrListData.get(arg2).equals("重置密码")) {
					Intent intent = new Intent(MyMenuDrawer.this, SetPasswordActivity.class);
					startActivity(intent);
				}
				if (arrListData.get(arg2).equals("退出")) {
					finish();
				}

			}
		});
		tvContent.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MyMenuDrawer.this, SaoMiaoActivity.class);
				startActivity(intent);
			}
		});

		super.onCreate(savedInstanceState);

		drawLayout.setDrawerListener(new DrawerListener() {
			/**
			 * 当抽屉滑动状态改变的时候被调用
			 * 状态值是STATE_IDLE（闲置--0）, STATE_DRAGGING（拖拽的--1）, STATE_SETTLING（固定--2）中之一。
			 * 抽屉打开的时候，点击抽屉，drawer的状态就会变成STATE_DRAGGING，然后变成STATE_IDLE
			 */
			@Override
			public void onDrawerStateChanged(int arg0) {
				// TODO Auto-generated method stub
				System.out.println("DrawerStateChanged and arg0 is:" + arg0);
			}

			/**
			 * 当抽屉被滑动的时候调用此方法
			 * arg1 表示 滑动的幅度（0-1）
			 */
			@Override
			public void onDrawerSlide(View arg0, float arg1) {
				// TODO Auto-generated method stub
				System.out.println("onDrawerSlide  arg1:" + arg1);

			}

			/**
			 * 当一个抽屉被完全打开的时候被调用
			 */
			@Override
			public void onDrawerOpened(View arg0) {
				// TODO Auto-generated method stub
				System.out.println("onDrawerOpened");

			}

			/**
			 * 当一个抽屉完全关闭的时候调用此方法
			 */
			@Override
			public void onDrawerClosed(View arg0) {
				System.out.println("arg0.getId() is:" + arg0.getId());
				// TODO Auto-generated method stub
				System.out.println("onDrawerClosed");

			}
		});
	}





}
