package com.example.zhaohw.gpsservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.zhaohw.gpsservice.util.ThreadPoolManager;

/**
 * @author zhaohw
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
	private static final String TAG = "MainActivity111";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initService();
		initView();
	}
	
	private void initService() {
		ThreadPoolManager.getInstance().addThreadTask(new Runnable() {
			@Override
			public void run() {
				// bind service
			}
		});
	}
	
	private void initView() {
		findViewById(R.id.bt_location_manager).setOnClickListener(this);
		findViewById(R.id.bt_lcoation_service).setOnClickListener(this);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.bt_location_manager: {
				LocationManagerActivity.start(this);
			}
			break;
			case R.id.bt_lcoation_service: {
				LocationServiceActivity.start(this);
			}
			break;
			default:
				break;
		}
	}
}
