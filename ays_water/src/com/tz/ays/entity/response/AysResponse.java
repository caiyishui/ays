package com.tz.ays.entity.response;

import org.apache.http.Header;
//设计一个请求的返回类，用来接收http请求服务器的返回状态，
//里面存放的是exception 用来存放求求的异常状态的返回
//Header 用来存放http的报头，里面可以携带cookie的信息 详细可以查看http报头的说明
//和返回的消息正文String.
public class AysResponse {
	private Exception exception;
	private Header[] headers;
	private String string;
	public Exception getException() {
		return exception;
	}
	public void setException(Exception exception) {
		this.exception = exception;
	}
	public Header[] getHeaders() {
		return headers;
	}
	public void setHeaders(Header[] headers) {
		this.headers = headers;
	}
	public String getString() {
		return string;
	}
	public void setString(String string) {
		this.string = string;
	}
	
	
}
