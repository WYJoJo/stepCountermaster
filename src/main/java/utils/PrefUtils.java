package utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.cn.stepcounter.Myapplication;


public class PrefUtils {

	private static final String PREF_NAME = "sport";
	
//	private static PrefUtils instance;
	private static SharedPreferences mSharedPreferences;

//	public static PrefUtils getInstance() {
//		if (instance == null) {
//			instance = new PrefUtils(JysStoreApplication.application);
//		}
//		return instance;
//	}
	
	private static SharedPreferences getPreferences(){
		if (mSharedPreferences == null) {
			mSharedPreferences = Myapplication.getInstance().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
		}
		return mSharedPreferences;
	}

//	private PrefUtils(Context context) {
//		mSharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
//	}

	/**
	 * @param key
	 * @param bool
	 */
	public static void saveBoolean(String key, boolean bool) {
		getPreferences().edit().putBoolean(key, bool).commit();
	}

	/**
	 * @param key
	 * @return
	 */
	public static boolean getBoolean(String key) {
		return getPreferences().getBoolean(key, false);
	}
	
	/**
	 * @param key
	 * @return
	 */
	public static boolean getBoolean(String key,boolean defaul) {
		return getPreferences().getBoolean(key, defaul);
	}

	/**
	 * @param key
	 * @param value
	 */
	public static void saveString(String key, String value) {
		getPreferences().edit().putString(key, value).commit();
	}

	/**
	 * @param key
	 * @return
	 */
	public static String getString(String key) {
		return getPreferences().getString(key, "");
	}

	/**
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getString(String key, String defaultValue) {
		return getPreferences().getString(key, defaultValue);
	}

	/**
	 * @param key
	 * @param value
	 */
	public static void saveInt(String key, Integer value) {
		getPreferences().edit().putInt(key, value).commit();
	}

	/**
	 * @param key
	 * @return
	 */
	public static int getInt(String key) {
		return getPreferences().getInt(key, 0);
	}

	/**
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static int getInt(String key, int defaultValue) {
		return getPreferences().getInt(key, defaultValue);
	}

	/**
	 * @param key
	 * @param value
	 */
	public static void saveLong(String key, long value) {
		getPreferences().edit().putLong(key, value).commit();
	}

	/**
	 * @param key
	 * @return
	 */
	public static long getLong(String key) {
		return getPreferences().getLong(key, 0l);
	}

	/**
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static long getLong(String key, long defaultValue) {
		return getPreferences().getLong(key, defaultValue);
	}

	/**
	 * @param key
	 * @param value
	 */
	public static void saveFloat(String key, float value) {
		getPreferences().edit().putFloat(key, value).commit();
	}

	/**
	 * @param key
	 * @return
	 */
	public static Float getFloat(String key) {
		return getPreferences().getFloat(key, 0);
	}

	/**
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static Float getFloat(String key, float defaultValue) {
		return getPreferences().getFloat(key, defaultValue);
	}

//	public SharedPreferences getSharedPreferences() {
//		return mSharedPreferences;
//	}
	
	public static void removeTemp(String key){
		getPreferences().edit().remove(key).apply();
	}
	
	public static void remove(String key){
		getPreferences().edit().remove(key).commit();
	}

}
