package com.tz.ays.activity;



import com.tz.ays.common.BaseActivity;
import com.tz.ays.entity.request.RequesetCourierLogin;
import com.tz.ays.entity.response.AysResponse;
import com.tz.ays.entity.result.ResultUserLogin;
import com.tz.ays.utils.MyConstants;
import com.tz.ays.utils.MyLog;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SplashActivity extends BaseActivity implements OnClickListener {
    private RelativeLayout relative_main;

	/** Called when the activity is first created. */

	@Override
	public void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_splash);
		//开启动画
		
	}

	@Override
	public void initViews() {
		// TODO Auto-generated method stub
		relative_main=(RelativeLayout) findViewById(R.id.splash_main);
		startAnimation();
	}
	/**
	 * 开启视图动画
	 *   开启导航登入界面
	 */
	private void startAnimation() {
		// TODO Auto-generated method stub
		Animation animation=AnimationUtils.loadAnimation(this,R.anim.splash_alpha);
		relative_main.startAnimation(animation);
		animation.setAnimationListener(new AnimationListener() {
			
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			public void onAnimationEnd(Animation animation) {
				//动画结束
				startLoginActivity();
				
			}
		});
		
	}
	public void startLoginActivity(){
		Intent intent=new Intent();
		intent.setClass(this,LoginActivity.class);		
		startActivity(intent);
		finish();
		
	}

	@Override
	public void initListeners() {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		
	}

	public void onClick(View v) {
//		MyTestHttpTask task =new MyTestHttpTask();
//		RequesetCourierLogin login=new RequesetCourierLogin();
//		login.setUserName("water");
//		login.setPassword("123321");
//		task.execute(MyConstants.PATH,login);
		
		
	}
	private class MyTestHttpTask extends AsyncTask<Object, Void, AysResponse>{

		

	

		@Override
		protected AysResponse doInBackground(Object... params) {
			String url=(String) params[0];
			RequesetCourierLogin login =(RequesetCourierLogin) params[1];
			AysResponse resp = request.RequestByPost(url,login);
			
			return resp;
		}

//		@Override
//		protected void onPostExecute(AysResponse result) {
//			// TODO Auto-generated method stub
//			super.onPostExecute(result);
//			if(result.getException()==null){
//				String string =result.getString();
//				MyLog.d("content"+string);
//				Toast.makeText(SplashActivity.this,"content"+string,2000).show();
//				textView.setText(string);
//				
//			}
//		}
		
	}
}