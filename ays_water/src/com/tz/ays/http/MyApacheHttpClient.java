package com.tz.ays.http;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.DefaultClientConnection;
//  单列模式，保证是同一个客户端，方便数据传输
public class MyApacheHttpClient {
	public MyApacheHttpClient(){
		
	}
	public static HttpClient httpClient;
	
	public static HttpClient getInstance(){
		
		if(httpClient==null){
			httpClient =new DefaultHttpClient();
		}
		return httpClient;			
	}
		
		
		
	}

