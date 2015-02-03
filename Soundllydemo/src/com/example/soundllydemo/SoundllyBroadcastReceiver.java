package com.example.soundllydemo;

import java.util.ArrayList;

import com.example.soundllydemo.TestApplication;

import com.soundlly.sdk.Soundlly;
import com.soundlly.sdk.SoundllyCore;
import com.soundlly.sdk.net.model.AttributesModel;
import com.soundlly.sdk.net.model.ContentsModel;
import com.soundlly.sdk.service.SoundllyService;

import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

public class SoundllyBroadcastReceiver extends BroadcastReceiver {

	private static final String TAG = "SoundllyBroadcastReceiver";

	private String url = "";
	private String comment = "";
	private String code = "";

	private Context mContext = null;

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		mContext = context;

		if ((context.getPackageName() + SoundllyService.ACTION_RESULT)
				.equals(intent.getAction())) {

			Log.w(TAG, "SoundllyService.ACTION_RESULT");

			handleSoundllyResult(intent);

		} else if ((context.getPackageName() + SoundllyService.ACTION_ON_BIND)
				.equals(intent.getAction())) {

			Log.w(TAG, "SoundllyService.ACTION_ON_BIND");

			Soundlly sm = SoundllyCore.getSoundlly();

			if (sm != null) {
				sm.setApplicationId(TestApplication.API_KEY);
			}

		} else {
			// nothing to do
		}

	}

	private void handleSoundllyResult(Intent intent) {
		switch (intent.getExtras().getInt(Soundlly.EXTRA_STATUS_CODE)) {
		case Soundlly.CODE_OK:
			doLoadContents(intent);
			break;
		case Soundlly.CODE_NO_CONTENTS:
			// handling code for result
			break;
		// case Soundlly.CODE_NOT_FOUND:
		// handling code for result
		// break;
		case Soundlly.CODE_TIME_OUT:
			// handling code for result
			break;
		case Soundlly.CODE_UNAUTHORIZED:
			// handling code for result
			break;
		case Soundlly.CODE_UNKNOWN_ERROR:
			// handling code for result
			break;
		case Soundlly.CODE_NO_WATERMARK:
			// handling code for result
			break;
		case Soundlly.CODE_MIC_ERROR:
			// handling code for result
			break;
		default:
			break;
		}
	}

	private void doLoadContents(Intent intent) {

		ContentsModel contents = intent
				.getParcelableExtra(Soundlly.EXTRA_CONTENTS);
		ArrayList<AttributesModel> attributes = contents.getAttributes();

		if (attributes != null) {

			for (AttributesModel model : attributes) {

				if (model.getType().equals("string")) {

					if (model.getKey().equals("url")) {
						url = model.getValue();

					} else {
						comment = model.getValue();
					}
				} else if (model.getType().equals("integer")) {
					if (model.getKey().equals("code")) {
						code = model.getValue();
					}
				}
			}

			Intent webintent = new Intent(mContext, ResultWebActivity.class);
			webintent.putExtra(SndlydmConst.INTENT_EXTRA_KEY.URL, url);
			webintent.putExtra(SndlydmConst.INTENT_EXTRA_KEY.COMMENT, comment);
			webintent.putExtra(SndlydmConst.INTENT_EXTRA_KEY.CODE, code);
			
			if (mContext != null) {
				PendingIntent pi = PendingIntent.getActivity(mContext,
						SndlydmConst.INTENT_REQCODE_STARTACTIVITY, webintent,
						PendingIntent.FLAG_ONE_SHOT);
				try {
					pi.send();
				} catch (CanceledException e) {
					e.printStackTrace();
				}
			}

		}
	}

}
