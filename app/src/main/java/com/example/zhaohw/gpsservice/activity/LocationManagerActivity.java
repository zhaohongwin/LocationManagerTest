package com.example.zhaohw.gpsservice.activity;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.zhaohw.gpsservice.R;

/**
 * @author zhaohw
 */
public class LocationManagerActivity extends AppCompatActivity implements View.OnClickListener {
	private TextView tvLocation;
	private TextView tvGetLocation;
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
//		LocationUtils.getInstance().registerLocationListener(myLocationListener);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
//		LocationUtils.getInstance().unRegisterLocationListener(myLocationListener);
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
//		LocationUtils.getInstance().init(this, 1000, 0);
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

}
