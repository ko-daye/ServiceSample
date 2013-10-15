package com.example.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

	static final String TAG = "LocalService";

	@Override
	public void onCreate() {
		Log.i(TAG, "onCreate");
		Toast.makeText(this, "MyService#onCreate", Toast.LENGTH_SHORT).show();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i(TAG, "onStartCommand Received start id " + startId + ": "
				+ intent);
		Toast.makeText(this, "MyService#onStartCommand", Toast.LENGTH_SHORT)
				.show();
		// 明示的にサービスの起動、停止が決められる場合の返り値
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		Log.i(TAG, "onDestroy");
		Toast.makeText(this, "MyService#onDestroy", Toast.LENGTH_SHORT).show();
	}

	// onCreate , onStartCommand , onDestroy() はstartServiceと同じなので省略
	/*
	 * 以下はBind時に必要なコード
	 */

	// サービスに接続するためのBinder
	public class MyServiceLocalBinder extends Binder {
		// サービスの取得
		public MyService getService() {
			return MyService.this;
		}
	}
	
	public void runTimer() {
//		Thread th = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				
//				Toast.makeText(getContext(), "MyService:Running",Toast.LENGTH_SHORT).show();
//				try {
//					wait(1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//		});
//		
//		th.start();
		
		Toast.makeText(getContext(), "MyService:Running",Toast.LENGTH_SHORT).show();
	}

	// Binderの生成
	private final IBinder mBinder = new MyServiceLocalBinder();
	
	public Context getContext() {
		return this;
	}

	@Override
	public IBinder onBind(Intent intent) {
		Toast.makeText(this, "MyService#onBind" + ": " + intent,
				Toast.LENGTH_SHORT).show();
		Log.i(TAG, "onBind" + ": " + intent);
		return mBinder;
	}

	@Override
	public void onRebind(Intent intent) {
		Toast.makeText(this, "MyService#onRebind" + ": " + intent,
				Toast.LENGTH_SHORT).show();
		Log.i(TAG, "onRebind" + ": " + intent);
	}

	@Override
	public boolean onUnbind(Intent intent) {
		Toast.makeText(this, "MyService#onUnbind" + ": " + intent,
				Toast.LENGTH_SHORT).show();
		Log.i(TAG, "onUnbind" + ": " + intent);

		// onUnbindをreturn trueでoverrideすると次回バインド時にonRebildが呼ばれる
		return true;
	}
}