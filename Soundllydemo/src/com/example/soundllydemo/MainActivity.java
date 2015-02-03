package com.example.soundllydemo;

import com.example.soundllydemo.TestApplication;

import com.example.soundllydemo.R;

import com.soundlly.sdk.Soundlly;
import com.soundlly.sdk.SoundllyCore;
import com.soundlly.sdk.service.*;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";

	private Soundlly mSoundlly;

	protected Button mbtn_listen;
	protected WebView mwebv_resulturl;

	protected boolean mbListenBtnFlag = SndlydmConst.SNDLY_LISTEN_STOP;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setLayout();

		mSoundlly = SoundllyCore.getSoundlly();

		mSoundlly.setDeveoloperMode();
		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		Log.w(TAG, "mSoundlly.bindSoundllyService();");

		mSoundlly.bindSoundllyService();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

		Log.w(TAG, "mSoundlly.unbindSoundllyService();");

		setStopListen();

		mSoundlly.unbindSoundllyService();
		
	}

	private void setListenWatermark(boolean isListen) {

		mSoundlly.setListenWatermark(TestApplication.API_KEY, isListen);

	}

	private void setLayout() {

		mbtn_listen = (Button) findViewById(R.id.btn_listen);

		mwebv_resulturl = (WebView) findViewById(R.id.webv_resulturl);
		
		mbtn_listen.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mbListenBtnFlag == SndlydmConst.SNDLY_LISTEN_STOP) {
					setStartListen();
				} else {
					setStopListen();
				}
			}
		});
	}

	
	private void setStopListen() {
		mbListenBtnFlag = SndlydmConst.SNDLY_LISTEN_STOP;

		mSoundlly.stopListening(TestApplication.API_KEY);

		Toast.makeText(MainActivity.this,
				getResources().getString(R.string.stop_listen),
				Toast.LENGTH_LONG).show();

		mbtn_listen.setText(getResources().getText(R.string.stop_listen));

	}

	private void setStartListen() {
		mbListenBtnFlag = SndlydmConst.SNDLY_LISTEN_START;

		mSoundlly.startListening(TestApplication.API_KEY);

		Toast.makeText(MainActivity.this,
				getResources().getString(R.string.start_listen),
				Toast.LENGTH_LONG).show();

		mbtn_listen.setText(getResources().getText(R.string.start_listen));

	}

}
