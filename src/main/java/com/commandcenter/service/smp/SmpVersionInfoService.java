package com.commandcenter.service.smp;

import com.commandcenter.model.smp.SmpVersionInfo;

import java.util.List;

public interface SmpVersionInfoService {
    int insertVersionInfo(SmpVersionInfo smpVersionInfo);
    int updateVersionInfo(SmpVersionInfo smpVersionInfo);
    List<SmpVersionInfo> selectVersionInfo(String dataType);
    int selectVersionByDataType(String dataType);
}
