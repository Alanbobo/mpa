package com.commandcenter.service.smp.impl;

import com.commandcenter.dao.smp.PucSmartonegroupInfoDao;
import com.commandcenter.model.smp.PucSmartonegroupInfo;
import com.commandcenter.service.smp.PucSmartonegroupInfoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PucSmartonegroupInfoServiceImpl implements PucSmartonegroupInfoService {
    private static Logger log = Logger.getLogger(PucSmartonegroupInfoServiceImpl.class);
    @Autowired
    private PucSmartonegroupInfoDao pucSmartonegroupInfoDao;
    @Override
    public long getPucSmartonegroupInfoRowCount(){
        return pucSmartonegroupInfoDao.getPucSmartonegroupInfoRowCount();
    }
    @Override
    public List<PucSmartonegroupInfo> selectPucSmartonegroupInfo(){
        return pucSmartonegroupInfoDao.selectPucSmartonegroupInfo();
    }
    @Override
    public PucSmartonegroupInfo selectPucSmartonegroupInfoByObj(PucSmartonegroupInfo obj){
        return pucSmartonegroupInfoDao.selectPucSmartonegroupInfoByObj(obj);
    }
    @Override
    public PucSmartonegroupInfo selectPucSmartonegroupInfoById(Object id){
        return pucSmartonegroupInfoDao.selectPucSmartonegroupInfoById(id);
    }
    @Override
    public int insertPucSmartonegroupInfo(PucSmartonegroupInfo value){
        return pucSmartonegroupInfoDao.insertPucSmartonegroupInfo(value);
    }
    @Override
    public int insertNonEmptyPucSmartonegroupInfo(PucSmartonegroupInfo value){
        return pucSmartonegroupInfoDao.insertNonEmptyPucSmartonegroupInfo(value);
    }
    @Override
    public int deletePucSmartonegroupInfoById(Object id){
        return pucSmartonegroupInfoDao.deletePucSmartonegroupInfoById(id);
    }
    @Override
    public int updatePucSmartonegroupInfoById(PucSmartonegroupInfo enti){
        return pucSmartonegroupInfoDao.updatePucSmartonegroupInfoById(enti);
    }
    @Override
    public int updateNonEmptyPucSmartonegroupInfoById(PucSmartonegroupInfo enti){
        return pucSmartonegroupInfoDao.updateNonEmptyPucSmartonegroupInfoById(enti);
    }

    public PucSmartonegroupInfoDao getPucSmartonegroupInfoDao() {
        return this.pucSmartonegroupInfoDao;
    }

    public void setPucSmartonegroupInfoDao(PucSmartonegroupInfoDao pucSmartonegroupInfoDao) {
        this.pucSmartonegroupInfoDao = pucSmartonegroupInfoDao;
    }

    @Override
    public int deleteAllSmartoneGroup() {
        return pucSmartonegroupInfoDao.deleteAllSmartoneGroup();
    }
    @Override
    public void batchInsertSmartoneGroupInfo(List<PucSmartonegroupInfo> pucSmartonegroupInfos, String type) {
        switch (type) {
            case "add":
                log.info("正在进行批量" + "SMARTONEGROUP信息增加" + "处理");
                pucSmartonegroupInfos.stream().forEach(pucSmartonegroupInfoDao::insertPucSmartonegroupInfo);
                break;
            case "update":
                log.info("正在进行批量" + "SMARTONEGROUP信息修改" + "处理");
                pucSmartonegroupInfos.stream().forEach(pucSmartonegroupInfoDao::updatePucSmartonegroupInfoById);
                break;
            case "delete":
                log.info("正在进行批量" + "SMARTONEGROUP信息删除" + "处理");
                pucSmartonegroupInfos.stream().forEach(info -> {
                    //删除用户信息
                    pucSmartonegroupInfoDao.deletePucSmartonegroupInfoById(info.getSmartoneGroupGuid());
                });
                break;
            case "addHalf":
                log.info("正在增量同步批量" + "SMARTONEGROUP信息" + "处理");
                pucSmartonegroupInfos.stream().forEach(info -> {
                    if(pucSmartonegroupInfoDao.selectPucSmartonegroupInfoById(info.getSmartoneGroupGuid())!=null){
                        pucSmartonegroupInfoDao.updatePucSmartonegroupInfoById(info);
                    }else{
                        pucSmartonegroupInfoDao.insertPucSmartonegroupInfo(info);
                    }
                });
                break;
            default:
                break;
        }
    }
}