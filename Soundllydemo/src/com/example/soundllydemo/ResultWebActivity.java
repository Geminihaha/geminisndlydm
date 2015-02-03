package com.example.soundllydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebBackForwardList;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

public class ResultWebActivity extends Activity {

	private static final String TAG = "ResultWebActivity";
	
	public WebView mwebv_resulturl;

	public TextView mtxtv_comment;
	public TextView mtxtv_code;

	public Intent mGetIntent;
	
	
	public String mStrGetUrl = "";
	public String mStrGetComment = "";
	public String mStrGetCode = "";
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_resultweb);
		// TODO Auto-generated method stub

		mGetIntent = getIntent();

		mStrGetUrl 		= mGetIntent.getExtras().getString(SndlydmConst.INTENT_EXTRA_KEY.URL);
		mStrGetComment 	= mGetIntent.getExtras().getString(SndlydmConst.INTENT_EXTRA_KEY.COMMENT);
		mStrGetCode 	= mGetIntent.getExtras().getString(SndlydmConst.INTENT_EXTRA_KEY.CODE);

		setLayout();

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub

		if (mStrGetComment != null && mStrGetComment.length() > 0) {
			mtxtv_comment.setText(mStrGetComment);
		}

		if (mStrGetCode != null && mStrGetCode.length() > 0) {
			mtxtv_code.setText(mStrGetCode);
		}

		if (mStrGetUrl != null && mStrGetUrl.length() > 0) {
			mwebv_resulturl.loadUrl(mStrGetUrl);
		}

		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	private void setLayout() {

		mtxtv_comment = (TextView) findViewById(R.id.txtv_commant);
		mtxtv_code = (TextView) findViewById(R.id.txtv_code);

		mwebv_resulturl = (WebView) findViewById(R.id.webv_resulturl);
		mwebv_resulturl.getSettings().setJavaScriptEnabled(true);
		mwebv_resulturl.setWebViewClient(new WebViewClientClass()); 

	}
	
    private class WebViewClientClass extends WebViewClient { 
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) { 
            view.loadUrl(url); 
            return true; 
        } 
    }
}
