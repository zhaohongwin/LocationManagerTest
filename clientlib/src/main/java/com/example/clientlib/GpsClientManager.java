package com.example.clientlib;

import android.content.Context;

/**
 *
 * @author zhaohw
 * @date 2018/8/10
 */

public class GpsClientManager {
	private static final String TAG = "GpsClientManager11";
	private static GpsClientManager instance;
	private Context mContext;
	
	public static GpsClientManager getInstance() {
		if (instance == null) {
			synchronized (GpsClientManager.class) {
				instance = new GpsClientManager();
			}
		}
		return instance;
	}
	
	public void init(Context context) {
		this.mContext = context;
		bindService();
	}
	
	private void bindService() {
	
	}
}
