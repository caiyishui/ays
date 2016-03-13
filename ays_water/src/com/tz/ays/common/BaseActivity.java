package com.tz.ays.common;

import com.tz.ays.http.HttpRequest;
import com.tz.ays.utils.MyLog;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
/**
 * 创建一个抽象的BaseActivity的类我们抽离出我们一些activity共有的方法，
 * 方便我们新建别的activity的时候直接实现皆苦，以后所有的activity继承此activity的时候自动实现抽象类
 * 并将BaseApplication加载到BaseActivity中，所以，以后的activity只要继承了BaseActivity
 * 就可以或许BaseApplication的资源
 *
 * @author water
 *
 */
public abstract class BaseActivity extends FragmentActivity {
	public HttpRequest request;
	public BaseApplication app;
	//分别在oncreate方法中调用，初始化方法，方便创建
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		request=BaseApplication.initHttpRequest();
		app=BaseApplication.getInstance();
		MyLog.DEBUG=true;//必须要加，不然打不出log
		setContentView();
		initViews();
		initListeners();
		initData();
	}
	public abstract void setContentView();
	
	public abstract void initViews();
	 
	public abstract void initListeners();
	
	public abstract void initData();
	
}
