package com.tz.ays.common;

import com.tz.ays.http.HttpRequest;
import com.tz.ays.utils.MyLog;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
/**
 * ����һ�������BaseActivity�������ǳ��������һЩactivity���еķ�����
 * ���������½����activity��ʱ��ֱ��ʵ�ֽԿ࣬�Ժ����е�activity�̳д�activity��ʱ���Զ�ʵ�ֳ�����
 * ����BaseApplication���ص�BaseActivity�У����ԣ��Ժ��activityֻҪ�̳���BaseActivity
 * �Ϳ��Ի���BaseApplication����Դ
 *
 * @author water
 *
 */
public abstract class BaseActivity extends FragmentActivity {
	public HttpRequest request;
	public BaseApplication app;
	//�ֱ���oncreate�����е��ã���ʼ�����������㴴��
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		request=BaseApplication.initHttpRequest();
		app=BaseApplication.getInstance();
		MyLog.DEBUG=true;//����Ҫ�ӣ���Ȼ�򲻳�log
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
