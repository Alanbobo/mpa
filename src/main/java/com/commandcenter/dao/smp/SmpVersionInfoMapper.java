package com.commandcenter.dao.smp;

import com.commandcenter.model.smp.SmpVersionInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SmpVersionInfoMapper {
    int insertVersionInfo(SmpVersionInfo smpVersionInfo);
    int updateVersionInfo(SmpVersionInfo smpVersionInfo);
    List<SmpVersionInfo> selectVersionInfo(String dataType);
    int selectVersionByDataType(String dataType);
}
