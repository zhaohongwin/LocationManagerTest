package com.example.zhaohw.gpsservice.activity;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.zhaohw.gpsservice.R;
import com.example.zhaohw.gpsservice.util.location.LocationUtils;

/**
 * @author zhaohw
 */
public class LocationManagerActivity extends AppCompatActivity implements View.OnClickListener {
	private TextView tvLocation;
	private TextView tvGetLocation;
	private MyLocationListener myLocationListener;
	private int count = 0;
	
	public static void start(Context context) {
		context.startActivity(new Intent(context, LocationManagerActivity.class));
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location_manager);
		
		initView();
		initLocationListener();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		LocationUtils.getInstance().registerLocationListener(myLocationListener);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		LocationUtils.getInstance().unRegisterLocationListener(myLocationListener);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	private void initView() {
		tvGetLocation = (TextView)findViewById(R.id.tv_get_location);
		tvLocation = (TextView)findViewById(R.id.tv_location_update);
		findViewById(R.id.button).setOnClickListener(this);
	}
	
	private void initLocationListener() {
		myLocationListener = new MyLocationListener();
		LocationUtils.getInstance().init(this, 1000, 0);
	}
	
	/**
	 * Called when a view has been clicked.
	 *
	 * @param v The view that was clicked.
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.button: {
			}
			break;
			default:
				break;
		}
	}
	
	private class MyLocationListener implements LocationUtils.MxLocationListener {
		
		/**
		 * On location changed.
		 *
		 * @param location the location
		 */
		@Override
		public void onLocationChanged(final Location location) {
			if (location != null) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						String strResult = "getAccuracy:" + location.getAccuracy() + "\r\n" + "getAltitude:" + location.getAltitude() + "\r\n" + "getBearing:" + location.getBearing() + "\r\n" + "getElapsedRealtimeNanos:" + String.valueOf(location.getElapsedRealtimeNanos()) + "\r\n" + "getLatitude:" + location.getLatitude() + "\r\n" + "getLongitude:" + location.getLongitude() + "\r\n" + "getProvider:" + location.getProvider() + "\r\n" + "getSpeed:" + location.getSpeed() + "\r\n" + "getTime:" + location.getTime() + "\r\n";
						if (tvLocation != null) {
							tvLocation.setText(strResult);
						}
					}
				});
				
			}
		}
		
		/**
		 * On status changed.
		 *
		 * @param provider the provider
		 * @param status   the status
		 * @param extras   the extras
		 */
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		
		}
		
		/**
		 * On provider enabled.
		 *
		 * @param provider the provider
		 */
		@Override
		public void onProviderEnabled(String provider) {
		
		}
		
		/**
		 * On provider disabled.
		 *
		 * @param provider the provider
		 */
		@Override
		public void onProviderDisabled(String provider) {
		
		}
	}
}
