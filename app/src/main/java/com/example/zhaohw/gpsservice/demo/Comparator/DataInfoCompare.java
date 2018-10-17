package com.example.zhaohw.gpsservice.demo.Comparator;

import java.util.Comparator;

/**
 * Created by zhaohw on 2018/10/17.
 */

public class DataInfoCompare implements Comparator<DataInfo> {
	
	@Override
	public int compare(DataInfo o1, DataInfo o2) {
		if (o1 == null) {
			return 1;
		}
		
		if (o2 == null) {
			return -1;
		}
		
		int[] iO1 = new int[]{o1.getYear(), o1.getMonth(), o1.getDay()};
		int[] iO2 = new int[]{o2.getYear(), o2.getMonth(), o2.getDay()};
		
		if (iO1[0] == iO2[0]) {
			if (iO1[1] == iO2[1]) {
				return iO1[2] - iO2[2] == 0 ? 0 : (iO1[2] - iO2[2] > 0 ? 1 : -1);
			} else if (iO1[1] > iO2[1]) {
				return 1;
			} else {
				return -1;
			}
		} else if (iO1[0] < iO2[0]) {
			return -1;
		} else {
			return 1;
		}
	}
	
	// for test
//	    List<DataInfo> dataInfos = new ArrayList<>();
//		dataInfos.add(new DataInfo(2018, 4, 1));
//		dataInfos.add(new DataInfo(2018, 4, 3));
//		dataInfos.add(new DataInfo(2018, 4, 2));
//		dataInfos.add(new DataInfo(2018, 4, 6));
//		dataInfos.add(new DataInfo(2018, 4, 4));
//		dataInfos.add(new DataInfo(2018, 4, 7));
//
//		Collections.sort(dataInfos, new FutureWeatherComparator());
//
//		dataInfos.clear();
//		dataInfos.add(new DataInfo(2018, 7, 30));
//		dataInfos.add(new DataInfo(2018, 8, 2));
//		dataInfos.add(new DataInfo(2018, 7, 29));
//		dataInfos.add(new DataInfo(2018, 8, 1));
//		dataInfos.add(new DataInfo(2018, 7, 31));
//		dataInfos.add(new DataInfo(2018, 8, 3));
//
//		Collections.sort(dataInfos, new FutureWeatherComparator());
//
//		dataInfos.clear();
//		dataInfos.add(new DataInfo(2018, 1, 2));
//		dataInfos.add(new DataInfo(2017, 12, 30));
//		dataInfos.add(new DataInfo(2018, 1, 1));
//		dataInfos.add(new DataInfo(2017, 12, 31));
//		dataInfos.add(new DataInfo(2018, 1, 3));
//		dataInfos.add(new DataInfo(2017, 12, 29));
//
//		Collections.sort(dataInfos, new FutureWeatherComparator());
}
