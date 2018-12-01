package com.zhaohw.comment.util.location;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;


import com.zhaohw.comment.util.ThreadPoolManager;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Location function.
 * @author zhaohw
 */
public class LocationUtils {
	private static final String TAG = "MxLocationManager111";
	private final long MIN_TIME = 1000;
	private final float MIN_DISTANCE = 10f;
	private Context mContext;
	private long mMinTime;
	private float mMinDistance;
	private LocationManager mLocationManager;
	private String provider;
	private AndroidLocationListener androidLocationListener;
	private List<MxLocationListener> mxLocationListeners = new ArrayList<>();
	
	private LocationUtils() {
	
	}
	
	public static LocationUtils getInstance() {
		return LocationUtils.SingletonHolder.INSTANCE;
	}
	
	private static class SingletonHolder {
		private static final LocationUtils INSTANCE = new LocationUtils();
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
		getCurLocation(null);
		if (provider != null) {
			mLocationManager.requestLocationUpdates(provider, mMinTime, mMinDistance, androidLocationListener);
		}
	}
	
	/**
	 * Uninit.
	 */
	public void uninit() {
		if (androidLocationListener != null) {
			mLocationManager.removeUpdates(androidLocationListener);
		}
	}
	
	/**
	 * Register location listener.
	 *
	 * @param mxLocationListener the mx location listener
	 */
	@SuppressLint("MissingPermission")
	public void registerLocationListener(MxLocationListener mxLocationListener) {
		if (!mxLocationListeners.contains(mxLocationListener)) {
			mxLocationListeners.add(mxLocationListener);
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
	}
	
	/**
	 * Gets cur location.
	 *
	 * @param iCurrentLocation the current location
	 */
	@SuppressLint("MissingPermission")
	public synchronized void getCurLocation(final ICurrentLocation iCurrentLocation) {
		if (mLocationManager == null) {
			mLocationManager = (LocationManager)mContext.getSystemService(Context.LOCATION_SERVICE);
		}
		
		provider = getProvider();
		if (provider != null) {
			ThreadPoolManager.getInstance().addThreadTask(new Runnable() {
				@Override
				public void run() {
					iCurrentLocation.onCurrentLocation(mLocationManager.getLastKnownLocation(provider));
				}
			});
		}
	}
	
	private String getProvider() {
		if (mLocationManager == null) {
			return null;
		}
		
		List<String> providerList = mLocationManager.getProviders(true);
		if (providerList.contains(LocationManager.GPS_PROVIDER)) {
			provider = LocationManager.GPS_PROVIDER;
		} else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
			provider = LocationManager.NETWORK_PROVIDER;
		} else {
			provider = null;
		}
		return provider;
	}

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

/**
 * The interface Current location.
 */
public interface ICurrentLocation {
	/**
	 * On current location.
	 *
	 * @param location the location
	 */
	void onCurrentLocation(Location location);
}
}
