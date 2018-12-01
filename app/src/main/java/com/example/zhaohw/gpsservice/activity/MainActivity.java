package com.example.zhaohw.gpsservice.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.zhaohw.gpsservice.R;

/**
 * @author zhaohw
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
	private static final String TAG = "MainActivity111";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initView();
		initReceiver();
	}
	
	private void initReceiver() {
//		NetworkUtils.getInstance().init(this);
//		NetworkUtils.getInstance().addNetworkStateListener(new NetworkUtils.INetworkStateListener() {
//			@Override
//			public void onNetworkState(NetworkUtils.NetState netState, NetworkUtils.NetType type) {
//				Toast.makeText(MainActivity.this, type + (netState == NetworkUtils.NetState.STATE_CONNECTED ? "已连接" : "已断开"), Toast.LENGTH_SHORT).show();
//			}
//		});
	}
	
	private void initView() {
		findViewById(R.id.bt_location_manager).setOnClickListener(this);
		findViewById(R.id.bt_lcoation_service).setOnClickListener(this);
		findViewById(R.id.bt_net_judge).setOnClickListener(this);
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
			case R.id.bt_net_judge: {
//				Toast.makeText(this, NetworkUtils.getInstance().hasNetwork() ? "有网络" : "无网络", Toast.LENGTH_SHORT).show();
			}
			break;
			default:
				break;
		}
	}
}
