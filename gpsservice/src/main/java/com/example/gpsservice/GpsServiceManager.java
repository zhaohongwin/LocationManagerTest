package com.example.gpsservice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.example.gpsadapter.bean.FailMessageBean;
import com.example.gpsadapter.bean.LocationInfo;
import com.example.gpsservice.constant.Constants;
import com.example.gpsservice.listener.ILocationListener;
import com.example.gpsservice.listener.ILocationResult;
import com.example.gpsservice.util.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaohw
 * @date 2018/8/10
 */

public class GpsServiceManager {
	private Context mContext;
	private LocationManager mLocationManager;
	private String provider;
	private AndroidLocationListener androidLocationListener;
	private List<ILocationResult> iLocationResults = new ArrayList<>();
	private List<ILocationListener> iLocationListeners = new ArrayList<>();
	
	public GpsServiceManager() {
	
	}
	
	public void init(Context context) {
		this.mContext = context;
		androidLocationListener = new AndroidLocationListener();
		mLocationManager = (LocationManager)mContext.getSystemService(Context.LOCATION_SERVICE);
	}
	
	public void unInit() {
	
	}
	
	@SuppressLint("MissingPermission")
	public int requestLocationUpdates(long minTime, float minDistance) {
		if (mLocationManager != null) {
			mLocationManager.requestLocationUpdates(provider, minTime, minDistance, androidLocationListener);
			return Constants.Response.SUCCESS;
		} else {
			mLocationManager = (LocationManager)mContext.getSystemService(Context.LOCATION_SERVICE);
			return Constants.Response.FAIL;
		}
	}
	
	public void addLocationResultListener(ILocationResult locationResult) {
		if (!iLocationResults.contains(locationResult)) {
			iLocationResults.add(locationResult);
		}
	}
	
	public void removeLocationResultLitener(ILocationResult locationResult) {
		if (iLocationResults.contains(locationResult)) {
			iLocationResults.remove(locationResult);
		}
	}
	
	public void addLocationListener(ILocationListener locationListener) {
		if (!iLocationListeners.contains(locationListener)) {
			iLocationListeners.add(locationListener);
		}
	}
	
	@SuppressLint("MissingPermission")
	public void removeLocationListener(ILocationListener locationListener) {
		if (iLocationListeners.contains(locationListener)) {
			iLocationListeners.remove(locationListener);
		}
	}
	
	@SuppressLint("MissingPermission")
	private void getLocation() {
		Location location = null;
		
		if (mLocationManager == null) {
			mLocationManager = (LocationManager)mContext.getSystemService(Context.LOCATION_SERVICE);
		}
		
		if (mLocationManager != null) {
			List<String> providerList = mLocationManager.getProviders(true);
			if (providerList.contains(LocationManager.GPS_PROVIDER)) {
				provider = LocationManager.GPS_PROVIDER;
			} else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
				provider = LocationManager.NETWORK_PROVIDER;
			} else {
				provider = null;
				onFailedResults(new FailMessageBean(Constants.Response.FAIL, "provider is null"));
				return;
			}
			
			location = mLocationManager.getLastKnownLocation(provider);
			
			if (location != null) {
				onSuccessLocation(Utils.convert(location));
			}
		} else {
			onFailedResults(new FailMessageBean(Constants.Response.FAIL, "mLocationManager is null"));
		}
	}
	
	private void onFailedResults(FailMessageBean msg) {
		for (ILocationResult locationResult : iLocationResults) {
			locationResult.onFailed(msg);
		}
	}
	
	private void onSuccessLocation(LocationInfo locationInfo) {
		for (ILocationResult locationResult : iLocationResults) {
			locationResult.onSuccess(locationInfo);
		}
	}
	
	private class AndroidLocationListener implements LocationListener {
		
		@Override
		public void onLocationChanged(Location location) {
			for (ILocationListener iLocationListener : iLocationListeners) {
				iLocationListener.onLocationChanged(Utils.convert(location));
			}
		}
		
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			for (ILocationListener iLocationListener : iLocationListeners) {
				iLocationListener.onStatusChanged(provider, status, extras);
			}
		}
		
		@Override
		public void onProviderEnabled(String provider) {
			for (ILocationListener iLocationListener : iLocationListeners) {
				iLocationListener.onProviderEnabled(provider);
			}
		}
		
		@Override
		public void onProviderDisabled(String provider) {
			for (ILocationListener iLocationListener : iLocationListeners) {
				iLocationListener.onProviderDisabled(provider);
			}
		}
	}
}
