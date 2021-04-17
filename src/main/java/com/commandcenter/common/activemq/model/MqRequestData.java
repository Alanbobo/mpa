package com.commandcenter.common.activemq.model;

public class MqRequestData {
	private MqHeaderData header;
	private Object body;

	public MqHeaderData getHeader() {
		return header;
	}

	public void setHeader(MqHeaderData header) {
		this.header = header;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

}
