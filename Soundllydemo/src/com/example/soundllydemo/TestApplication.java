package com.example.soundllydemo;

import com.soundlly.sdk.SoundllyCore;

import android.app.Application;

public class TestApplication extends Application {

	public static final String API_KEY = "54c5c5b4-0a9205df-da905901-d05fifb8"; // 117
//	public static final String API_KEY = "54cfbe8f-0a9205df-735a9d8c-406A99F9"; // geminihaha 1 ~ 3
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		SoundllyCore.init(this);

		super.onCreate();

	}
}
