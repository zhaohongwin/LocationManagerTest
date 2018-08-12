package com.example.zhaohw.gpsservice.location;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Mx location manager.
 */
public class MxLocationManager {
	private static final String TAG = "MxLocationManager111";
	private volatile static MxLocationManager mxLocationManager;
	private final long MIN_TIME = 1000;
	private final float MIN_DISTANCE = 10f;
	private Context mContext;
	private long mMinTime;
	private float mMinDistance;
	private LocationManager mLocationManager;
	private String provider;
	private AndroidLocationListener androidLocationListener;
	private List<MxLocationListener> mxLocationListeners = new ArrayList<>();
	
	private MxLocationManager() {
	
	}
	
	/**
	 * Gets instance.
	 *
	 * @return the instance
	 */
	public static MxLocationManager getInstance() {
		if (mxLocationManager == null) {
			synchronized (MxLocationManager.class) {
				mxLocationManager = new MxLocationManager();
			}
		}
		return mxLocationManager;
	}
	
	/**
	 * Init.
	 *
	 * @param context     the context
	 * @param minTime     the min time
	 * @param minDistance the min distance
	 */
	@SuppressLint("MissingPermission")
	public void init(Context context, long minTime, float minDistance) {
		this.mContext = context;
		this.mMinTime = minTime > 0 ? minTime : MIN_TIME;
		this.mMinDistance = minDistance >= 0 ? minDistance : MIN_DISTANCE;
		
		if (mLocationManager == null) {
			mLocationManager = (LocationManager)mContext.getSystemService(Context.LOCATION_SERVICE);
		}
		
		androidLocationListener = new AndroidLocationListener();
		
		//获取定位
		getCurLocation();
	}
	
	/**
	 * Gets cur location.
	 *
	 * @return the cur location
	 */
	@SuppressLint("MissingPermission")
	public Location getCurLocation() {
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
			}
			
			if (provider != null) {
				location = mLocationManager.getLastKnownLocation(provider);
			}
		}
		
		return location;
	}
	
	/**
	 * Android 源生 LocationListener
	 */
	private class AndroidLocationListener implements LocationListener {
		@Override
		public void onLocationChanged(Location location) {
			for (MxLocationListener locationListener : mxLocationListeners) {
				locationListener.onLocationChanged(location);
			}
		}
		
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			for (MxLocationListener locationListener : mxLocationListeners) {
				locationListener.onStatusChanged(provider, status, extras);
			}
		}
		
		@Override
		public void onProviderEnabled(String provider) {
			for (MxLocationListener locationListener : mxLocationListeners) {
				locationListener.onProviderEnabled(provider);
			}
		}
		
		@Override
		public void onProviderDisabled(String provider) {
			for (MxLocationListener locationListener : mxLocationListeners) {
				locationListener.onProviderDisabled(provider);
			}
		}
	}
	
	/**
	 * Register location listener.
	 *
	 * @param mxLocationListener the mx location listener
	 */
	@SuppressLint("MissingPermission")
	public void registerLocationListener(MxLocationListener mxLocationListener) {
		if (provider != null) {
			if (mxLocationListeners.size() <= 0) {
				mLocationManager.requestLocationUpdates(provider, mMinTime, mMinDistance, androidLocationListener);
			}
			
			if (!mxLocationListeners.contains(mxLocationListener)) {
				mxLocationListeners.add(mxLocationListener);
			}
		}
	}
	
	/**
	 * Un register location listener.
	 *
	 * @param mxLocationListener the mx location listener
	 */
	public void unRegisterLocationListener(MxLocationListener mxLocationListener) {
		if (mxLocationListeners.contains(mxLocationListener)) {
			mxLocationListeners.remove(mxLocationListener);
		}
		
		if (mxLocationListeners.size() <= 0) {
			mLocationManager.removeUpdates(androidLocationListener);
		}
	}
	
	/**
	 * The interface Mx location listener.
	 */
	public interface MxLocationListener {
		/**
		 * On location changed.
		 *
		 * @param location the location
		 */
		void onLocationChanged(Location location);
		
		/**
		 * On status changed.
		 *
		 * @param provider the provider
		 * @param status   the status
		 * @param extras   the extras
		 */
		void onStatusChanged(String provider, int status, Bundle extras);
		
		/**
		 * On provider enabled.
		 *
		 * @param provider the provider
		 */
		void onProviderEnabled(String provider);
		
		/**
		 * On provider disabled.
		 *
		 * @param provider the provider
		 */
		void onProviderDisabled(String provider);
	}
}
