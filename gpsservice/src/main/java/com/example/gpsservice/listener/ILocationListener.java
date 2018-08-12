package com.example.gpsservice.listener;

import android.os.Bundle;

import com.example.gpsadapter.bean.LocationInfo;

/**
 *
 * @author zhaohw
 * @date 2018/8/10
 */

public interface ILocationListener {
	void onLocationChanged(LocationInfo location);
	void onStatusChanged(String provider, int status, Bundle extras);
	void onProviderEnabled(String provider);
	void onProviderDisabled(String provider);
}
