package com.zhaohw.comment.util.Asserts;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author zhaohw
 * @date 2018/10/17
 */

public class AssetsCopyUtils {
	private static final String TAG = "AssetsFileUtils";
	
	private AssetsCopyUtils() {
	}
	
	public static AssetsCopyUtils getInstance() {
		return SingletonHolder.INSTANCE;
	}
	
	private static class SingletonHolder {
		private static final AssetsCopyUtils INSTANCE = new AssetsCopyUtils();
	}
	
	/**
	 * %s is packageName
	 */
	private static String dataBasePath = "/data/data/%s/databases";
	
	private Context mContext;
	
	public void init(Context context) {
		mContext = context;
	}
	
	/**
	 * 是否需要拷贝dbFile
	 *
	 * @param dbFile
	 * @return
	 */
	public boolean isNeedCopyToDataBase(String dbFile) {
		boolean result = true;
		File file = new File(getDatabaseFile(dbFile));
		SharedPreferences dbs = mContext.getSharedPreferences(AssetsCopyUtils.class.toString(), 0);
		// Get Database file flag, if true means this database file was copied and valid
		boolean flag = dbs.getBoolean(dbFile, false);
		if (flag && file.exists()) {
			result = false;
		}
		return result;
	}
	
	/**
	 * db文件
	 *
	 * @param dbFile
	 * @return
	 */
	private String getDatabaseFile(String dbFile) {
		return getDatabaseFilepath() + File.separator + dbFile;
	}
	
	/**
	 * db文件存储路径
	 *
	 * @return
	 */
	private String getDatabaseFilepath() {
		return String.format(dataBasePath, mContext.getApplicationInfo().packageName);
	}
	
	/**
	 * 拷贝db文件到/data/data/%s/databases
	 *
	 * @param dbFile
	 */
	public void copyAssetsToDatabase(String dbFile) {
		String strPath = getDatabaseFilepath();
		String strFile = getDatabaseFile(dbFile);
		
		File file = new File(strFile);
		SharedPreferences dbs = mContext.getSharedPreferences(AssetsCopyUtils.class.toString(), 0);
		file = new File(strPath);
		if (!file.exists() && !file.mkdirs()) {
			Log.i(TAG, "Create \"" + strPath + "\" fail!");
			return;
		}
		if (!copyAssetsToFileSystem(dbFile, strFile)) {
			Log.i(TAG, String.format("Copy %s to %s fail!", dbFile, strFile));
			return;
		}
		dbs.edit().putBoolean(dbFile, true).apply();
	}
	
	/**
	 * 拷贝Assets中的"assetsSrc"文件到"des"
	 *
	 * @param assetsSrc
	 * @param des
	 * @return
	 */
	private boolean copyAssetsToFileSystem(String assetsSrc, String des) {
		Log.i(TAG, "Copy " + assetsSrc + " to " + des);
		InputStream iStream = null;
		OutputStream oStream = null;
		try {
			AssetManager am = mContext.getAssets();
			iStream = am.open(assetsSrc);
			oStream = new FileOutputStream(des);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = iStream.read(buffer)) > 0) {
				oStream.write(buffer, 0, length);
			}
			iStream.close();
			oStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if (iStream != null) {
					iStream.close();
				}
				if (oStream != null) {
					oStream.close();
				}
			} catch (Exception ee) {
				ee.printStackTrace();
			}
			return false;
		}
		return true;
	}
}
