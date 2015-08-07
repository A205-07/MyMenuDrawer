package com.meitwo.menudrawer;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Message;
import android.provider.MediaStore.Audio.Media;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class GeQu extends Activity implements OnClickListener {
	List<Object> musiclists = new ArrayList<Object>();
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	ImageView play_pause, stop, onplay, downplay,  exit;
	ActivityReceiver activityReceiver;
	public static final String CTL_ACTION = "org.crazyit.action.CTL_ACTION";
	public static final String UPDATE_ACTION = "org.crazyit.action.UPDATE_ACTION";
	Intent intentservice;
	// 定义音乐的播放状态 ，0X11 代表停止 ，0x12代表播放,0x13代表暂停
	int status = 0x11;
	// 定义音乐的播放状态 ，0X14 代表随机播放 ，0x15顺序播放,0x16代表单曲播放


	final int[] imageViews = {
			R.id.imageView1, R.id.imageView2, R.id.imageView3 ,
			R.id.imageView4, R.id.imageView5, R.id.imageView6 ,
			R.id.imageView7, R.id.imageView8, R.id.imageView9  };

	final int[] images = {
			R.drawable.sgdgdf, R.drawable.ff,  R.drawable.bg,
			R.drawable.wll, R.drawable.sef,  R.drawable.mainback,
			R.drawable.sliding_bg, R.drawable.main_bg,  R.drawable.beijing9 };


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);


		UIinit();
		logic();
		musicList();
		activityReceiver = new ActivityReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(UPDATE_ACTION);
		registerReceiver(activityReceiver, filter);
		intentservice = new Intent(this, MusicService.class);
		startService(intentservice);

		ImageButton btn2 = (ImageButton) findViewById(R.id.bianhuan);

		btn2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Random rng = new Random();
				List<Integer> generated = new ArrayList<Integer>();
				for (int i = 0; i < 9; i++) {
					while (true) {
						Integer next = rng.nextInt(9);
						if (!generated.contains(next)) {
							generated.add(next);
							ImageView iv = (ImageView) findViewById(imageViews[i]);
							iv.setImageResource(images[next]);
							break;
						}
					}
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void UIinit() {
		play_pause = (ImageView) this.findViewById(R.id.imageview1);
		//stop = (ImageView) this.findViewById(R.id.imageButton2);
		onplay = (ImageView) this.findViewById(R.id.imageview3);

		//exit = (ImageView) this.findViewById(R.id.imageButton4);
		downplay = (ImageView) this.findViewById(R.id.imageview4);
	}

	public void logic() {
		play_pause.setOnClickListener(this);
		//stop.setOnClickListener(this);
		onplay.setOnClickListener(this);
		downplay.setOnClickListener(this);

		//exit.setOnClickListener(this);
	}

	@Override
	public void onClick(View source) {
		Intent intent = new Intent(CTL_ACTION);
		switch (source.getId()) {
		case R.id.imageview1: {
			intent.putExtra("control", 1);
			break;
		}

		case R.id.imageview3: {
			intent.putExtra("control", 3);
			break;
		}
		case R.id.imageview4: {
			intent.putExtra("control", 4);
			break;
		}

		}
		sendBroadcast(intent);

	}

	
	public class ActivityReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// 获取Intent中的update消息，update代表播放状态
		 	int update = intent.getIntExtra("update", -1);
			switch (update) {
			case 0x11: {
				//play_pause.setTag("播放");
				status = 0x11;

				break;
			}

			// 控制系统进入播放状态
			case 0x12: {
				// 播放状态下设置使用按钮
				//play_pause.setTag("暂停");
				// 设置当前状态
				status = 0x12;
				play_pause.setImageDrawable(getResources().getDrawable(R.drawable.pause_button_default));
				break;
			}
			// 控制系统进入暂停状态
			case 0x13: {
				//play_pause.setTag("播放");
				status = 0x13;
				play_pause.setImageDrawable(getResources().getDrawable(R.drawable.play_button_default));
				break;
			}

			}
		}

	}

	/* 播放列表 */
	public void musicList() {
		// 取得指定位置的文件设置显示到播放列表
		String[] music = new String[] { Media._ID, Media.DISPLAY_NAME,
				Media.TITLE, Media.DURATION, Media.ARTIST, Media.DATA };
		Cursor cursor = getContentResolver().query(Media.EXTERNAL_CONTENT_URI,
				music, null, null, null);
		while (cursor.moveToNext()) {
			Music temp = new Music();

			temp.setTitle(cursor.getString(2));

			temp.setArtist(cursor.getString(4));
			temp.setData(cursor.getString(5));
			musiclists.add(temp);

			Map<String, Object> map = new HashMap<String, Object>();

				map.put("name", cursor.getString(1));
				map.put("artist", cursor.getString(4));

			list.add(map);
		}

		ListView listview = (ListView) findViewById(R.id.musics);
		SimpleAdapter adapter = new SimpleAdapter(this, list,
				R.layout.musicsshow, new String[] { "name", "artist" },
				new int[] { R.id.name, R.id.artist });
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int current, long id) {

				Intent intent=new Intent(CTL_ACTION);
				intent.putExtra("control", 5);
				intent.putExtra("current", current);
				sendBroadcast(intent);
			}
		});
	}
}