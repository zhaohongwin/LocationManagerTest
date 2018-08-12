package com.example.zhaohw.gpsservice;

import android.app.Application;
import android.content.Intent;

import com.example.gpsservice.GpsService;

/**
 *
 * @author zhaohw
 * @date 2018/8/9
 */

public class GpsApplication extends Application {
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		startService(new Intent(this, GpsService.class));
	}

}
