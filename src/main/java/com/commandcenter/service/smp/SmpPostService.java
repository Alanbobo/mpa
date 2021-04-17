package com.commandcenter.service.smp;

import com.commandcenter.model.smp.PostInfo;

import java.util.List;

public interface SmpPostService {
    int deleteAllSmpPostInfo();
    void batchInsertSmpPostInfo(List<PostInfo> smpPostList, String type);

    /**
     * 按唯一标识获取数据
     */
    PostInfo selectSmpPostInfoByGuid(String guid);
}
