package com.zhaohw.comment.util.location;

import android.content.Context;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;

@RequiresApi(api = Build.VERSION_CODES.N)
public class GPSProvider implements LocationListener, GpsStatus.Listener {
	
	public GPSProvider(Context context) {
		
		mLocationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
		
		try {
			mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
		} catch (IllegalArgumentException ex) {
			Log.e(TAG, "could not request location update: " + ex);
			return;
		} catch (SecurityException ex) {
			Log.e(TAG, "not allowed to request location update: " + ex);
			return;
		}
		
		try {
			mLocationManager.addGpsStatusListener(this);
		} catch (SecurityException ex) {
			Log.e(TAG, "not allowed to add gps status listener: " + ex);
		}
	}
	
	public void shutdown() {
		mLocationManager.removeUpdates(this);
		mLocationManager.removeGpsStatusListener(this);
	}
	
	@Override
	public void onLocationChanged(Location location) {

//		Native.updateGPSLocationInfo(location.getLongitude(),
//									 location.getLatitude(),
//									 location.getTime(),
//									 location.getAltitude(),
//									 location.getBearing(),
//									 location.getSpeed(),
//									 location.getAccuracy(),false);
	}
	
	@Override
	public void onProviderDisabled(String provider) {
	}
	
	@Override
	public void onProviderEnabled(String provider) {
	}
	
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}
	
	@Override
	public void onGpsStatusChanged(int event) {
		
		if (event == GpsStatus.GPS_EVENT_SATELLITE_STATUS) {
			
			mGpsStatus = mLocationManager.getGpsStatus(mGpsStatus);
			
			int num_svs = 0;
			
			for (GpsSatellite sv : mGpsStatus.getSatellites()) {
				
				mElevations[num_svs] = sv.getElevation();
				mAzimuths[num_svs] = sv.getAzimuth();
				mPrns[num_svs] = sv.getPrn();
				mSnrs[num_svs] = sv.getSnr();
				
				if (++num_svs >= MAX_SVS) break;
			}

//			Native.updateGPSSatelliteInfo(num_svs, mElevations, mAzimuths, mPrns, mSnrs);
		}
		
	}
	
	private static final String TAG = "GPSProvider";
	private static final int MAX_SVS = 32;
	
	private LocationManager mLocationManager;
	private GpsStatus mGpsStatus;
	
	private float[] mElevations = new float[MAX_SVS];
	private float[] mAzimuths = new float[MAX_SVS];
	private int[] mPrns = new int[MAX_SVS];
	private float[] mSnrs = new float[MAX_SVS];
	
}
