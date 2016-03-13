package com.tz.ays.common;

import com.tz.ays.http.HttpRequest;

import android.app.Application;
/**
 *  自定义的application，记住在清单文件中，一定要将原来的application
 *  修改成自己的application，不然application就不是自定义的application
 *  application的生命周期不是很稳定，为了确保的application的对象都是一个，我们需要使用单列模式
 * @author  water
 *
 */
public class BaseApplication extends Application{
	//利用静态的方法返回过去
	public static BaseApplication app;
	public static HttpRequest request;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		app=this;
	}
	
	//为了确保的application的对象都是一个，我们需要使用单列模式
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
