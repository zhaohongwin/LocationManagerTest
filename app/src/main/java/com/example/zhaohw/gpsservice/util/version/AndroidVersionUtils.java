package com.example.zhaohw.gpsservice.util.version;

import android.os.Build;

/**
 * 版本工具，用于判断当前版本是否大于某个版本
 */

public class AndroidVersionUtils {
	/**
	 * 是否在2.2版本及以上
	 *
	 * @return 是否在2.2版本及以上
	 */
	public static boolean isFroyo() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
	}
	
	/**
	 * 是否在2.3版本及以上
	 *
	 * @return 是否在2.3版本及以上
	 */
	public static boolean isGingerbread() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD;
	}
	
	/**
	 * 是否在2.3.3版本及以上
	 *
	 * @return 是否在2.3.3版本及以上
	 */
	public static boolean isGingerbreadMR1() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD_MR1;
	}
	
	/**
	 * 是否在3.0版本及以上
	 *
	 * @return 是否在3.0版本及以上
	 */
	public static boolean isHoneycomb() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
	}
	
	/**
	 * 是否在3.1版本及以上
	 *
	 * @return 是否在3.1版本及以上
	 */
	public static boolean isHoneycombMR1() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1;
	}
	
	/**
	 * 是否在4.0版本及以上
	 *
	 * @return 是否在4.0版本及以上
	 */
	public static boolean isIceCreamSandwich() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
	}
	
	/**
	 * 是否在4.0.3版本及以上
	 *
	 * @return 是否在4.0.3版本及以上
	 */
	public static boolean isIceCreamSandwichMR1() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1;
	}
	
	/**
	 * 是否在4.1版本及以上
	 *
	 * @return 是否在4.1版本及以上
	 */
	public static boolean isJellyBean() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
	}
	
	/**
	 * 是否在4.4.2版本及以上 奇巧巧克力
	 *
	 * @return 是否在4.4.2版本及以上
	 */
	public static boolean isKitkat() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
	}
	
	/**
	 * 是否在5.0.1版本及以上 棒棒糖
	 *
	 * @return 是否在5.0.1版本及以上
	 */
	public static boolean isLollipop() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
	}
	
	/**
	 * 是否在6.0版本及以上 棉花糖
	 *
	 * @return 是否在6.0版本及以上
	 */
	public static boolean isMarshmallow() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
	}
	
	/**
	 * 是否在7.0版本及以上  牛轧糖
	 *
	 * @return 是否在7.0版本及以上
	 */
	public static boolean isNougat() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N;
	}
	
	/**
	 * 是否在7.1.1版本及以上  牛轧糖
	 *
	 * @return 是否在7.1.1版本及以上
	 */
	public static boolean isNougatMR1() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1;
	}
	
	/**
	 * 是否在8.0版本及以上
	 *
	 * @return 是否在8.0版本及以上
	 */
	public static boolean isOreo() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O;
	}
}
