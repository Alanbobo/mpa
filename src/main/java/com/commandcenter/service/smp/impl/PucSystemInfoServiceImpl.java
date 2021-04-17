package com.commandcenter.service.smp.impl;
import java.util.List;
import java.util.Map;

import com.commandcenter.model.contacts.StaffDeviceInfoForApp;
import org.apache.log4j.Logger;

import com.commandcenter.dao.smp.PucSystemInfoMapper;
import com.commandcenter.model.smp.PucSystemInfo;
import com.commandcenter.service.smp.PucSystemInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class PucSystemInfoServiceImpl implements PucSystemInfoService{

    private static Logger log = Logger.getLogger(PucSystemInfoServiceImpl.class);
    @Autowired
    private PucSystemInfoMapper pucSystemInfoMapper;
    @Override
    public long getPucSystemInfoRowCount(){
        return pucSystemInfoMapper.getPucSystemInfoRowCount();
    }
    @Override
    public List<PucSystemInfo> selectPucSystemInfo(){
        return pucSystemInfoMapper.selectPucSystemInfo();
    }
    @Override
    public PucSystemInfo selectPucSystemInfoByObj(PucSystemInfo obj){
        return pucSystemInfoMapper.selectPucSystemInfoByObj(obj);
    }
    @Override
    public PucSystemInfo selectPucSystemInfoById(String id){
        return pucSystemInfoMapper.selectPucSystemInfoById(id);
    }
    @Override
    public int insertPucSystemInfo(PucSystemInfo value){
        return pucSystemInfoMapper.insertPucSystemInfo(value);
    }
    @Override
    public int insertNonEmptyPucSystemInfo(PucSystemInfo value){
        return pucSystemInfoMapper.insertNonEmptyPucSystemInfo(value);
    }
    @Override
    public int deletePucSystemInfoById(String id){
        return pucSystemInfoMapper.deletePucSystemInfoById(id);
    }
    @Override
    public int updatePucSystemInfoById(PucSystemInfo enti){
        return pucSystemInfoMapper.updatePucSystemInfoById(enti);
    }
    @Override
    public int updateNonEmptyPucSystemInfoById(PucSystemInfo enti){
        return pucSystemInfoMapper.updateNonEmptyPucSystemInfoById(enti);
    }

    @Override
    public int deleteAllPucSystemInfo() {
        return pucSystemInfoMapper.deleteAllPucSystemInfo();
    }

    @Override
    public void batchInsertPucSystemInfo(List<PucSystemInfo> PucSystemInfoList, String type) {
        switch (type) {
            case "add":
                log.info("正在进行批量" + "PucSystemInfo增加" + "处理");
                PucSystemInfoList.stream().forEach(pucSystemInfoMapper::insertPucSystemInfo);
                break;
            case "update":
                log.info("正在进行批量" + "PucSystemInfo修改" + "处理");
                PucSystemInfoList.stream().forEach(pucSystemInfoMapper::updatePucSystemInfoById);
                break;
            case "delete":
                log.info("正在进行批量" + "PucSystemInfo删除" + "处理");
                PucSystemInfoList.stream().forEach(info -> {
                    //删除数据字典语言信息
                    pucSystemInfoMapper.deletePucSystemInfoByIdLogic(info);
                });
                break;
            case "addHalf":
                log.info("正在增量同步批量" + "PucSystemInfo" + "处理");
                PucSystemInfoList.stream().forEach(info -> {
                    if(pucSystemInfoMapper.selectPucSystemInfoById(info.getGuid())!=null){
                        pucSystemInfoMapper.updatePucSystemInfoById(info);
                    }else{
                        pucSystemInfoMapper.insertPucSystemInfo(info);
                    }
                });
                break;
            default:

                break;
        }
    }

    @Override
    public List<PucSystemInfo> selectPucSystemInfoByMap(Map paraMap) {
        return pucSystemInfoMapper.selectPucSystemInfoByMap(paraMap);
    }


    public PucSystemInfoMapper getPucSystemInfoMapper() {
        return this.pucSystemInfoMapper;
    }

    public void setPucSystemInfoMapper(PucSystemInfoMapper pucSystemInfoMapper) {
        this.pucSystemInfoMapper = pucSystemInfoMapper;
    }

}