package com.tz.ays.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.tz.ays.activity.R;
import com.tz.ays.entity.request.HttpParam;
import com.tz.ays.entity.response.AysResponse;
import com.tz.ays.entity.result.ResultCallback;

import com.tz.ays.utils.MyConstants;
import com.tz.ays.utils.MyLog;

public class HttpRequest implements IHttpRequset {

	private HttpClient client;
	private HttpResponse resp;
	private Context context;
	public HttpRequest(Context context){
		client=MyApacheHttpClient.getInstance();
		this.context=context;
	}



	public AysResponse RequestByPost(String url, HttpParam params,
			NameValuePair header) {
	
		AysResponse aysResponse =new AysResponse(); 
		HttpPost post =new HttpPost(url);
		//����url���׳��ִ�������һ��Ҫ�������ӡ����־
		MyLog.i(url);
		if(params!=null){
			// ���÷��佫NameValuePair�����ݷŵ�list��
			List<NameValuePair> parameters=new ArrayList<NameValuePair>();
			//�����ȡ�ö�����ֶ�
			Field[] declaredFields = params.getClass().getDeclaredFields();
			for(Field field:declaredFields){
				field.setAccessible(true);
				try {
					if(field.get(params)!=null){
							
						
					
					
					String name = field.getName();//��
					String value = null;
					try {
						value = String.valueOf(field.get(params));
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						aysResponse.setException(e);
						e.printStackTrace();
						aysResponse.setException(e);
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						aysResponse.setException(e);
					}
					NameValuePair pair =new BasicNameValuePair(name,value);
					parameters.add(pair);	
					}
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					aysResponse.setException(e);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					aysResponse.setException(e);
				}
		}
		UrlEncodedFormEntity entity=null;
		try {
			entity =new UrlEncodedFormEntity(parameters);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			aysResponse.setException(e);
		}
		//ͨ��post������entityʵ��
		post.setEntity(entity);
		}
		//���ݱ�ͷ
		if(header!=null){
		post.setHeader(header.getName(), header.getValue());
		}
		try{
			//�������ӳ�ʱ�Ͷ�ȡ��ʱ
			setTimeOutForRequest();
		}catch(Exception e){
			aysResponse.setException(new TimeoutException());
		}
		try {
		 resp=client.execute(post);
		 int statueCode=resp.getStatusLine().getStatusCode();
		 MyLog.d(String.valueOf(statueCode));
		 if(statueCode==200){
			 //���������Ϊ200��������ɹ�
			 //�ж���û���쳣
			 String content=EntityUtils.toString(resp.getEntity(), "UTF-8");
			 //�����ص��������ķ��ػ������ø�aysResponse
			 aysResponse.setString(content);
			 //�����ص�����ͷ���ػ��������ø�aysResponse cookie�Ϳ�����������
			 aysResponse.setHeaders(resp.getAllHeaders());
			 MyLog.d("result"+content);
			 
		 }else{
			 aysResponse.setException(new Exception());
		 }
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			aysResponse.setException(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			aysResponse.setException(e);
		}
		
		return aysResponse;
	}



	public AysResponse RequestByPost(String url, HttpParam params) {
		// TODO Auto-generated method stub
		return RequestByPost(url, params,null);
	}



	public AysResponse RequestByPost(String url) {
		// TODO Auto-generated method stub
		return RequestByPost(url, null);
	}
	
	public void setTimeOutForRequest(){			
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,MyConstants.CONNECTION_OUT);
		client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, MyConstants.SO_OUT);
	}


	
	//get����
	public AysResponse RequestByGet(String url, HttpParam params,
			NameValuePair head) {
		AysResponse aysResponse =new AysResponse();
		StringBuffer sb=new StringBuffer();
		sb.append("?");
		if(params!=null){
		Field[] declaredFields = params.getClass().getDeclaredFields();
		for(Field field:declaredFields){
			field.setAccessible(true);
			//field.get(params)����п����ǿյ�
			try {
				if(field.get(params)!=null){
					sb.append(field.getName());
					sb.append("=");
					try {
						sb.append(field.get(params));
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						aysResponse.setException(e);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
						aysResponse.setException(e);
					}
					sb.append("&");
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				aysResponse.setException(e);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				aysResponse.setException(e);
			}
		
		}
		}
		sb.deleteCharAt(sb.length()-1);
		url=url+sb.toString();						
		MyLog.d(url);
		HttpGet get=new HttpGet(url);
		if(head!=null){
			get.setHeader(head.getName(), head.getValue());
		}
		//��ʱ�ж�
		try{
			setTimeOutForRequest();
		}catch(Exception e){
			aysResponse.setException(new TimeoutException());
		}
		try {
			 HttpResponse resp = client.execute(get);
			 int statusCode = resp.getStatusLine().getStatusCode();
			 MyLog.d(String.valueOf(statusCode));
			 if(statusCode==200){
				 //����ɹ�
				 String string = EntityUtils.toString(resp.getEntity());
				 aysResponse.setString(string);
				 //��ȡ�����˴�������
				 aysResponse.setHeaders(resp.getAllHeaders());
			 }else{
				 aysResponse.setException(new Exception());
			 }
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			aysResponse.setException(e);
		} catch (IOException e) {
			e.printStackTrace();
			aysResponse.setException(e);
		}
		
		
		return aysResponse;
	}



	public AysResponse RequestByGet(String url, HttpParam params) {
		// TODO Auto-generated method stub
		return RequestByGet(url,params,null);
	}

	public AysResponse RequestByGet(String url) {
		// TODO Auto-generated method stub
		return RequestByGet(url,null);
	}
	/**
	 * ����Respֱ�ӽ������ؽ����ʵ����
	 * @param resp
	 * @param clazz
	 * @return
	 */
	public <T extends ResultCallback> T formatResponse(AysResponse resp,T clazz){
		if(resp.getException()==null){
			//û���쳣 ��ȡ�ַ��������ɶ���
			clazz=(T)JSON.parseObject(resp.getString(), clazz.getClass());
			
		}
		return clazz;
	}
	public String formatException(AysResponse resp){
		if(resp.getException() instanceof TimeoutException){
			return context.getString(R.string.timeout_excepiton);
		}else if(resp.getException() instanceof IOException){
			return context.getString(R.string.service_error);
		}else{
			return context.getString(R.string.data_excption);
		}
	}

	
}
