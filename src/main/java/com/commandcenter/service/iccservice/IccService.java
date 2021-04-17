package com.commandcenter.service.iccservice;

import com.commandcenter.model.wcf.FeedbackModelForWcf;

import java.util.List;

/**
 * @author r25437
 * @create 2018-10-26 13:57
 * @desc icc信息处理服务接口
 **/
public interface IccService {

    /**
     * 推送处警信息给APP
     * @param caseId
     * @return
     */
    void sendIccFeedBackInfoToStaffApp(String caseId,String sendType, String message);

    /**
     * VCS新反馈推送ICC，增量推送
     * @param caseId
     * @return
     */
    void sendVcsFeedBackInfoToIcc(String caseId, List<FeedbackModelForWcf> feedbackModelForWcfList);
}
