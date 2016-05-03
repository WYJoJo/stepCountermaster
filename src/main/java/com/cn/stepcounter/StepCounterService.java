package com.cn.stepcounter;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

import com.cn.stepcounter.Fragment.StepFragment;

import org.xutils.DbManager;

import constans.UserData;
import db.Db;
import db.User;
import utils.DataUtils;
import utils.PrefUtils;


//service负责后台的需要长期运行的任务
// 计步器服务
// 运行在后台的服务程序，完成了界面部分的开发后
// 就可以开发后台的服务类StepService
// 注册或注销传感器监听器，在手机屏幕状态栏显示通知，与StepActivity进行通信，走过的步数记到哪里了？？？
public class StepCounterService extends Service {

	public static Boolean FLAG = true;// 服务运行标志

	private SensorManager mSensorManager;// 传感器服务
	private StepDetector detector;// 传感器监听对象

	private PowerManager mPowerManager;// 电源管理服务
	private WakeLock mWakeLock;// 屏幕灯

	private int total_step = 0;
	private float distance = 0.0f;
	private float calories = 0.0f;
	private int step_length = 50;

	private UserData userData;
	private User user;
	private DbManager db;

	private String date;
	private float cal_c;
	private boolean isPlay = false;
	private float area;

	private Thread thread;
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			countStep();
			countDistance();
			countCalories();
			userData.setTotal_step(total_step);
			userData.setCalories(calories);
			userData.setDistance(distance);
			Intent intent = new Intent("step_receiver");
			intent.putExtra("total_step", total_step);
			intent.putExtra("distance",distance);
			intent.putExtra("calories",calories);
			sendBroadcast(intent);
			if (date.substring(11,date.length()).equals("23:59:59")){
				try {
					cal_c = calories + userData.getCal_c();
					userData.setCal_c(cal_c);
					user.setId(001);
					user.setDate(date);
					user.setStep(total_step);
					user.setCalories(calories);
					user.setDistance(distance);
					user.setHead("");
					user.setHeight(userData.getHeight());
					user.setType(userData.getTag());
					user.setWeight(userData.getWeight());
					db.save(user);
					StepDetector.CURRENT_SETP = 0;
				}catch (Throwable e){
					e.printStackTrace();
				}
			}
			}

	};

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();

		FLAG = true;// 标记为服务正在运行

		// 创建监听器类，实例化监听对象
		detector = new StepDetector(this);

		// 获取传感器的服务，初始化传感器
		mSensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
		// 注册传感器，注册监听器
		mSensorManager.registerListener(detector,
				mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_FASTEST);

		// 电源管理服务
		mPowerManager = (PowerManager) this
				.getSystemService(Context.POWER_SERVICE);
		mWakeLock = mPowerManager.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK
				| PowerManager.ACQUIRE_CAUSES_WAKEUP, "S");
		mWakeLock.acquire();

		userData = UserData.getInstance();

		user = new User();

		db = Db.getInstance();

		startCountStep();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		play();
		return START_NOT_STICKY;
	}
	private void play(){
		if (!isPlay){
			isPlay = true;
			Intent intent = new Intent(this,StepFragment.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
			PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);
			Notification notification = new Notification.Builder(this).setContentTitle("step").setContentIntent(pendingIntent).build();
			startForeground(12,notification);
		}
	}

	private void startCountStep(){
		if (thread == null) {

			thread = new Thread() {// ???????????????????

				@Override
				public void run() {
					// TODO Auto-generated method stub
					super.run();
					int temp = 0;
					while (true) {
						try {
							Thread.sleep(1000);
							date = DataUtils.getDateNow();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (FLAG) {
							Message msg = new Message();
							if (temp != StepDetector.CURRENT_SETP) {
								temp = StepDetector.CURRENT_SETP;
							}
							handler.sendMessage(msg);// ???????
						}
					}
				}
			};
			thread.start();
		}
		handler.removeCallbacks(thread);
		countStep();
		countDistance();
		countCalories();
	}
	//计算步数
	private void countStep() {
		if (StepDetector.CURRENT_SETP % 2 == 0) {
			total_step = StepDetector.CURRENT_SETP;
		} else {
			total_step = StepDetector.CURRENT_SETP +1;
		}
		total_step = StepDetector.CURRENT_SETP;
	}

	//计算步行距离
	private void countDistance() {
		if (total_step % 2 == 0) {
			distance = (total_step / 2) * 3 * step_length * 0.01f;
		} else {
			distance = ((total_step / 2) * 3 + 1) * step_length * 0.01f;
		}
		distance = (float)(Math.round(distance * 100) * 0.01d);
	}

	//计算热量
	private void countCalories(){
		getArea();
		calories = 0.05f * total_step + 2213.09f * area - 1993.57f;
		if (userData.getType() == 0){
			//跑步消耗
		}else if (userData.getType() == 1){
			calories = calories * 0.178f;
		}else {
			calories = total_step * 0.176f;
		}
		calories = (float)(Math.round(calories * 100) * 0.01d);
	}

	private void getArea(){
		area = 0.0061f * userData.getHeight() + 0.0128f * userData.getWeight() - 0.1529f;
		area = area * 0.0001f;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
//		stopForeground(true);
		/*FLAG = false;// 服务停止
		if (detector != null) {
			mSensorManager.unregisterListener(detector);
		}

		if (mWakeLock != null) {
			mWakeLock.release();
		}*/
	}

}
