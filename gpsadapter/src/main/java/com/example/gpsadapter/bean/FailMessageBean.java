package com.example.gpsadapter.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 * @author zhaohw
 * @date 2018/8/10
 */

public class FailMessageBean implements Parcelable {
	private int errorCode;
	private String errorReason;
	
	public FailMessageBean() {
	}
	
	protected FailMessageBean(Parcel in) {
		errorCode = in.readInt();
		errorReason = in.readString();
	}
	
	public static final Creator<FailMessageBean> CREATOR = new Creator<FailMessageBean>() {
		@Override
		public FailMessageBean createFromParcel(Parcel in) {
			return new FailMessageBean(in);
		}
		
		@Override
		public FailMessageBean[] newArray(int size) {
			return new FailMessageBean[size];
		}
	};
	
	public int getErrorCode() {
		return errorCode;
	}
	
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getErrorReason() {
		return errorReason;
	}
	
	public void setErrorReason(String errorReason) {
		this.errorReason = errorReason;
	}
	
	public FailMessageBean(int errorCode, String errorReason) {
		this.errorCode = errorCode;
		this.errorReason = errorReason;
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
		
		dest.writeInt(errorCode);
		dest.writeString(errorReason);
	}
}
