package com.example.gpsservice.listener;

import com.example.gpsadapter.bean.FailMessageBean;
import com.example.gpsadapter.bean.LocationInfo;

/**
 * @author zhaohw
 * @date 2018/8/10
 */

public interface ILocationResult {
	void onSuccess(LocationInfo locationInfo);
	void onFailed(FailMessageBean msg);
}
