package com.commandcenter.model.smp;

import java.util.Date;

public class SmpOrgInfo {
    private String guid;

    private String orgIdentifier;

    private String orgGovCode;

    private String districtCode;

    private String orgName;

    private String orgType;

    private String busessType;

    private Long seq;

    private String parentOrgGuid;

    private Double latitude;

    private Double longitude;

    private String contact;

    private String contactno;

    private String fax;

    private String isvalid;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private String remark;

    private String regionType;

    private String importAllName;

    private Short is110;

    private String systemNo;

    private String orgSegmentContent;

    private String upOrgGuid;

    private String orgShortName;

    private String upOrgGuidName;

    private Short enableFlag;

    private Date deleteTime;

    private String source;

    private String pucId;

    private String systemId;

    private String orgCode;

    private String orgIconGuid;

    private String versionSeq;

    private String pucSysType;

    private String initPoint;

    private byte[] orgIcon;

    private int version;

    public void setVersion(int version) {
        this.version = version;
    }

    public int getVersion() {
        return version;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid == null ? null : guid.trim();
    }

    public String getOrgIdentifier() {
        return orgIdentifier;
    }

    public void setOrgIdentifier(String orgIdentifier) {
        this.orgIdentifier = orgIdentifier == null ? null : orgIdentifier.trim();
    }

    public String getOrgGovCode() {
        return orgGovCode;
    }

    public void setOrgGovCode(String orgGovCode) {
        this.orgGovCode = orgGovCode == null ? null : orgGovCode.trim();
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode == null ? null : districtCode.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType == null ? null : orgType.trim();
    }

    public String getBusessType() {
        return busessType;
    }

    public void setBusessType(String busessType) {
        this.busessType = busessType == null ? null : busessType.trim();
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getParentOrgGuid() {
        return parentOrgGuid;
    }

    public void setParentOrgGuid(String parentOrgGuid) {
        this.parentOrgGuid = parentOrgGuid == null ? null : parentOrgGuid.trim();
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno == null ? null : contactno.trim();
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    public String getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid == null ? null : isvalid.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getRegionType() {
        return regionType;
    }

    public void setRegionType(String regionType) {
        this.regionType = regionType == null ? null : regionType.trim();
    }

    public String getImportAllName() {
        return importAllName;
    }

    public void setImportAllName(String importAllName) {
        this.importAllName = importAllName == null ? null : importAllName.trim();
    }

    public Short getIs110() {
        return is110;
    }

    public void setIs110(Short is110) {
        this.is110 = is110;
    }

    public String getSystemNo() {
        return systemNo;
    }

    public void setSystemNo(String systemNo) {
        this.systemNo = systemNo == null ? null : systemNo.trim();
    }

    public String getOrgSegmentContent() {
        return orgSegmentContent;
    }

    public void setOrgSegmentContent(String orgSegmentContent) {
        this.orgSegmentContent = orgSegmentContent == null ? null : orgSegmentContent.trim();
    }

    public String getUpOrgGuid() {
        return upOrgGuid;
    }

    public void setUpOrgGuid(String upOrgGuid) {
        this.upOrgGuid = upOrgGuid == null ? null : upOrgGuid.trim();
    }

    public String getOrgShortName() {
        return orgShortName;
    }

    public void setOrgShortName(String orgShortName) {
        this.orgShortName = orgShortName == null ? null : orgShortName.trim();
    }

    public String getUpOrgGuidName() {
        return upOrgGuidName;
    }

    public void setUpOrgGuidName(String upOrgGuidName) {
        this.upOrgGuidName = upOrgGuidName == null ? null : upOrgGuidName.trim();
    }

    public Short getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(Short enableFlag) {
        this.enableFlag = enableFlag;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getPucId() {
        return pucId;
    }

    public void setPucId(String pucId) {
        this.pucId = pucId == null ? null : pucId.trim();
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId == null ? null : systemId.trim();
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public String getOrgIconGuid() {
        return orgIconGuid;
    }

    public void setOrgIconGuid(String orgIconGuid) {
        this.orgIconGuid = orgIconGuid == null ? null : orgIconGuid.trim();
    }

    public String getVersionSeq() {
        return versionSeq;
    }

    public void setVersionSeq(String versionSeq) {
        this.versionSeq = versionSeq == null ? null : versionSeq.trim();
    }

    public String getPucSysType() {
        return pucSysType;
    }

    public void setPucSysType(String pucSysType) {
        this.pucSysType = pucSysType == null ? null : pucSysType.trim();
    }

    public String getInitPoint() {
        return initPoint;
    }

    public void setInitPoint(String initPoint) {
        this.initPoint = initPoint == null ? null : initPoint.trim();
    }

    public byte[] getOrgIcon() {
        return orgIcon;
    }

    public void setOrgIcon(byte[] orgIcon) {
        this.orgIcon = orgIcon;
    }
}