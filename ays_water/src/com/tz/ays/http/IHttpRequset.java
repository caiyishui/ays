package com.tz.ays.http;

import org.apache.http.NameValuePair;

import com.tz.ays.entity.request.HttpParam;
import com.tz.ays.entity.response.AysResponse;
import com.tz.ays.entity.result.ResultUserLogin;

public interface IHttpRequset {
	//直接分Post和Get
	//Post请求
	//NameValuePair header存放的是cookie的信息，方便后续其他请求的验证	
	public  AysResponse RequestByPost(String url,HttpParam params,NameValuePair header );
	
	// 不带header
	public AysResponse RequestByPost(String url,HttpParam params);
	public AysResponse RequestByPost(String url);
	
	//Get请求
	public AysResponse RequestByGet(String url,HttpParam params,NameValuePair head );
	
	public AysResponse RequestByGet(String url,HttpParam params );
	
	public AysResponse RequestByGet(String url);
}
