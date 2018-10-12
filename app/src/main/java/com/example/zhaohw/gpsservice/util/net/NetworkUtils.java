package com.example.zhaohw.gpsservice.util.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaohw
 * @date 2018/10/11
 */

public class NetworkUtils {
	private final String TAG = "NetworkChangedManager";
	private Context mContext;
	private NetworkConnectChangedReceiver networkConnectChangedReceiver;
	private List<INetworkStateListener> networkConnectListeners = new ArrayList<>();
	private volatile NetType netType = NetType.TYPE_UNKNOWN;
	private volatile NetState netState = NetState.STATE_UNKNOWN;
	
	/**
	 * 网络状态
	 */
	public enum NetState {
		STATE_CONNECTING, STATE_CONNECTED, STATE_SUSPENDED, STATE_DISCONNECTING, STATE_DISCONNECTED, STATE_UNKNOWN
	}
	
	/**
	 * 网络类型
	 */
	public enum NetType {
		TYPE_WIFI, TYPE_MOBILE, TYPE_UNKNOWN
	}
	
	private NetworkUtils() {
	}
	
	public static NetworkUtils getInstance() {
		return SingletonHolder.INSTANCE;
	}
	
	private static class SingletonHolder {
		private static final NetworkUtils INSTANCE = new NetworkUtils();
	}
	
	public void init(Context context) {
		mContext = context;
		networkConnectChangedReceiver = new NetworkConnectChangedReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		mContext.registerReceiver(networkConnectChangedReceiver, filter);
	}
	
	public void unInit() {
		if (mContext != null && networkConnectChangedReceiver != null) {
			mContext.unregisterReceiver(networkConnectChangedReceiver);
		}
	}
	
	/**
	 * 当前是否有网络连接
	 *
	 * @return
	 */
	public synchronized boolean hasNetwork() {
		ConnectivityManager manager = (ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (manager == null) {
			return false;
		}
		
		NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
		return (activeNetwork != null && activeNetwork.isAvailable() && activeNetwork.isConnected());
	}
	
//	public synchronized boolean hasNetwork() {
//		return NetState.STATE_CONNECTED == netState;
//	}
	
	public void addNetworkConnectListener(INetworkStateListener listener) {
		if (listener != null && !networkConnectListeners.contains(listener)) {
			networkConnectListeners.add(listener);
		}
	}
	
	public void removeNetworkConnectListener(INetworkStateListener listener) {
		if (listener != null && networkConnectListeners.contains(listener)) {
			networkConnectListeners.remove(listener);
		}
	}
	
	private class NetworkConnectChangedReceiver extends BroadcastReceiver {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			// 监听网络连接，包括wifi和移动数据的打开和关闭,以及连接上可用的连接都会接到监听
			if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
				ConnectivityManager manager = (ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
				if (manager == null) {
					Log.i(TAG, "onReceive: manager == null");
					return;
				}
				
				NetworkInfo networkInfo = manager.getActiveNetworkInfo();
				
				if (networkInfo != null) {
					NetworkInfo.State state = networkInfo.getState();
					Log.i(TAG, "onReceive: state" + state);
					// 网络类型 wifi | mobile
					if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
						netType = NetType.TYPE_WIFI;
					} else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
						netType = NetType.TYPE_MOBILE;
					}
					
					// 网络状态 connected | disconnected
					if (NetworkInfo.State.CONNECTED == state && networkInfo.isAvailable()) {
						netState = NetState.STATE_CONNECTED;
						Log.i(TAG, netType + "已连接");
					} else {
						Log.i(TAG, netType + "已断开");
						netState = NetState.STATE_DISCONNECTED;
					}
				} else {
					Log.i(TAG, "已断开");
					netState = NetState.STATE_DISCONNECTED;
				}
				
				for (INetworkStateListener listener : networkConnectListeners) {
					listener.onNetworkState(netState, netType);
				}
			}
		}
	}
	
	/**
	 * 网络状态监听
	 */
	public interface INetworkStateListener {
		/**
		 * @param netState {@link NetState}
		 * @param type     {@link NetType}
		 */
		void onNetworkState(NetState netState, NetType type);
	}
}
