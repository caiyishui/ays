package com.tz.ays.common;

import com.tz.ays.http.HttpRequest;

import android.app.Application;
/**
 *  �Զ����application����ס���嵥�ļ��У�һ��Ҫ��ԭ����application
 *  �޸ĳ��Լ���application����Ȼapplication�Ͳ����Զ����application
 *  application���������ڲ��Ǻ��ȶ���Ϊ��ȷ����application�Ķ�����һ����������Ҫʹ�õ���ģʽ
 * @author  water
 *
 */
public class BaseApplication extends Application{
	//���þ�̬�ķ������ع�ȥ
	public static BaseApplication app;
	public static HttpRequest request;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		app=this;
	}
	
	//Ϊ��ȷ����application�Ķ�����һ����������Ҫʹ�õ���ģʽ
	public static BaseApplication getInstance(){
		return app;
		
	}
	public static HttpRequest initHttpRequest(){
		if(request==null){
		request=new HttpRequest(app.getApplicationContext());
		}
		return request;		
	}
	

}
