package com.example.zhaohw.gpsservice.activity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.zhaohw.gpsservice.R;
import com.example.zhaohw.gpsservice.util.ThreadPoolManager;

public class LocationServiceActivity extends AppCompatActivity {
	TextView tvLocation;
	TextView tvGetLocation;
	LocationReceiver locationReceiver;
	IntentFilter filter;
	
	public static void start(Context context) {
		context.startActivity(new Intent(context, LocationServiceActivity.class));
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location_service);
		
		locationReceiver = new LocationReceiver();
		filter = new IntentFilter();
		filter.addAction("android.intent.action.GpsService");
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		ThreadPoolManager.getInstance().addThreadTask(new Runnable() {
			@Override
			public void run() {
				registerReceiver(locationReceiver, filter);
			}
		});
	}
	
	@Override
	protected void onPause() {
		ThreadPoolManager.getInstance().addThreadTask(new Runnable() {
			@Override
			public void run() {
				unregisterReceiver(locationReceiver);
			}
		});
		super.onPause();
	}
	
	@SuppressLint("HandlerLeak")
	Handler handler = new Handler() {
		/**
		 * Subclasses must implement this to receive messages.
		 *
		 * @param msg
		 */
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			
			if (msg.what == 1) {
				String tmp = "";
				Bundle bundle = msg.getData();
				if (bundle != null) {
					tmp = "latitude:" + bundle.getDouble("latitude") + "\r\n" + "longitude:" + bundle.getDouble("longitude") + "\r\n" + "provider:" + bundle.getString("provider");
				}
				tvLocation.setText(tmp);
			}
		}
	};
	
	public class LocationReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			Bundle bundle = intent.getExtras();
			
			
			Message msg = handler.obtainMessage();
			Bundle b = new Bundle();
			b.putDouble("latitude", bundle.getDouble("latitude"));
			b.putDouble("longitude", bundle.getDouble("longitude"));
			b.putString("provider", bundle.getString("provider"));
			msg.setData(b);
			msg.what = 1;
			handler.sendMessage(msg);
		}
	}
}
