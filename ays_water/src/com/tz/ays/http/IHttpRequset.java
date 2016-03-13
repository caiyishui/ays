package com.tz.ays.http;

import org.apache.http.NameValuePair;

import com.tz.ays.entity.request.HttpParam;
import com.tz.ays.entity.response.AysResponse;
import com.tz.ays.entity.result.ResultUserLogin;

public interface IHttpRequset {
	//ֱ�ӷ�Post��Get
	//Post����
	//NameValuePair header��ŵ���cookie����Ϣ��������������������֤	
	public  AysResponse RequestByPost(String url,HttpParam params,NameValuePair header );
	
	// ����header
	public AysResponse RequestByPost(String url,HttpParam params);
	public AysResponse RequestByPost(String url);
	
	//Get����
	public AysResponse RequestByGet(String url,HttpParam params,NameValuePair head );
	
	public AysResponse RequestByGet(String url,HttpParam params );
	
	public AysResponse RequestByGet(String url);
}
