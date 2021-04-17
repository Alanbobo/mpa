package com.commandcenter.common.activemq.model;

import java.util.HashMap;

public class MqResponseData {
	private MqHeaderData header;
	private HashMap<String, Object> body;


	public MqHeaderData getHeader() {
		return header;
	}

	public void setHeader(MqHeaderData header) {
		this.header = header;
	}

	public HashMap<String, Object> getBody() {
		return body;
	}

	public void setBody(HashMap<String, Object> body) {
		this.body = body;
	}


}
