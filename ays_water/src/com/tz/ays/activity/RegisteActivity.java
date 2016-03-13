package com.tz.ays.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.tz.ays.common.BaseActivity;
import com.tz.ays.fragment.RegistFristFragment;
import com.tz.ays.fragment.RegistNextFragment;
import com.tz.ays.utils.CheckUtils;

public class RegisteActivity extends BaseActivity{
	private FrameLayout registeFrame;
	private FragmentManager fm;
	private RegistFristFragment firstFragment;
	private RegistNextFragment nextFragment;
	private Button next;
	private int step;
	@Override
	public void setContentView() {
		// TODO Auto-generated method stub
		setContentView(R.layout.activity_regist);
	}

	@Override
	public void initViews() {
		// TODO Auto-generated method stub
		//添加frament
		registeFrame=(FrameLayout) findViewById(R.id.regist_frame);
		next=(Button) findViewById(R.id.btn_next);
		step=1;
		//获取fragment管理器
		
		fm = this.getSupportFragmentManager();
		
		if(firstFragment==null){
			firstFragment = new RegistFristFragment();
		}
		//开启fragment事务
		FragmentTransaction  beginTransaction =fm.beginTransaction();
		//更换fragment
		beginTransaction.replace(R.id.regist_frame, firstFragment);
		
		//提交事务
		beginTransaction.commit();
		fm.addOnBackStackChangedListener(new OnBackStackChangedListener() {
			
			public void onBackStackChanged() {
				// TODO Auto-generated method stub
				int backStackEntryCount = fm.getBackStackEntryCount();
				if(backStackEntryCount==0){
					next.setText("下一步");
					step=1;
					
				}else if(backStackEntryCount==1){
					next.setText("注册");
					step=2;
				}
			}
		});
		
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
	public void next(View v){
		if(step==1){
			repleaceFragment();
			
		}else if(step==2){
			regist();
			//开始注册发送注册请求
			
		}
		
		
	}

	private void regist() {
		 //获取fragment里面需要提交的数据
		 String cellPhone = firstFragment.getEtRegistMobile().getText().toString().trim();
		 String userName=firstFragment.getEtRegistName().getText().toString().trim();
		 String password=firstFragment.getEtPwd().getText().toString().trim();
		 
		 String frontPath=nextFragment.getFrontPhotoPath();
		 String backPath=nextFragment.getBackPhotoPath();
		 String certPath=nextFragment.getCertPhotoPath();
		 String age=nextFragment.getEtAge().getText().toString().trim();
		 String realName=nextFragment.getEtName().getText().toString().trim();
		 if(CheckUtils.isMobileCheck(cellPhone, this)){
			 if(CheckUtils.isUserNameCheck(userName, this)){
				 if(CheckUtils.isPasswordCheck(password, this)){
					 if(CheckUtils.isPathCheck(frontPath, this)){
						 if(CheckUtils.isPathCheck(backPath, this)){
							 if(CheckUtils.isPathCheck(certPath, this)){
								 if(CheckUtils.isAgeCheck(age, this)){
									 if(CheckUtils.isNameCheck(realName, this)){
										 Toast.makeText(RegisteActivity.this,"验证合法", 2000).show();
									 }
								 }								 
							 }
						 }
					 }
				 }
			 }
		 }
	}

	private void repleaceFragment() {
		
		if(nextFragment==null){
		nextFragment = new RegistNextFragment();
		}
		//开启fragment事务
		FragmentTransaction  beginTransaction =fm.beginTransaction();
		//更换fragment
		beginTransaction.replace(R.id.regist_frame, nextFragment);
		//将第二个添加到回退栈里面
		beginTransaction.addToBackStack("next");
		//提交事务
		beginTransaction.commit();
		
		
	}
}
