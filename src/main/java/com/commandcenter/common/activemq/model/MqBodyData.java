package com.commandcenter.common.activemq.model;


public class MqBodyData {

    private	String	version;
    private	String	begin_time;
    private	String	end_time;
    private	String	puc_id;
    private	String	puc_sys_type;
    private String systemNo;
    private String langCode;
    private int version_data;

    public void setVersion_data(int version_data) {
        this.version_data = version_data;
    }

    public int getVersion_data() {
        return version_data;
    }

    public String getSystemNo() {
        return systemNo;
    }


    public void setSystemNo(String systemNo) {
        this.systemNo = systemNo;
    }

    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getBegin_time() {
        return begin_time;
    }

    public void setBegin_time(String begin_time) {
        this.begin_time = begin_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getPuc_id() {
        return puc_id;
    }

    public void setPuc_id(String puc_id) {
        this.puc_id = puc_id;
    }

    public String getPuc_sys_type() {
        return puc_sys_type;
    }

    public void setPuc_sys_type(String puc_sys_type) {
        this.puc_sys_type = puc_sys_type;
    }




}
