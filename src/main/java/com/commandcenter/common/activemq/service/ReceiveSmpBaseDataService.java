package com.commandcenter.common.activemq.service;

import com.commandcenter.common.activemq.model.MqResponseData; /**
 * @Auther: j18864
 * @Date: 2018/9/4 15:50
 * @Description:
 */
public interface ReceiveSmpBaseDataService {
    void recSmpBaseData(MqResponseData mqResponseData, int runTimeNum, String data, boolean isAddData) throws Exception;
}
