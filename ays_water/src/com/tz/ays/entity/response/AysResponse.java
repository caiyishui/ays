package com.tz.ays.entity.response;

import org.apache.http.Header;
//���һ������ķ����࣬��������http����������ķ���״̬��
//�����ŵ���exception �������������쳣״̬�ķ���
//Header �������http�ı�ͷ���������Я��cookie����Ϣ ��ϸ���Բ鿴http��ͷ��˵��
//�ͷ��ص���Ϣ����String.
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
