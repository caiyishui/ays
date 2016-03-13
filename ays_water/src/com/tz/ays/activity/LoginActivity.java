package com.tz.ays.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.tz.ays.common.BaseActivity;
import com.tz.ays.entity.request.RequesetCourierLogin;
import com.tz.ays.entity.response.AysResponse;
import com.tz.ays.entity.result.ResultUserLogin;
import com.tz.ays.utils.MD5;
import com.tz.ays.utils.MyConstants;

public class LoginActivity extends BaseActivity{
private EditText etLoginName;
private EditText etLoginPwd;
private ProgressDialog dialog;

	@Override
	public void setContentView() {
		setContentView(R.layout.activity_login);
		
	}

	@Override
	public void initViews() {
		// TODO Auto-generated method stub
		etLoginName=(EditText) findViewById(R.id.et_login_name);
		etLoginPwd=(EditText) findViewById(R.id.et_login_pwd);
		
	}

	@Override
	public void initListeners() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		
	}
	public void back(View v){
		finish();
	}
	public void startLogin(View v){
		//
		String userName=etLoginName.getText().toString().trim();
		String password=etLoginPwd.getText().toString().trim();
		if(userName.equals("")||password.equals("")){
			Toast.makeText(this, "账号不能为空", 2000).show();
			return;
		}
		password=MD5.GetMD5Code(password);
		dialog=ProgressDialog.show(this,null,getString(R.string.load_msg));
		RequesetCourierLogin courierLogin =new RequesetCourierLogin();
		courierLogin.setUserName(userName);
		courierLogin.setPassword(password);
		//异步登入
		LoginTask task=new LoginTask();
		task.execute(courierLogin);
	
	}
	public void startMainActivity(){
		Intent intent =new Intent();
		intent.setClass(this, BusinessActivity.class);
		startActivity(intent);
		finish();
	}
	public void startRegist(View v){
		//
		Intent intent =new Intent();
		intent.setClass(this, RegisteActivity.class);
		startActivity(intent);
		finish();
	}
	private class LoginTask extends AsyncTask<RequesetCourierLogin,Void,AysResponse>{

		@Override
		protected AysResponse doInBackground(RequesetCourierLogin... params) {
			
			RequesetCourierLogin courierLogin =params[0];
			AysResponse aysResponse = request.RequestByPost(MyConstants.LOGIN, courierLogin);
			
			return aysResponse;
		}

		@Override
		protected void onPostExecute(AysResponse result) {
			dialog.dismiss();
			if(result.getException()==null){
				//请求成功，只是表示服务器成功
				ResultUserLogin resultUserLogin=new ResultUserLogin();
				 resultUserLogin=request.formatResponse(result, resultUserLogin);
				 Toast.makeText(LoginActivity.this,resultUserLogin.getMsg(), 2000).show();
				 
				 if(resultUserLogin.getRet()==0){
					 // 
					 startMainActivity();
					 
				 }
				
				
			}else{
				 //出现了异常
				 String formatException = request.formatException(result);
				 Toast.makeText(LoginActivity.this, formatException, 2000).show();
			 }
			super.onPostExecute(result);
		}

		
}
}