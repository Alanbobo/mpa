package com.commandcenter.common.activemq.model;

public class MqHeaderData {
	private String system_id;
	private String subsystem_id;
	private String msgid;
	private String related_id;
	private String send_time;
	private String cmd;
	private String request;
	private String reques_type;
	private String reponse;
	private String reponse_type;
	private String action;

	public String getSystem_id() {
		return system_id;
	}

	public void setSystem_id(String system_id) {
		this.system_id = system_id;
	}

	public String getSubsystem_id() {
		return subsystem_id;
	}

	public void setSubsystem_id(String subsystem_id) {
		this.subsystem_id = subsystem_id;
	}

	public String getMsgid() {
		return msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}

	public String getRelated_id() {
		return related_id;
	}

	public void setRelated_id(String related_id) {
		this.related_id = related_id;
	}

	public String getSend_time() {
		return send_time;
	}

	public void setSend_time(String send_time) {
		this.send_time = send_time;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getReques_type() {
		return reques_type;
	}

	public void setReques_type(String reques_type) {
		this.reques_type = reques_type;
	}

	public String getReponse() {
		return reponse;
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
	}

	public String getReponse_type() {
		return reponse_type;
	}

	public void setReponse_type(String reponse_type) {
		this.reponse_type = reponse_type;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}


}
