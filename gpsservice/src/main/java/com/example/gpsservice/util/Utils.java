package com.example.gpsservice.util;

import android.annotation.SuppressLint;
import android.location.Location;

import com.example.gpsadapter.bean.LocationInfo;

/**
 *
 * @author zhaohw
 * @date 2018/8/10
 */

public class Utils {
	@SuppressLint("NewApi")
	public static LocationInfo convert(Location location) {
		if (location != null) {
			return new LocationInfo(location.getProvider(), location.getTime(), location.getElapsedRealtimeNanos(), location.getLatitude(), location.getLongitude(), location.getAltitude(), location.getSpeed(), location.getBearing(), location.getAccuracy(), location.getVerticalAccuracyMeters(), location.getSpeedAccuracyMetersPerSecond(), location.getBearingAccuracyDegrees());
		} else {
			return null;
		}
	}
}
