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

	ArrayList<String> arrData;//list��������ݱ���
	Context mContext;
//	TextView contentTv;

	//���췽��
	public MyListAdapter(ArrayList<String> arrData, Context mContext,
			TextView tv) {
		super();
		this.arrData = arrData;
		this.mContext = mContext;
	}



	@Override
	public int getCount() {//����list�ܹ��ж��ٸ�Ԫ��
		// TODO Auto-generated method stub
		return arrData.size();
	}

	@Override
	public Object getItem(int position) {//����ڼ���Ԫ��
		// TODO Auto-generated method stub
		return arrData.get(position);
	}

	@Override
	public long getItemId(int position) {//����λ��
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	//����liat����ÿһ��listItem
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		ListItemsView listItemsView;
		if(convertView == null){//�������list��Ҫ�ж���û��item,���û�о�Ҫ����
			listItemsView = new ListItemsView();//����һ������ģ�ͣ��������ݱ任
			//����
			convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, null);
			//��ģ����layout�е�������Ӧ
			listItemsView.imView = (ImageView)convertView.findViewById(R.id.item_icon);
			listItemsView.tv = (TextView)convertView.findViewById(R.id.item_name);
			convertView.setTag(listItemsView);
		}
		else{//�����Ϊ�գ�ֱ�ӻ�ȡ�������ģ��
			listItemsView = (ListItemsView)convertView.getTag();
		}
		//��listItems���и�ֵ��ͼ�ꡢ���ƣ�
		listItemsView.imView.setBackgroundResource(R.drawable.yinyuetubiao);//ͬһ��ͼ��
		listItemsView.tv.setText(this.arrData.get(position));


		return convertView;
	}

}
