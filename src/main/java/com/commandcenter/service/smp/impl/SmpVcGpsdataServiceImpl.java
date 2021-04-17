package com.commandcenter.service.smp.impl;

import com.commandcenter.dao.smp.SmpVcGpsdataMapper;
import com.commandcenter.model.smp.SmpVcGpsdata;
import com.commandcenter.service.smp.SmpVcGpsdataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("smpVcGpsdataService")
public class SmpVcGpsdataServiceImpl implements SmpVcGpsdataService {
@Autowired
SmpVcGpsdataMapper smpVcGpsdataMapper;
    @Override
    public int deleteByPrimaryKey(String guid) {
        return smpVcGpsdataMapper.deleteByPrimaryKey(guid);
    }

    @Override
    public int insert(SmpVcGpsdata record) {
        return smpVcGpsdataMapper.insert(record);
    }

    @Override
    public int insertSelective(SmpVcGpsdata record) {
        return smpVcGpsdataMapper.insertSelective(record);
    }

    @Override
    public SmpVcGpsdata selectByPrimaryKey(String guid) {
        return smpVcGpsdataMapper.selectByPrimaryKey(guid);
    }

    @Override
    public int updateByPrimaryKeySelective(SmpVcGpsdata record) {
        return smpVcGpsdataMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SmpVcGpsdata record) {
        return smpVcGpsdataMapper.updateByPrimaryKey(record);
    }

    @Override
    public void deleteAllVcgpsdataInfor() {
        smpVcGpsdataMapper.deleteAllVcgpsdataInfor();
    }

    @Override
    public List<SmpVcGpsdata> selectByStaffGuid(String staffGuid) {
        return smpVcGpsdataMapper.selectByStaffGuid(staffGuid);
    }
}