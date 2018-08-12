package com.example.gpsadapter.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 * @author zhaohw
 * @date 2018/8/10
 */

public class LocationInfo implements Parcelable {
	private String mProvider;
	private long mTime = 0;
	private long mElapsedRealtimeNanos = 0;
	private double mLatitude = 0.0;
	private double mLongitude = 0.0;
	private double mAltitude = 0.0f;
	private float mSpeed = 0.0f;
	private float mBearing = 0.0f;
	private float mHorizontalAccuracyMeters = 0.0f;
	private float mVerticalAccuracyMeters = 0.0f;
	private float mSpeedAccuracyMetersPerSecond = 0.0f;
	private float mBearingAccuracyDegrees = 0.0f;
	
	public LocationInfo() {
	}
	
	public LocationInfo(String mProvider, long mTime, long mElapsedRealtimeNanos, double mLatitude, double mLongitude, double mAltitude, float mSpeed, float mBearing, float mHorizontalAccuracyMeters, float mVerticalAccuracyMeters, float mSpeedAccuracyMetersPerSecond, float mBearingAccuracyDegrees) {
		this.mProvider = mProvider;
		this.mTime = mTime;
		this.mElapsedRealtimeNanos = mElapsedRealtimeNanos;
		this.mLatitude = mLatitude;
		this.mLongitude = mLongitude;
		this.mAltitude = mAltitude;
		this.mSpeed = mSpeed;
		this.mBearing = mBearing;
		this.mHorizontalAccuracyMeters = mHorizontalAccuracyMeters;
		this.mVerticalAccuracyMeters = mVerticalAccuracyMeters;
		this.mSpeedAccuracyMetersPerSecond = mSpeedAccuracyMetersPerSecond;
		this.mBearingAccuracyDegrees = mBearingAccuracyDegrees;
	}
	
	private LocationInfo(Parcel in) {
		mProvider = in.readString();
		mTime = in.readLong();
		mElapsedRealtimeNanos = in.readLong();
		mLatitude = in.readDouble();
		mLongitude = in.readDouble();
		mAltitude = in.readDouble();
		mSpeed = in.readFloat();
		mBearing = in.readFloat();
		mHorizontalAccuracyMeters = in.readFloat();
		mVerticalAccuracyMeters = in.readFloat();
		mSpeedAccuracyMetersPerSecond = in.readFloat();
		mBearingAccuracyDegrees = in.readFloat();
	}
	
	public static final Creator<LocationInfo> CREATOR = new Creator<LocationInfo>() {
		@Override
		public LocationInfo createFromParcel(Parcel in) {
			return new LocationInfo(in);
		}
		
		@Override
		public LocationInfo[] newArray(int size) {
			return new LocationInfo[size];
		}
	};
	
	public String getProvider() {
		return mProvider;
	}
	
	public void setProvider(String mProvider) {
		this.mProvider = mProvider;
	}
	
	public long getTime() {
		return mTime;
	}
	
	public void setTime(long mTime) {
		this.mTime = mTime;
	}
	
	public long getElapsedRealtimeNanos() {
		return mElapsedRealtimeNanos;
	}
	
	public void setElapsedRealtimeNanos(long mElapsedRealtimeNanos) {
		this.mElapsedRealtimeNanos = mElapsedRealtimeNanos;
	}
	
	public double getLatitude() {
		return mLatitude;
	}
	
	public void setLatitude(double mLatitude) {
		this.mLatitude = mLatitude;
	}
	
	public double getLongitude() {
		return mLongitude;
	}
	
	public void setLongitude(double mLongitude) {
		this.mLongitude = mLongitude;
	}
	
	public double getAltitude() {
		return mAltitude;
	}
	
	public void setAltitude(double mAltitude) {
		this.mAltitude = mAltitude;
	}
	
	public float getSpeed() {
		return mSpeed;
	}
	
	public void setSpeed(float mSpeed) {
		this.mSpeed = mSpeed;
	}
	
	public float getBearing() {
		return mBearing;
	}
	
	public void setBearing(float mBearing) {
		this.mBearing = mBearing;
	}
	
	public float getHorizontalAccuracyMeters() {
		return mHorizontalAccuracyMeters;
	}
	
	public void setHorizontalAccuracyMeters(float mHorizontalAccuracyMeters) {
		this.mHorizontalAccuracyMeters = mHorizontalAccuracyMeters;
	}
	
	public float getVerticalAccuracyMeters() {
		return mVerticalAccuracyMeters;
	}
	
	public void setVerticalAccuracyMeters(float mVerticalAccuracyMeters) {
		this.mVerticalAccuracyMeters = mVerticalAccuracyMeters;
	}
	
	public float getSpeedAccuracyMetersPerSecond() {
		return mSpeedAccuracyMetersPerSecond;
	}
	
	public void setSpeedAccuracyMetersPerSecond(float mSpeedAccuracyMetersPerSecond) {
		this.mSpeedAccuracyMetersPerSecond = mSpeedAccuracyMetersPerSecond;
	}
	
	public float getBearingAccuracyDegrees() {
		return mBearingAccuracyDegrees;
	}
	
	public void setBearingAccuracyDegrees(float mBearingAccuracyDegrees) {
		this.mBearingAccuracyDegrees = mBearingAccuracyDegrees;
	}
	
	/**
	 * Describe the kinds of special objects contained in this Parcelable
	 * instance's marshaled representation. For example, if the object will
	 * include a file descriptor in the output of {@link #writeToParcel(Parcel, int)},
	 * the return value of this method must include the
	 * {@link #CONTENTS_FILE_DESCRIPTOR} bit.
	 *
	 * @return a bitmask indicating the set of special object types marshaled
	 * by this Parcelable object instance.
	 */
	@Override
	public int describeContents() {
		return 0;
	}
	
	/**
	 * Flatten this object in to a Parcel.
	 *
	 * @param dest  The Parcel in which the object should be written.
	 * @param flags Additional flags about how the object should be written.
	 *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
	 */
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		
		dest.writeString(mProvider);
		dest.writeLong(mTime);
		dest.writeLong(mElapsedRealtimeNanos);
		dest.writeDouble(mLatitude);
		dest.writeDouble(mLongitude);
		dest.writeDouble(mAltitude);
		dest.writeFloat(mSpeed);
		dest.writeFloat(mBearing);
		dest.writeFloat(mHorizontalAccuracyMeters);
		dest.writeFloat(mVerticalAccuracyMeters);
		dest.writeFloat(mSpeedAccuracyMetersPerSecond);
		dest.writeFloat(mBearingAccuracyDegrees);
	}
}
