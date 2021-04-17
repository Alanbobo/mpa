package com.commandcenter.service.smp.impl;

import com.commandcenter.dao.smp.PostInfoMapper;
import com.commandcenter.model.smp.PostInfo;
import com.commandcenter.service.smp.SmpPostService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SmpPostService")
public class SmpPostServiceImpl implements SmpPostService {
    private static Logger log = Logger.getLogger(SmpPostServiceImpl.class);
    @Autowired
    private PostInfoMapper smpPostMapper;

    @Override
    public int deleteAllSmpPostInfo() {
        return smpPostMapper.deleteAllSmpPostInfo();
    }

    /**
     * 批量增加Post数据
     * @param smpPostList
     * @param type
     */
    @Override
    public void batchInsertSmpPostInfo(List<PostInfo> smpPostList, String type) {
        switch (type) {
            case "add":
                log.info("正在进行批量" + "岗位信息增加" + "处理");
                smpPostList.stream().forEach(smpPostMapper::insertPostInfo);
                break;
            case "update":
                log.info("正在进行批量" + "岗位信息修改" + "处理");
                smpPostList.stream().forEach(smpPostMapper::updatePostInfo);
                break;
            case "delete":
                log.info("正在进行批量" + "岗位信息删除" + "处理");
                smpPostList.stream().forEach(info -> {
                    //删除用户信息
                    smpPostMapper.deleteSmpPostInfoByGuid(info.getGuid());
                });
                break;
            case "addHalf":
                log.info("正在增量同步批量" + "岗位信息" + "处理");
                smpPostList.stream().forEach(info -> {
                    if(smpPostMapper.selectSmpPostInfoByGuid(info.getGuid())!=null){
                        smpPostMapper.updatePostInfo(info);
                    }else{
                        smpPostMapper.insertPostInfo(info);
                    }
                });
                break;
            default:
                break;
        }
    }

    /**
     * 按唯一标识获取数据
     */
    @Override
    public PostInfo selectSmpPostInfoByGuid(String guid){
        return smpPostMapper.selectSmpPostInfoByGuid(guid);
    }

}
