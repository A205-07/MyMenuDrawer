package com.meitwo.menudrawer;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyListAdapter extends BaseAdapter {

	ArrayList<String> arrData;//list里面的内容变量
	Context mContext;
//	TextView contentTv;

	//构造方法
	public MyListAdapter(ArrayList<String> arrData, Context mContext,
			TextView tv) {
		super();
		this.arrData = arrData;
		this.mContext = mContext;
	}



	@Override
	public int getCount() {//代表list总共有多少个元素
		// TODO Auto-generated method stub
		return arrData.size();
	}

	@Override
	public Object getItem(int position) {//代表第几个元素
		// TODO Auto-generated method stub
		return arrData.get(position);
	}

	@Override
	public long getItemId(int position) {//代表位置
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	//代表liat当中每一个listItem
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		ListItemsView listItemsView;
		if(convertView == null){//如果滑动list后，要判断有没有item,如果没有就要创建
			listItemsView = new ListItemsView();//定义一个数据模型，便于数据变换
			//构造
			convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, null);
			//让模型与layout中的组件相对应
			listItemsView.imView = (ImageView)convertView.findViewById(R.id.item_icon);
			listItemsView.tv = (TextView)convertView.findViewById(R.id.item_name);
			convertView.setTag(listItemsView);
		}
		else{//如果不为空，直接获取这个数据模型
			listItemsView = (ListItemsView)convertView.getTag();
		}
		//对listItems进行赋值（图标、名称）
		listItemsView.imView.setBackgroundResource(R.drawable.yinyuetubiao);//同一个图标
		listItemsView.tv.setText(this.arrData.get(position));


		return convertView;
	}

}
