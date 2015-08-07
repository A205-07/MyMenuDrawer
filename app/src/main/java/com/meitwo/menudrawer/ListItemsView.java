package com.meitwo.menudrawer;

import android.widget.ImageView;
import android.widget.TextView;
/*
//一个model，跟Itemlist对应的数据模型
*/
public class ListItemsView {
	ImageView imView;
	TextView tv;
	public ImageView getImView() {
		return imView;
	}
	public void setImView(ImageView imView) {
		this.imView = imView;
	}
	public TextView getTv() {
		return tv;
	}
	public void setTv(TextView tv) {
		this.tv = tv;
	}
	
}
