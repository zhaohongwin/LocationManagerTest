package com.example.gpsservice;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

/**
 * @author zhaohw
 * @date 2018/8/9
 */

@SuppressLint("Registered")
public class GpsService extends Service {
	private static final String TAG = "GpsService111";
	
	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		Log.d(TAG, "onBind: ");
		return null;
	}
	
	/**
	 * Called by the system when the service is first created.  Do not call this method directly.
	 */
	@SuppressLint("MissingPermission")
	@Override
	public void onCreate() {
	
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(TAG, "onStartCommand: ");
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();

	}
}
